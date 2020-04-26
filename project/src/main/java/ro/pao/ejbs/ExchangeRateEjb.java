package ro.pao.ejbs;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.pao.constants.EnumApplicationCurrency;
import ro.pao.dto.ExchangeRateTo;
import ro.pao.dto.TransactionTo;
import ro.pao.entities.ExchangeRate;
import ro.pao.entities.ExchangeRate_;
import ro.pao.utils.UtilDate;
import ro.pao.utils.UtilToBuild;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("DuplicatedCode")
@Stateless
public class ExchangeRateEjb implements Serializable {

    private static final long serialVersionUID = -4987754943167705650L;

    private static final Logger logger = LoggerFactory.getLogger(ExchangeRateEjb.class);

    @PersistenceContext
    private EntityManager em;

    /**
     * @param day       The day where the check is being made.
     * @param provider  The provider, only BNR.
     * @param reference reference currency.
     * @param asked     asked currency.
     * @return It returns the transfer object if there is one or null otherwise.
     */
    public ExchangeRateTo findExchangeRate(Date day, String provider, EnumApplicationCurrency reference, EnumApplicationCurrency asked) {
        logger.debug("findExchangeRate(day:{} provider:{} reference:{} asked:{}) - start", day, provider, reference, asked);
        Date date = UtilDate.onlyDate(day);
        if (date == null) {
            return null;
        }
        logger.debug("findExchangeRate() - date:{}", date);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ExchangeRate> cq = cb.createQuery(ExchangeRate.class);
        Root<ExchangeRate> cr = cq.from(ExchangeRate.class);
        ArrayList<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(cr.get(ExchangeRate_.provider), provider));
        predicates.add(cb.equal(cr.get(ExchangeRate_.reference), reference));
        predicates.add(cb.equal(cr.get(ExchangeRate_.to), asked));
        predicates.add(cb.equal(cr.get(ExchangeRate_.queryDate), date));
        cq.where(predicates.toArray(new Predicate[0]));
        TypedQuery<ExchangeRate> cqq = em.createQuery(cq);
        cqq.setMaxResults(1);
        Optional<ExchangeRate> findFirst = cqq.getResultStream().findFirst();
        if (findFirst.isPresent()) {
            logger.debug("findExchangeRate() - end, resultId:{}", findFirst.get().getId());
            return UtilToBuild.buildTo(findFirst.get());
        }
        logger.debug("findExchangeRate() - end, no result found");
        return null;
    }

    public List<ExchangeRateTo> findExchangeRate(Date day, String provider) {
        logger.debug("findExchangeRate(day:{} provider:{}) - start", day, provider);
        Date date = UtilDate.onlyDate(day);
        if (date == null) {
            return null;
        }
        logger.debug("findExchangeRate() - date:{}", date);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ExchangeRate> cq = cb.createQuery(ExchangeRate.class);
        Root<ExchangeRate> cr = cq.from(ExchangeRate.class);
        ArrayList<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(cr.get(ExchangeRate_.provider), provider));
        predicates.add(cb.equal(cr.get(ExchangeRate_.queryDate), date));
        cq.where(predicates.toArray(new Predicate[0]));
        TypedQuery<ExchangeRate> cqq = em.createQuery(cq);
        List<ExchangeRate> result = cqq.getResultList();
        if (!result.isEmpty()) {
            List<ExchangeRateTo> returnResult = new ArrayList<>();
            result.forEach(aa -> returnResult.add(UtilToBuild.buildTo(aa)));
            logger.debug("findExchangeRate() - end, size:{}", returnResult.size());
            return returnResult;
        }
        logger.debug("findExchangeRate() - end, size:0");
        return null;
    }

    /**
     * A schedule method that runs from time to time.
     * It checks if the database is up to date with the exchange rate.
     */
    @Schedule(hour = "*", minute = "*/30", persistent = false)
    @Transactional
    public void importExchangeRate() {
        logger.debug("importExchangeRate() - start");
        try {
            URL url = new URL("https://www.bnr.ro/nbrfxrates.xml");
            DocumentFactory factory = DocumentFactory.getInstance();
            Map<String, String> uris = new TreeMap<>();
            uris.put("p", "http://www.bnr.ro/xsd");
            factory.setXPathNamespaceURIs(uris);
            SAXReader reader = new SAXReader(factory);
            Document document = reader.read(url);
            Element root = document.getRootElement();
            logger.debug("importExchangeRate() - XmlRoot:{} namespace:{}", root.getName(), root.getNamespace());
            Element body = (Element) root.selectSingleNode("p:Body");
            Element cube = (Element) body.selectSingleNode("p:Cube");
            String date = cube.valueOf("@date");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date dateBnr = df.parse(date);
            Date today = UtilDate.onlyDate(new Date());
            @SuppressWarnings("unchecked") List<Element> rates = body.selectNodes("p:Cube/p:Rate");
            for (Element rate : rates) {
                logger.debug("importExchangeRate() - currency:{} rate:{}", rate.attributeValue("currency"), rate.getStringValue());
                EnumApplicationCurrency currency = EnumApplicationCurrency.whatCurrency(rate.attributeValue("currency"));
                ExchangeRateTo exchangeRateTo = findExchangeRate(today, "BNR", currency, EnumApplicationCurrency.RON);
                if (exchangeRateTo == null) {
                    ExchangeRate exchangeRate = new ExchangeRate();
                    exchangeRate.setPublishingDate(dateBnr);
                    exchangeRate.setQueryDate(today);
                    exchangeRate.setProvider("BNR");
                    exchangeRate.setRate(Double.parseDouble(rate.getStringValue()));
                    exchangeRate.setReference(currency);
                    exchangeRate.setAutomated(true);
                    exchangeRate.setTo(EnumApplicationCurrency.RON);
                    logger.info("importExchangeRate() - Adding exchange rate to database, exchangeRate={}", exchangeRate);
                    em.persist(exchangeRate);
                } else {
                    logger.info("importExchangeRate() - The exchange rate already exists");
                }
            }
            em.flush();
        } catch (Exception e) {
            logger.error("importExchangeRate() - Exception: ", e);
        }
        logger.debug("importExchangeRate() - end");
    }

    public String asCsv(List<ExchangeRateTo> rows) {
        StringBuilder sb = new StringBuilder();
        sb.append(ExchangeRateTo.asCsvHeader());
        for (ExchangeRateTo row : rows) {
            sb.append(row.asCsvEntry());
        }
        return sb.toString();
    }
}

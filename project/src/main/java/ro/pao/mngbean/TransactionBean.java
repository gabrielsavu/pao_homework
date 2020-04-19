package ro.pao.mngbean;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.pao.dto.ExchangeRateTo;
import ro.pao.dto.TransactionTo;
import ro.pao.ejbs.ExchangeRateEjb;
import ro.pao.ejbs.TransactionEjb;
import ro.pao.entities.Client;
import ro.pao.entities.Client_;
import ro.pao.entities.ExchangeRate;
import ro.pao.entities.Transaction;
import ro.pao.utils.UtilDate;
import ro.pao.utils.UtilToBuild;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Named("transactionBean")
@ConversationScoped
public class TransactionBean implements Serializable {

    private static final long serialVersionUID = -667714283336342909L;

    private static final Logger logger = LoggerFactory.getLogger(TransactionBean.class);

    @PersistenceContext
    private EntityManager em;

    @EJB
    private TransactionEjb transactionEjb;

    @EJB
    private ExchangeRateEjb exchangeRateEjb;

    private LazyTransactions items;

    private TransactionTo item;

    private List<SelectItem> rates = new ArrayList<>();

    private Date queryDate;

    private String inputName;

    private class LazyTransactions extends LazyDataModel<TransactionTo> {
        private Integer rowCount;

        public LazyTransactions() {
            super();
        }

        @Override
        public int getRowCount() {
            if (rowCount == null) {
                logger.debug("getRowCount() - start");
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<Long> qq = cb.createQuery(Long.class);
                Root<Transaction> root = qq.from(Transaction.class);
                qq.select(cb.count(root));
                Number results = em.createQuery(qq).getSingleResult();
                logger.debug("getRowCount() - results:{}", results);
                this.rowCount = results.intValue();
            }
            return rowCount;
        }

        @Override
        public void setRowCount(int rowCount) {
            this.rowCount = rowCount;
        }

        @Override
        public List<TransactionTo> load(int first, int pageSize, String sortField, SortOrder sortOrder,
                                        Map<String, Object> filters) {
            logger.debug("load(first:{}, pageSize:{}, sortField:{}, sortOrder:{}, filters:{}) - start",
                    first, pageSize, sortField, sortOrder, filters.toString());
            ArrayList<TransactionTo> returnValue = new ArrayList<>();
            try {
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<Transaction> qq = cb.createQuery(Transaction.class);
                Root<Transaction> root = qq.from(Transaction.class);
                TypedQuery<Transaction> query = em.createQuery(qq.select(root));
                query.setFirstResult(first);
                query.setMaxResults(pageSize);
                List<Transaction> results = query.getResultList();
                for (Transaction result : results) {
                    TransactionTo clientTo = UtilToBuild.buildTo(result);
                    returnValue.add(clientTo);
                }
                logger.debug("load(first:{}, pageSize:{}, sortField:{}, sortOrder:{}, filters:{}) - return size:{}",
                        first, pageSize, sortField, sortOrder, filters.toString(), returnValue.size());
            } catch (Exception e) {
                logger.error("load(first:{}, pageSize:{}, sortField:{}, sortOrder:{}, filters:{}) - Exception: " + e.getMessage(),
                        first, pageSize, sortField, sortOrder, filters.toString(), e);
            }
            return returnValue;
        }
    }

    @PostConstruct
    public void postConstruct() {
        logger.debug("postConstruct() - start");
        items = new LazyTransactions();
        item = new TransactionTo();
        List<ExchangeRateTo> bnr = exchangeRateEjb.findExchangeRate(UtilDate.onlyDate(new Date()), "BNR");
        queryDate = bnr.get(0).getQueryDate();
        for (ExchangeRateTo rate : bnr) {
            StringBuilder sb = new StringBuilder();
            sb.append("1 ");
            sb.append(rate.getReference());
            sb.append(" = ");
            sb.append(rate.getRate());
            sb.append(" ");
            sb.append(rate.getTo());
            rates.add(new SelectItem(rate, sb.toString()));
        }
        logger.debug("postConstruct() - end");
    }

    public List<Client> inputNameSuggestions(String enteredValue) {
        logger.debug("inputNameSuggestions(enteredValue : {}) - start", enteredValue);

        String pattern = "(.*)\\s\\|\\sLastName:\\s(.*)\\s\\|\\sFirstName:\\s(.*)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(enteredValue);
        if (m.find()) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Client> qq = cb.createQuery(Client.class);
            Root<Client> cr = qq.from(Client.class);
            ArrayList<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.like(cr.get(Client_.cnp), "%" + m.group(1) + "%"));
            qq.where(cb.or(predicates.toArray(new Predicate[0])));
            TypedQuery<Client> query = em.createQuery(qq);
            List<Client> results = query.getResultList();
            logger.debug("inputNameSuggestions() - end, return : {}", results);
            return results;
        }
        logger.debug("inputNameSuggestions() - end, return : null");
        return null;
    }

    public String addTransaction() {
        logger.debug("addTransaction() - start, item : {}", item);
        if (item.getExchangeRate() == null || item.getClient() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Invalid client."));
            return null;
        }
        transactionEjb.saveTransaction(item);
        logger.debug("addTransaction() - end");
        return null;
    }

    public LazyTransactions getItems() {
        return items;
    }

    public void setItems(LazyTransactions items) {
        this.items = items;
    }

    public TransactionTo getItem() {
        return item;
    }

    public void setItem(TransactionTo item) {
        this.item = item;
    }

    public List<SelectItem> getRates() {
        return rates;
    }

    public void setRates(List<SelectItem> rates) {
        this.rates = rates;
    }

    public Date getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(Date queryDate) {
        this.queryDate = queryDate;
    }

    public String getInputName() {
        return inputName;
    }

    public void setInputName(String inputName) {
        this.inputName = inputName;
    }
}

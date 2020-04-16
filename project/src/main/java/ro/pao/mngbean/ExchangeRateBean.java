package ro.pao.mngbean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.pao.dto.ExchangeRateTo;
import ro.pao.ejbs.ExchangeRateEjb;
import ro.pao.utils.UtilDate;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named("exchangeRateBean")
@RequestScoped
public class ExchangeRateBean implements Serializable {

    private static final long serialVersionUID = -222807543592959305L;

    private static final Logger logger = LoggerFactory.getLogger(ExchangeRateBean.class);

    private List<ExchangeRateTo> items = new ArrayList<>();

    private Boolean init = false;

    @EJB
    private ExchangeRateEjb exchangeRateEjb;

    public String actionRenderView() {
        logger.debug("actionRenderView() - start");
        items.clear();
        List<ExchangeRateTo> exchangeRates = exchangeRateEjb.findExchangeRate(UtilDate.onlyDate(new Date()), "BNR");
        if (exchangeRates == null) {
            return null;
        }
        if (!exchangeRates.isEmpty()) {
            init = true;
            items.addAll(exchangeRates);
        }
        logger.debug("actionRenderView() - end");
        return null;
    }

    public Boolean getInit() {
        return init;
    }

    public void setInit(Boolean init) {
        this.init = init;
    }

    public List<ExchangeRateTo> getItems() {
        return items;
    }

    public void setItems(List<ExchangeRateTo> items) {
        this.items = items;
    }
}

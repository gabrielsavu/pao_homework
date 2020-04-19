package ro.pao.converters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.pao.constants.EnumApplicationCurrency;
import ro.pao.dto.ExchangeRateTo;
import ro.pao.entities.ExchangeRate;
import ro.pao.mngbean.TransactionBean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesConverter(forClass = ExchangeRateTo.class)
public class ExchangeRateConverter implements Converter {

    private static final Logger logger = LoggerFactory.getLogger(ExchangeRateConverter.class);

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        logger.debug("getAsObject(value : {}) - start", value);
        ExchangeRateTo exchangeRate = new ExchangeRateTo();
        String pattern = "(.*):\\s*1\\s(.*)\\s*=\\s*(.*)\\s(.*)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(value);
        if (m.find()) {
            exchangeRate.setProvider(m.group(1).trim());
            exchangeRate.setReference(EnumApplicationCurrency.valueOf(m.group(2).trim()));
            exchangeRate.setRate(Double.valueOf(m.group(3).trim()));
            exchangeRate.setTo(EnumApplicationCurrency.valueOf(m.group(4).trim()));
            logger.debug("getAsObject(value : {}) - end, return : {}", value, exchangeRate);
            return exchangeRate;
        }
        logger.debug("getAsObject(value : {}) - end, return : null", value);
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        logger.debug("getAsString(value : {}) - start", value);
        if (value instanceof ExchangeRateTo) {
            ExchangeRateTo exchangeRate = (ExchangeRateTo) value;
            logger.debug("getAsString(value : {}) - end", value);
            return exchangeRate.getProvider() +
                    ": 1 " +
                    exchangeRate.getReference() +
                    " = " +
                    exchangeRate.getRate() +
                    " " +
                    exchangeRate.getTo();
        }
        logger.debug("getAsString(value : {}) - end, return null", value);
        return null;
    }
}

package ro.pao.converters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.pao.dto.ClientTo;
import ro.pao.dto.ExchangeRateTo;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesConverter(forClass = ClientTo.class)
public class ClientConverter implements Converter {

    private static final Logger logger = LoggerFactory.getLogger(ClientConverter.class);

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        logger.debug("getAsObject(value : {}) - start", value);

        ClientTo client = new ClientTo();
        String pattern = "(.*)\\s\\|\\sLastName:\\s(.*)\\s\\|\\sFirstName:\\s(.*)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(value);
        if (m.find()) {
            client.setCnp(m.group(1).trim());
            client.setLastName(m.group(2).trim());
            client.setFirstName(m.group(3).trim());
            logger.debug("getAsObject(value : {}) - end, return : {}", value, client);
            return client;
        }
        logger.debug("getAsObject(value : {}) - end, return : null", value);
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        logger.debug("getAsString(value : {}) - start", value);
        if (value instanceof ClientTo) {
            ClientTo client = (ClientTo) value;
            logger.debug("getAsString(value : {}) - end", value);
            return client.getCnp() + " | LastName: " + client.getLastName() + " | FirstName: " + client.getFirstName();
        }
        logger.debug("getAsString(value : {}) - end, return : null", value);
        return null;
    }
}

package ro.pao.ejbs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.pao.dto.ClientTo;
import ro.pao.entities.Client;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateless
public class ClientEjb implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(ClientEjb.class);

    @PersistenceContext
    private EntityManager em;

    public Client saveClient(String firstName, String lastName, String cnp) {
        Client client = new Client();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setCnp(cnp);
        em.persist(client);
        em.flush();
        return client;
    }

    public Client saveClient(ClientTo item) {
        Client client = new Client();
        logger.debug("{}", item);
        client.setFirstName(item.getFirstName());
        client.setLastName(item.getLastName());
        client.setCnp(item.getCnp());
        em.persist(client);
        em.flush();
        return client;
    }

    public String asCsv(List<ClientTo> rows) {
        StringBuilder sb = new StringBuilder();
        sb.append(ClientTo.asCsvHeader());
        for (ClientTo row : rows) {
            sb.append(row.asCsvEntry());
        }
        return sb.toString();
    }

}

package ro.pao.ejbs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.pao.dto.ClientTo;
import ro.pao.entities.Client;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

@Stateless
public class ClientEjb implements Serializable {
    @PersistenceContext
    private EntityManager em;

    private static final Logger logger = LoggerFactory.getLogger(ClientEjb.class);

    public Client saveClient(String firstName, String lastName) {
        Client client = new Client();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        em.persist(client);
        em.flush();
        return client;
    }

    public Client saveClient(ClientTo item) {
        Client client = new Client();
        client.setFirstName(item.getFirstName());
        client.setLastName(item.getLastName());
        em.persist(client);
        em.flush();
        return client;
    }

}

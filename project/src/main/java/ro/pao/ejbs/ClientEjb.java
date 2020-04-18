package ro.pao.ejbs;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.pao.dto.ClientTo;
import ro.pao.entities.Client;
import ro.pao.utils.UtilToBuild;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Stateless
public class ClientEjb implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(ClientEjb.class);

    @PersistenceContext
    private EntityManager em;

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
        logger.debug("{}", item);
        client.setFirstName(item.getFirstName());
        client.setLastName(item.getLastName());
        em.persist(client);
        em.flush();
        return client;
    }

}

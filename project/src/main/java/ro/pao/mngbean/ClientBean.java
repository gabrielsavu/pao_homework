package ro.pao.mngbean;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.pao.dto.ClientTo;
import ro.pao.ejbs.ClientEjb;
import ro.pao.entities.Client;
import ro.pao.utils.DataUpdateEvent;
import ro.pao.utils.UtilToBuild;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named("clientBean")
@RequestScoped
public class ClientBean implements Serializable {

    private static final long serialVersionUID = 7051849006519008366L;

    private static final Logger logger = LoggerFactory.getLogger(ClientBean.class);

    @PersistenceContext
    private EntityManager em;

    @EJB
    private ClientEjb clientEjb;

    @Inject
    private Event<DataUpdateEvent> dataUpdatedEvent;

    private LazyClients items;

    private ClientTo item;

    private class LazyClients extends LazyDataModel<ClientTo> {
        private Integer rowCount;

        public LazyClients() {
            super();
        }

        @Override
        public int getRowCount() {
            if (rowCount == null) {
                logger.debug("getRowCount() - start");
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<Long> qq = cb.createQuery(Long.class);
                Root<Client> root = qq.from(Client.class);
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
        public List<ClientTo> load(int first, int pageSize, String sortField, SortOrder sortOrder,
                                   Map<String, Object> filters) {
            logger.debug("load(first:{}, pageSize:{}, sortField:{}, sortOrder:{}, filters:{}) - start",
                    first, pageSize, sortField, sortOrder, filters.toString());
            ArrayList<ClientTo> returnValue = new ArrayList<>();
            try {
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<Client> qq = cb.createQuery(Client.class);
                Root<Client> root = qq.from(Client.class);
                TypedQuery<Client> query = em.createQuery(qq.select(root));
                query.setFirstResult(first);
                query.setMaxResults(pageSize);
                List<Client> results = query.getResultList();
                for (Client result : results) {
                    ClientTo clientTo = UtilToBuild.buildTo(result);
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
        items = new LazyClients();
        item = new ClientTo();
        logger.debug("postConstruct() - end");
    }

    @Transactional
    public String addUser() {
        logger.debug("addUser() - start, item : {}", item);
        Client client = clientEjb.saveClient(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User added."));
        logger.debug("addUser() - end, userId:{}", client.getId());
        dataUpdatedEvent.fire(new DataUpdateEvent());
        return "clients.xhtml";
    }

    public LazyClients getItems() {
        return items;
    }

    public void setItems(LazyClients items) {
        this.items = items;
    }

    public ClientTo getItem() {
        return item;
    }

    public void setItem(ClientTo item) {
        this.item = item;
    }
}

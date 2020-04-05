package ro.pao.ejbs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.pao.dto.ClientTo;
import ro.pao.dto.ExchangeRateTo;
import ro.pao.dto.TransactionTo;
import ro.pao.entities.Client;
import ro.pao.entities.ExchangeRate;
import ro.pao.entities.Transaction;
import ro.pao.utils.UtilDate;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.Date;

@Stateless
public class TransactionEjb implements Serializable {
    @PersistenceContext
    private EntityManager em;

    private static final Logger logger = LoggerFactory.getLogger(TransactionEjb.class);

    public Transaction saveTransaction(TransactionTo item) {
        Transaction transaction = new Transaction();
        transaction.setClient(em.find(Client.class, item.getClient()));
        transaction.setDate(new Date());
        transaction.setExchangeRate(em.find(ExchangeRate.class, item.getExchangeRate()));
        em.persist(transaction);
        em.flush();
        return transaction;
    }
}

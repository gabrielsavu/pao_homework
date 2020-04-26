package ro.pao.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.pao.dto.ClientTo;
import ro.pao.dto.ExchangeRateTo;
import ro.pao.dto.TransactionTo;
import ro.pao.ejbs.ClientEjb;
import ro.pao.ejbs.ExchangeRateEjb;
import ro.pao.ejbs.TransactionEjb;
import ro.pao.entities.Client;
import ro.pao.entities.ExchangeRate;
import ro.pao.entities.Transaction;

import javax.ejb.EJB;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataUpdateEvent {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private ClientEjb clientEjb;

    @EJB
    private TransactionEjb transactionEjb;

    @EJB
    private ExchangeRateEjb exchangeRateEjb;

    private static final Logger logger = LoggerFactory.getLogger(DataUpdateEvent.class);

    public void onDataUpdate(@Observes DataUpdateEvent dataUpdatedEvent) {
        writeClients();
        writeTransactions();
        writeExchangeRate();
    }

    private void writeExchangeRate() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ExchangeRate> qq = cb.createQuery(ExchangeRate.class);
        Root<ExchangeRate> root = qq.from(ExchangeRate.class);
        TypedQuery<ExchangeRate> query = em.createQuery(qq.select(root));
        List<ExchangeRate> results = query.getResultList();
        List<ExchangeRateTo> resultsTo = new ArrayList<>();
        for (ExchangeRate result : results) {
            resultsTo.add(UtilToBuild.buildTo(result));
        }
        try {
            FileWriter csvWriter = new FileWriter("exchange.csv");
            csvWriter.append(exchangeRateEjb.asCsv(resultsTo));
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeTransactions() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Transaction> qq = cb.createQuery(Transaction.class);
        Root<Transaction> root = qq.from(Transaction.class);
        TypedQuery<Transaction> query = em.createQuery(qq.select(root));
        List<Transaction> results = query.getResultList();
        List<TransactionTo> resultsTo = new ArrayList<>();
        for (Transaction result : results) {
            resultsTo.add(UtilToBuild.buildTo(result));
        }
        try {
            FileWriter csvWriter = new FileWriter("transactions.csv");
            csvWriter.append(transactionEjb.asCsv(resultsTo));
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeClients() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Client> qq = cb.createQuery(Client.class);
        Root<Client> root = qq.from(Client.class);
        TypedQuery<Client> query = em.createQuery(qq.select(root));
        List<Client> results = query.getResultList();
        List<ClientTo> resultsTo = new ArrayList<>();
        for (Client result : results) {
            resultsTo.add(UtilToBuild.buildTo(result));
        }
        try {
            FileWriter csvWriter = new FileWriter("clients.csv");
            csvWriter.append(clientEjb.asCsv(resultsTo));
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

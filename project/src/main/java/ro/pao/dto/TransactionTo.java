package ro.pao.dto;

import ro.pao.entities.Client;
import ro.pao.entities.ExchangeRate;

import java.util.Date;

public class TransactionTo {

    private Long id;

    private Date date;

    private ExchangeRate exchangeRate;

    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ExchangeRate getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(ExchangeRate exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}

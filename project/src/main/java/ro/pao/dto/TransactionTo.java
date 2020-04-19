package ro.pao.dto;

import ro.pao.entities.Client;
import ro.pao.entities.ExchangeRate;

import java.util.Date;

public class TransactionTo {

    private Long id;

    private Date date;

    private ExchangeRateTo exchangeRate;

    private ClientTo client;

    private Double amount;

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

    public ExchangeRateTo getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(ExchangeRateTo exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public ClientTo getClient() {
        return client;
    }

    public void setClient(ClientTo client) {
        this.client = client;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionTo{" +
                "id=" + id +
                ", date=" + date +
                ", exchangeRate=" + exchangeRate +
                ", client=" + client +
                ", amount=" + amount +
                '}';
    }
}

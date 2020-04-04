package ro.pao.dto;

import ro.pao.constants.EnumApplicationCurrency;

import java.util.Date;

public class ExchangeRateTo {
    private Long id;

    private Double rate;

    private String provider;

    private Date publishingDate;

    private Date queryDate;

    private EnumApplicationCurrency reference;

    private EnumApplicationCurrency to;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Date getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(Date publishingDate) {
        this.publishingDate = publishingDate;
    }

    public EnumApplicationCurrency getReference() {
        return reference;
    }

    public void setReference(EnumApplicationCurrency reference) {
        this.reference = reference;
    }

    public EnumApplicationCurrency getTo() {
        return to;
    }

    public void setTo(EnumApplicationCurrency to) {
        this.to = to;
    }

    public Date getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(Date queryDate) {
        this.queryDate = queryDate;
    }

    @Override
    public String toString() {
        return "ExchangeRateTo{" +
                "id=" + id +
                ", rate=" + rate +
                ", provider='" + provider + '\'' +
                ", publishingDate=" + publishingDate +
                ", queryDate=" + queryDate +
                ", reference=" + reference +
                ", to=" + to +
                '}';
    }
}

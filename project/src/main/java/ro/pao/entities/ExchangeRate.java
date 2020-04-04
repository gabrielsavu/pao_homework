package ro.pao.entities;

import ro.pao.constants.EnumApplicationCurrency;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ExchangeRate extends BasicEntity {

    private static final long serialVersionUID = -4129046469312865037L;

    private Long id;

    private Double rate;

    private String provider;

    private Date publishingDate;

    private Date queryDate;

    private EnumApplicationCurrency reference;

    private EnumApplicationCurrency to;

    private Boolean automated;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_exchange_rate")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Column(nullable = false, length = 50)
    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(Date date) {
        this.publishingDate = date;
    }

    @Enumerated(EnumType.STRING)
    public EnumApplicationCurrency getReference() {
        return reference;
    }

    public void setReference(EnumApplicationCurrency reference) {
        this.reference = reference;
    }

    @Enumerated(EnumType.STRING)
    public EnumApplicationCurrency getTo() {
        return to;
    }

    public void setTo(EnumApplicationCurrency to) {
        this.to = to;
    }

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(Date queryDate) {
        this.queryDate = queryDate;
    }

    @Column(nullable = false)
    public Boolean getAutomated() {
        return automated;
    }

    public void setAutomated(Boolean automated) {
        this.automated = automated;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "id=" + id +
                ", rate=" + rate +
                ", provider='" + provider + '\'' +
                ", publishingDate=" + publishingDate +
                ", queryDate=" + queryDate +
                ", reference=" + reference +
                ", to=" + to +
                ", automated=" + automated +
                '}';
    }
}

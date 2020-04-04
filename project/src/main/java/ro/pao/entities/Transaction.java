package ro.pao.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transaction extends BasicEntity {

    private static final long serialVersionUID = 6776432113830959838L;

    private Long id;

    private Date date;

    private ExchangeRate exchangeRate;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_transaction")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exchange_rate_id", nullable = false, foreignKey = @ForeignKey(name = "fk_exchange_rate"))
    public ExchangeRate getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(ExchangeRate exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}

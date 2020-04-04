package ro.pao.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


@StaticMetamodel(Transaction.class)
public abstract class Transaction_ {
    public static volatile SingularAttribute<ExchangeRate, Long> id;
    public static volatile SingularAttribute<ExchangeRate, Double> rate;
    public static volatile SingularAttribute<ExchangeRate, ExchangeRate> exchangeRate;
}

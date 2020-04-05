package ro.pao.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


@StaticMetamodel(Transaction.class)
public abstract class Transaction_ {
    public static volatile SingularAttribute<Transaction, Long> id;
    public static volatile SingularAttribute<Transaction, Double> rate;
    public static volatile SingularAttribute<Transaction, ExchangeRate> exchangeRate;
    public static volatile SingularAttribute<Transaction, Client> client;
}

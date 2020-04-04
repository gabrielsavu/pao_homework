package ro.pao.entities;

import ro.pao.constants.EnumApplicationCurrency;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@StaticMetamodel(ExchangeRate.class)
public abstract class ExchangeRate_ {

    public static volatile SingularAttribute<ExchangeRate, Long> id;
    public static volatile SingularAttribute<ExchangeRate, Double> rate;
    public static volatile SingularAttribute<ExchangeRate, String> provider;
    public static volatile SingularAttribute<ExchangeRate, Date> publishingDate;
    public static volatile SingularAttribute<ExchangeRate, Date> queryDate;
    public static volatile SingularAttribute<ExchangeRate, EnumApplicationCurrency> reference;
    public static volatile SingularAttribute<ExchangeRate, EnumApplicationCurrency> to;

}

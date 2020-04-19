package ro.pao.entities;

import javax.persistence.metamodel.SingularAttribute;

public abstract class Client_ {
    public static volatile SingularAttribute<Client, Long> id;
    public static volatile SingularAttribute<Client, String> cnp;
    public static volatile SingularAttribute<Client, String> firstName;
    public static volatile SingularAttribute<Client, String> lastName;
}

package ro.pao.entities;

import javax.persistence.*;

@Entity
public class Client extends BasicEntity {

    private static final long serialVersionUID = 8809988971178165533L;

    private Long id;

    private String cnp;

    private String firstName;

    private String lastName;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_client")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, unique = true)
    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    @Column(nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}


package ro.pao.dto;

import ro.pao.entities.Client;

public class ClientTo {
    private Long id;

    private String cnp;

    private String firstName;

    private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "ClientTo{" +
                "id=" + id +
                ", cnp='" + cnp + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public String asCsvEntry() {
        return id +
                "," +
                cnp +
                "," +
                firstName +
                "," +
                lastName +
                "\n";
    }

    static public String asCsvHeader() {
        return "id" +
                "," +
                "cnp" +
                "," +
                "firstName" +
                "," +
                "lastName\n";
    }
}

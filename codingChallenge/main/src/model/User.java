package model;

import java.util.Date;
import java.util.UUID;

public class User {
    private UUID id;
    private String firstname;
    private String surname;
    private String email;
    private Date birthdate;
    private String city;

    public User() {};

    public User(UUID id, String firstname, String surname, String email, Date birthdate, String city) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
        this.birthdate = birthdate;
        this.city = city;
    }

    public UUID getId() {
        return this.id;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getEmail() {
        return this.email;
    }

    public Date getBirthdate() {
        return this.birthdate;
    }

    public String getCity() {
        return this.city;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setCity(String city) {
        this.city = city;
    }


}



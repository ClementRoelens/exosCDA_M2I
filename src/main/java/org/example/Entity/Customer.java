package org.example.Entity;

import org.example.Exception.CustomFormatException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Customer {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private List<Event> eventsTicket;


    // Créé depuis l'IHM
    public Customer(String firstname, String lastname, String email) throws CustomFormatException {
        this.firstname = firstname;
        this.lastname = lastname;
        setEmail(email);
    }

    // Reçu depuis la DB
    public Customer(int id, String firstname, String lastname, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.eventsTicket = new ArrayList<>();
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws CustomFormatException {
        String pattern = "^([a-zA-Z0-9_.-]+)@([a-z0-9-]+\\.?[a-z0-9-]+)\\.([a-z]{2,6})$";
        if(!Pattern.matches(pattern, email)) {
            throw new CustomFormatException("Not correct email");
        }
        this.email = email;
    }

    public List<Event> getEventsTicket() {
        return eventsTicket;
    }

    public void setEventsTicket(List<Event> eventsTicket) {
        this.eventsTicket = eventsTicket;
    }
}

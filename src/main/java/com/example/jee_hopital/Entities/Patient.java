package com.example.jee_hopital.Entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Arrays;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstname;
    private String lastname;
    private Date birthdate;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] picture;

    public Patient() {
    }

    public Patient(String firstname, String lastname, Date birthdate, byte[] picture) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.picture = picture;
    }

    public Patient(int id, String firstname, String lastname, Date birthdate, byte[] picture) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.picture = picture;
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}

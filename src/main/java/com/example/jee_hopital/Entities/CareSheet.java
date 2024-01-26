package com.example.jee_hopital.Entities;

import javax.persistence.*;

@Entity
public class CareSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String care;
    private int duration;
    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;


    public CareSheet() {
    }

    public CareSheet(String care, int duration, Appointment appointment) {
        this.care = care;
        this.duration = duration;
        this.appointment = appointment;
    }

    public CareSheet(int id, String care, int duration, Appointment appointment) {
        this.id = id;
        this.care = care;
        this.duration = duration;
        this.appointment = appointment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCare() {
        return care;
    }

    public void setCare(String care) {
        this.care = care;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public String toString() {
        return "CareSheet{" +
                "id=" + id +
                ", care='" + care + '\'' +
                ", duration=" + duration +
                '}';
    }
}

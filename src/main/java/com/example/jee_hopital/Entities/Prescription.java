package com.example.jee_hopital.Entities;

import javax.persistence.*;

@Entity
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String drug;
    private int duration;
    @ManyToOne
    @JoinColumn(name="appointment_id")
    private Appointment appointment;

    public Prescription() {
    }

    public Prescription(String drug, int duration, Appointment appointment) {
        this.drug = drug;
        this.duration = duration;
        this.appointment = appointment;
    }

    public Prescription(int id, String drug, int duration, Appointment appointment) {
        this.id = id;
        this.drug = drug;
        this.duration = duration;
        this.appointment = appointment;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
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
        return "Prescription{" +
                "id=" + id +
                ", drug='" + drug + '\'' +
                ", duration=" + duration +
                '}';
    }
}

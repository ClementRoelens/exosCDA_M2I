package com.example.jee_hopital.Entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String physicianName;
    private Date date;
    @OneToMany(mappedBy = "prescription")
    private List<Prescription> prescriptions;
    @OneToMany(mappedBy = "prescription")
    private List<CareSheet> careSheets;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;


    public Appointment() {
    }

    public Appointment(String physicianName, Date date, Patient patient) {
        this.physicianName = physicianName;
        this.date = date;
        this.patient = patient;
    }

    public Appointment(int id, String physicianName, Date date, List<Prescription> prescriptions, List<CareSheet> careSheets,Patient patient) {
        this.id = id;
        this.physicianName = physicianName;
        this.date = date;
        this.prescriptions = prescriptions;
        this.careSheets = careSheets;
        this.patient = patient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhysicianName() {
        return physicianName;
    }

    public void setPhysicianName(String physicianName) {
        this.physicianName = physicianName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public List<CareSheet> getCareSheets() {
        return careSheets;
    }

    public void setCareSheets(List<CareSheet> careSheets) {
        this.careSheets = careSheets;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "physicianName='" + physicianName + '\'' +
                ", date=" + date +
                ", prescriptions=" + prescriptions +
                ", careSheets=" + careSheets +
                ", patient=" + patient +
                '}';
    }
}

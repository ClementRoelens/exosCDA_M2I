package com.example.jee_hopital.Service;

import com.example.jee_hopital.Entities.Appointment;
import com.example.jee_hopital.Entities.Patient;
import com.example.jee_hopital.Entities.User;
import com.example.jee_hopital.Repository.AppointmentDAOImpl;
import com.example.jee_hopital.Repository.PatientDAOImpl;
import com.example.jee_hopital.Repository.UserDAOImpl;

import java.util.List;

public class Service {
    private UserDAOImpl userDAO;
    private PatientDAOImpl patientDAO;
    private AppointmentDAOImpl appointmentDAO;


    public Service(){
        userDAO = new UserDAOImpl();
        patientDAO = new PatientDAOImpl();
        appointmentDAO = new AppointmentDAOImpl();
    }

    public User createUser(User user){
        return userDAO.create(user);
    }

    public boolean signin(User user){
        User foundUser = userDAO.readByName(user.getName());
        if (foundUser == null){
            return false;
        }
        return (user.getPassword().equals(foundUser.getPassword()));
    }

    public Patient createPatient(Patient patient){ return patientDAO.create(patient); }
    public List<Patient> getPatients(){ return patientDAO.read(); }
    public Patient getPatient(int id){ return patientDAO.read(id); }
    public List<Patient> seekPatientsByName(String name){
        return patientDAO.readByName(name);
    }
    public Appointment createAppointment(Appointment appointment){ return appointmentDAO.create(appointment); }
    public Appointment getAppointment(int id){ return appointmentDAO.read(id); }
    public boolean updateAppointment(Appointment appointment){
        Appointment a = appointmentDAO.read(appointment.getId());
        if (a != null){
            return appointmentDAO.update(appointment);
        }
        return false;
    }

    public void close(){
        userDAO.close();
        patientDAO.close();
        appointmentDAO.close();
    }
}

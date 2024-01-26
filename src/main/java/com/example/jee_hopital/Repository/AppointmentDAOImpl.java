package com.example.jee_hopital.Repository;

import com.example.jee_hopital.Entities.Appointment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class AppointmentDAOImpl implements IDAO<Appointment> {

    private StandardServiceRegistry standardServiceRegistry;
    private SessionFactory sessionFactory;


    public AppointmentDAOImpl(){
        standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory();
    }


    @Override
    public Appointment create(Appointment appointment) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        try {
            session.persist(appointment);
//            session.merge(appointment.getPatient());
            transaction.commit();
            return appointment;
        } catch (Exception e){
            if (transaction.isActive()){
                transaction.rollback();
            }
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public Appointment read(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        try {
            return session.get(Appointment.class,id);
        } catch (Exception e){
            if (transaction.isActive()){
                transaction.rollback();
            }
            return null;
        } finally {
            session.close();
        }
    }

    public List<Appointment> readAllFromAPatient(int patientId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        Query<Appointment> query;
        List<Appointment> appointments = new ArrayList<>();

        transaction.begin();

        try {
            query = session.createQuery("FROM Appointment WHERE patient_id = :patientId");
            query.setParameter("patientId", patientId);
            appointments = query.list();
            transaction.commit();
        } catch (Exception e){
            if (transaction.isActive()){
                transaction.rollback();
            }
        } finally {
            session.close();
        }

        return appointments;
    }

    public boolean update(Appointment appointment){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        try {
            session.update(appointment);
            transaction.commit();
            return true;
        } catch (Exception e){
            if (transaction.isActive()){
                transaction.rollback();
            }
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public void close() {
        sessionFactory.close();
    }
}

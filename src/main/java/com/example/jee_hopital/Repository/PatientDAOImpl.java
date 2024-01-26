package com.example.jee_hopital.Repository;

import com.example.jee_hopital.Entities.Patient;
import com.example.jee_hopital.Entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PatientDAOImpl implements IDAO<Patient> {
    private StandardServiceRegistry standardServiceRegistry;
    private SessionFactory sessionFactory;


    public PatientDAOImpl(){
        standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory();
    }


    @Override
    public Patient create(Patient patient) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        try {
            session.persist(patient);
            transaction.commit();
            return patient;
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
    public Patient read(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        try {
            return session.get(Patient.class,id);
        } catch (Exception e){
            if (transaction.isActive()){
                transaction.rollback();
            }
            return null;
        } finally {
            session.close();
        }
    }

    public List<Patient> read(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        Query<Patient> query;
        List<Patient> patients = new ArrayList<>();

        transaction.begin();

        try {
            query = session.createQuery("FROM Patient");
            patients = query.list();
            transaction.commit();
        } catch (Exception e){
            if (transaction.isActive()){
                transaction.rollback();
            }
        } finally {
            session.close();
        }

        return patients;
    }

    public List<Patient> readByName(String name){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        Query<Patient> query;
        List<Patient> patients = new ArrayList<>();

        transaction.begin();

        try {
            query = session.createQuery("FROM Patient WHERE lastname = :name");
            query.setParameter("name",name);
            patients = query.list();
            transaction.commit();
        } catch (Exception e){
            if (transaction.isActive()){
                transaction.rollback();
            }
        } finally {
            session.close();
        }

        return patients;
    }

    @Override
    public void close() {
        sessionFactory.close();
    }
}

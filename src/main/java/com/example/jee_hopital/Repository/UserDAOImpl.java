package com.example.jee_hopital.Repository;

import com.example.jee_hopital.Entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl implements IDAO<User> {
    private StandardServiceRegistry standardServiceRegistry;
    private SessionFactory sessionFactory;


    public UserDAOImpl(){
        standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory();
    }



    @Override
    public User create(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        try {
            session.persist(user);
            transaction.commit();
            return user;
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
    public User read(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        try {
            return session.get(User.class,id);
        } catch (Exception e){
            if (transaction.isActive()){
                transaction.rollback();
            }
            return null;
        } finally {
            session.close();
        }
    }

    public User readByName(String name){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        Query<User> query;

        transaction.begin();

        try {
            query = session.createQuery("FROM User WHERE name = :name");
            query.setParameter("name",name);
            return query.getSingleResult();
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
    public void close() {
        sessionFactory.close();
    }
}

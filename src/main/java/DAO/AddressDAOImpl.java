package DAO;

import Entities.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class AddressDAOImpl implements IDAO<Address> {
    private StandardServiceRegistry standardServiceRegistry;
    private SessionFactory sessionFactory;


    public AddressDAOImpl(){
        standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory();
    }



    @Override
    public Address create(Address address) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            session.save(address);
            session.getTransaction().commit();
            return address;
        }catch (Exception e){
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            System.out.println(e);
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Address> read() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Address> addresses = new ArrayList<>();
        Query<Address> query;

        try {
            query = session.createQuery("FROM Address");
            addresses = query.list();
            session.getTransaction().commit();
        }catch (Exception e){
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            System.out.println(e);
        } finally {
            session.close();
        }

        return addresses;
    }

    @Override
    public Address read(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            return session.get(Address.class,id);
        }catch (Exception e){
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            System.out.println(e);
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Address address) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            session.update(address);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            System.out.println(e);
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(Address address) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            session.delete(address);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            System.out.println(e);
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

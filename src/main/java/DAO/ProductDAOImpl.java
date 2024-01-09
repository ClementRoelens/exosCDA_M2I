package DAO;

import Entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements IDAO<Product> {

    private static ProductDAOImpl instance = null;
    private static Object lock = new Object();
    private StandardServiceRegistry standardServiceRegistry;
    private SessionFactory sessionFactory;


    private ProductDAOImpl(){
        standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory();
    }


    public static ProductDAOImpl getInstance(){
        if (instance == null){
            synchronized (lock){
                instance = new ProductDAOImpl();
            }
        }
        return instance;
    }



    @Override
    public Product create(Product product) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            session.save(product);
            session.getTransaction().commit();
            return product;
        } catch (Exception e){
            System.out.println(e);
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Product> read() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Product> products = new ArrayList<>();

        try {
            products = session.createQuery("FROM Product").list();
            session.getTransaction().commit();
        } catch (Exception e){
            System.out.println(e);
        } finally {
            session.close();
        }

        return products;
    }

    @Override
    public Product read(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            return session.get(Product.class,id);
        } catch (Exception e){
            System.out.println(e);
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Product product) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            session.update(product);
            session.getTransaction().commit();
            return true;
        } catch (Exception e){
            System.out.println(e);
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(Product product) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            session.delete(product);
            session.getTransaction().commit();
            return true;
        } catch (Exception e){
            System.out.println(e);
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public void close(){
        sessionFactory.close();
    }
}

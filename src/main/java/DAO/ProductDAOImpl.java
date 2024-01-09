package DAO;

import Entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.sql.Date;
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
    public List<Product> read() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Product> products = new ArrayList<>();

        try {
            products = session.createQuery("FROM Product").list();
            session.getTransaction().commit();
        } catch (Exception e){
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
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
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            System.out.println(e);
            return null;
        } finally {
            session.close();
        }
    }

    public List<Product> readBetweenDates(Date dateOne, Date dateTwo){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Product> products = new ArrayList<>();
        Query<Product> query;

        try {
            query = session.createQuery("FROM Product WHERE buyDate BETWEEN :dateOne AND :dateTwo");
            query.setParameter("dateOne", dateOne);
            query.setParameter("dateTwo", dateTwo);
            products = query.list();
            session.getTransaction().commit();
        } catch (Exception e){
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            System.out.println(e);
        } finally {
            session.close();
        }

        return products;
    }

    public List<Product> readWhereStockLowerThan(int stockLimit){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Product> products = new ArrayList<>();
        Query<Product> query;

        try {
            query = session.createQuery("FROM Product WHERE stock < :stock");
            query.setParameter("stock", stockLimit);
            products = query.list();
            session.getTransaction().commit();
        } catch (Exception e){
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            System.out.println(e);
        } finally{
            session.close();
        }

        return products;
    }

    public double readTotalValueFromMark(String mark){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Double> query;

        try {
            query = session.createQuery("SELECT SUM(stock*price) FROM Product WHERE mark = :mark");
            query.setParameter("mark", mark);
            return query.uniqueResult();
        } catch (Exception e){
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            System.out.println(e);
            return -1;
        } finally {
            session.close();
        }
    }

    public List<Product> readFromOneMark(String mark){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Product> query;
        List<Product> products = new ArrayList<>();

        try {
            query = session.createQuery("FROM Product WHERE mark = :mark");
            query.setParameter("mark", mark);
            products = query.list();
        }catch (Exception e){
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            System.out.println(e);
        } finally {
            session.close();
        }

        return products;
    }

    public double readAveragePrice(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Double> query;

        try {
            query = session.createQuery("SELECT AVG(price) FROM Product");
            return query.uniqueResult();
        } catch (Exception e){
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            System.out.println(e);
            return -1D;
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
    public boolean delete(Product product) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            session.delete(product);
            session.getTransaction().commit();
            return true;
        } catch (Exception e){
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            System.out.println(e);
            return false;
        } finally {
            session.close();
        }
    }

    public int deleteAllFromOneMark(String mark){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query;

        try {
            query = session.createQuery("DELETE Product WHERE mark = :mark");
            query.setParameter("mark", mark);
            return query.executeUpdate();
        } catch (Exception e){
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            System.out.println(e);
            return -1;
        } finally {
            session.close();
        }
    }

    @Override
    public void close(){
        sessionFactory.close();
    }
}

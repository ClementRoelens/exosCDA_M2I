package DAO;

import Entities.Image;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ImageDAOImpl implements IDAO<Image>{
    private StandardServiceRegistry standardServiceRegistry;
    private SessionFactory sessionFactory;


    public ImageDAOImpl(){
        standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory();
    }



    @Override
    public Image create(Image image) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            session.save(image);
            session.getTransaction().commit();
            return image;
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
    public List<Image> read() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Image> images = new ArrayList<>();
        Query<Image> query;

        try {
            query = session.createQuery("FROM Image");
            images = query.list();
        } catch (Exception e){
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            System.out.println(e);
        } finally {
            session.close();
        }

        return images;
    }

    @Override
    public Image read(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            return session.get(Image.class,id);
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
    public boolean update(Image image) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            session.update(image);
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
    public boolean delete(Image image) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            session.delete(image);
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
    public void close() {
        sessionFactory.close();
    }
}

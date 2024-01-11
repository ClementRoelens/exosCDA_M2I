package DAO;

import Entities.Comment;
import Entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CommentDAOImpl implements IDAO<Comment> {
    private StandardServiceRegistry standardServiceRegistry;
    private SessionFactory sessionFactory;


    public CommentDAOImpl(){
        standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory();
    }





    @Override
    public Comment create(Comment comment) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try{
            session.save(comment);
            session.getTransaction().commit();
            return comment;
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
    public List<Comment> read() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Comment> comments = new ArrayList<>();
        Query<Comment> query;

        try{
            query = session.createQuery("FROM Comment");
            comments = query.list();
            session.getTransaction().commit();
        } catch (Exception e){
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            System.out.println(e);
        } finally {
            session.close();
        }

        return comments;
    }

    @Override
    public Comment read(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try{
            return session.get(Comment.class,id);
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

    public List<Comment> readCommentsWithRatingHigherThan(int threshold){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Comment> query;
        List<Comment> comments = new ArrayList<>();

        try {
            query = session.createQuery("FROM Comment WHERE rating >= :threshold");
            query.setParameter("threshold",threshold);
            comments = query.list();
            session.getTransaction().commit();
        }catch (Exception e){
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            System.out.println(e);
        }

        return comments;
    }

    public List<Comment> readAllAboutOneProduct(int productId){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Comment> comments = new ArrayList<>();
        Query<Comment> query;

        try {
            query = session.createQuery("FROM Comment WHERE product_id = :productId");
            query.setParameter("productId",productId);
            comments = query.list();
            session.getTransaction().commit();
        } catch (Exception e){
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            System.out.println(e);
        } finally {
            session.close();
        }

        return comments;
    }

    @Override
    public boolean update(Comment comment) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try{
            session.update(comment);
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
    public boolean delete(Comment comment) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try{
            session.delete(comment);
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

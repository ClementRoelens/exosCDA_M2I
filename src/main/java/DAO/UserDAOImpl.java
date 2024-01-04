package DAO;

import Entity.User;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends BaseDAO<User> {
    @Override
    public User create(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            em.persist(user);
            em.getTransaction().commit();
            return user;
        } catch (Exception e) {
            System.out.println(e);
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<User> read() {
        EntityManager em = emf.createEntityManager();
        List<User> users = new ArrayList<>();
        em.getTransaction().begin();

        try {
            users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        } catch (Exception e){
            System.out.println(e);
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }

        return users;
    }

    @Override
    public User read(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            return em.find(User.class, id);
        } catch (Exception e){
            System.out.println(e);
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean update(User object) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            User user = em.find(User.class,id);
            em.remove(user);
            em.getTransaction().commit();
            return true;
        } catch (Exception e){
            System.out.println(e);
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }
}

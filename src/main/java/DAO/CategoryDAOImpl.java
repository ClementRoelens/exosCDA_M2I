package DAO;

import Entity.Category;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl extends BaseDAO<Category> {
    @Override
    public Category create(Category category) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            em.persist(category);
            em.getTransaction().commit();
            return category;
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
    public List<Category> read() {
        EntityManager em = emf.createEntityManager();
        List<Category> categories = new ArrayList<>();
        em.getTransaction().begin();

        try {
            categories = em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }

        return categories;
    }

    @Override
    public Category read(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            return em.find(Category.class, id);
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
    public boolean update(Category category) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            em.merge(category);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean delete(int id) {
        EntityManager em = emf.createEntityManager();
        Category category;
        em.getTransaction().begin();

        try {
            category = em.find(Category.class, id);
            em.remove(category);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
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

package DAO;

import Entity.ToDo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ToDoDAO extends BaseDAO<ToDo> {

    @Override
    public ToDo create(ToDo todo) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        ToDo newTodo = new ToDo(todo.getName());
        em.persist(newTodo);
        em.getTransaction().commit();

        em.close();
        return newTodo;
    }

    @Override
    public List<ToDo> read() {
        List<ToDo> todos;
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        todos = em.createQuery("SELECT t FROM ToDo t", ToDo.class).getResultList();

        em.close();
        return todos;
    }

    @Override
    public ToDo read(int id){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        return em.find(ToDo.class, id);
    }

    @Override
    public boolean update(ToDo todo) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        ToDo updatedTodo = em.find(ToDo.class, todo.getId());
        updatedTodo.setName(todo.getName());
        updatedTodo.setDone(todo.isDone());
        em.getTransaction().commit();

        em.close();
        return true;
    }

    @Override
    public boolean delete(int id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        ToDo removedTodo = em.find(ToDo.class, id);
        em.remove(removedTodo);
        em.getTransaction().commit();

        em.close();
        return true;
    }
}

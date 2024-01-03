package DAO;

import Entity.ToDo;
import Entity.TodoInfos;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class ToDoDAOImpl extends BaseDAO<ToDo> {

    @Override
    public ToDo create(ToDo todo) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            ToDo newTodo = new ToDo(todo.getName());
            newTodo.setTodoInfos(todo.getTodoInfos());

            em.persist(newTodo);

            em.getTransaction().commit();
            return newTodo;
        } catch (Exception e){
            System.out.println(e);
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<ToDo> read() {
        List<ToDo> todos = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            todos = em.createQuery("SELECT t FROM ToDo t", ToDo.class).getResultList();
        } catch (Exception e){
            System.out.println(e);
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }

        return todos;
    }

    @Override
    public ToDo read(int id){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            return em.find(ToDo.class, id);
        } catch (Exception e){
            System.out.println(e);
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean update(ToDo todo) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            ToDo updatedTodo = em.find(ToDo.class, todo.getId());
            updatedTodo.setName(todo.getName());
            updatedTodo.setDone(todo.isDone());
            TodoInfos infos = todo.getTodoInfos();
            updatedTodo.setTodoInfos(infos);

            em.getTransaction().commit();
            return true;
        } catch (Exception e){
            System.out.println(e);
            if (em.getTransaction().isActive()){
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
        em.getTransaction().begin();

        try {
            ToDo removedTodo = em.find(ToDo.class, id);
            em.remove(removedTodo);
            em.getTransaction().commit();
            return true;
        }catch (Exception e){
            System.out.println(e);
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }
}

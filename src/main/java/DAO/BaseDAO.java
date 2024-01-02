package DAO;

import Entity.ToDo;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public abstract class BaseDAO<T> {
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp_1");

    public abstract T create(T object);
    public abstract List<T> read();
    public abstract  T read(int id);
    public abstract boolean update(T object);
    public abstract boolean delete(int id);
}

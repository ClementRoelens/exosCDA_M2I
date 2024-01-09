package DAO;

import java.util.List;

interface IDAO<T> {
    T create (T object);
    List<T> read ();
    T read(int id);
    boolean update(T object);
    boolean delete(T object);
    void close();
}

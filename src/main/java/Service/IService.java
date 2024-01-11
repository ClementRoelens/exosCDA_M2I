package Service;

import java.util.List;

public interface IService<T> {
    T post(T object);
    List<T> getAll();
    T getOne(int id);
    boolean update(T object);
    boolean delete(int id);
    void close();
}

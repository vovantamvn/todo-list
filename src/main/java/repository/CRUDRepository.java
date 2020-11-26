package repository;

import java.util.List;

public interface CRUDRepository<T, R> {
    boolean save(T t);
    boolean update(T t, R r);
    void delete(R r);
    T findByID(R r);
    List<T> findAll();
}

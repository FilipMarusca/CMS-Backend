package repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Raul on 24/04/2017.
 */
public interface IRepository<T> {

    void add(T entity);

    default Serializable save(T entity) {
        return null;
    }

    void delete(Integer key);

    List<T> getAll();

    T findById(Integer key);

    void update(Integer key, T newEntity);

    default void update(T entity) {

    }
}
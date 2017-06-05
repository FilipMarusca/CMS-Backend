package repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Raul on 24/04/2017.
 */
public interface IRepository<T> {

    /**
     * @param entity The entity to add to the repository
     *               Note that if it has an id, an update will be performed
     */
    void add(T entity);

    /**
     *
     * @param entity The entity to save
     * @return The id of he entity
     */
    Serializable save(T entity);

    /**
     * Delete
     *
     * @param key The identifier of the object to delete
     */
    void delete(Serializable key);

    /**
     *
     * @return All the objects managed by this DAO
     */
    List<T> getAll();

    /**
     * @param id The id of the object to return
     * @return The object with the given id
     */
    T findById(Serializable id);

    /**
     *
     * @param entity The entity to save
     */
    void update(T entity);
}
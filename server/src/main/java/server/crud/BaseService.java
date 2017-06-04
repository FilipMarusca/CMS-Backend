package server.crud;

import repository.IRepository;
import server.validator.ValidatorInterface;
import service.exception.ServiceException;

import java.io.Serializable;
import java.util.List;

/**
 * @author Marius Adam
 */
public abstract class BaseService<T> {
    /**
     * Do not use it directly, call this.validate instead from child class
     */
    private final ValidatorInterface validator;

    BaseService(ValidatorInterface validator) {
        this.validator = validator;
    }

    public void add(T entity) throws ServiceException {
        save(entity);
    }

    public void delete(Integer id) {
        getRepository().delete(id);
    }

    /**
     * @param entity The object to save
     * @return The id of the entity
     */
    public Serializable save(T entity) throws ServiceException {
        validate(entity);
        return getRepository().save(entity);
    }

    public List<T> getAll() {
        List<T> all = getRepository().getAll();
        return all;
    }

    public T findById(int key) {
        return getRepository().findById(key);
    }

    public void update(T entity) {
        getRepository().update(entity);
    }


    void validate(T obj) throws ServiceException {
        validator.validate(obj);
    }

    abstract IRepository<T> getRepository();
}

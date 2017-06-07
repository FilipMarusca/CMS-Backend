package repository;

import javafx.util.Callback;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

public class AbstractRepository<T> implements IRepository<T> {
    protected static final Logger logger = Logger.getLogger(AbstractRepository.class.getName());
    protected final Class<T>       managedEntity;
    protected       SessionFactory sessionFactory;

    /**
     * @param sessionFactory The session factory
     * @param managedEntity  The entity runtime class for which this DAO is created
     */
    public AbstractRepository(SessionFactory sessionFactory, Class<T> managedEntity) {
        this.sessionFactory = sessionFactory;
        this.managedEntity = managedEntity;
    }

    /**
     *
     * @param entity The entity to add to the repository
     *               Note that if it has an id, an update will be performed
     */
    @Override
    public void add(T entity) {
        save(entity);
    }

    /**
     *
     * @param entity The entity to save
     * @return The id of he entity
     */
    @Override
    public Serializable save(T entity) {
        return executeWithRollback(session -> session.save(entity));
    }

    /**
     * Delete
     *
     * @param key The identifier of the object to delete
     */
    @Override
    public void delete(Serializable key) {
        executeWithRollback((Callback<Session, Void>) session -> {
            session.delete(session.get(managedEntity, key));
            return null;
        });
    }

    /**
     *
     * @return All the objects managed by this DAO
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() {
        logger.info("intra in get all");
        return executeWithRollback(session -> session.createCriteria(managedEntity).list());
    }

    /**
     * @param id The id of the object to return
     * @return The object with the given id
     */
    @Override
    @SuppressWarnings("unchecked")
    public T findById(Serializable id) {
        return executeWithRollback(session -> (T) session.get(managedEntity, id));
    }

    /**
     *
     * @param entity The entity to save
     */
    @Override
    public void update(T entity) {
        //save(entity);
        executeWithRollback(new Callback<Session, Void>() {
            @Override
            public Void call(Session param) {
                param.update(entity);
                return null;
            }
        });
    }

    /**
     * @param property A hibernate property path
     * @param value    The value to compare to
     * @return The matching objects of type T
     */
    @SuppressWarnings("unchecked")
    public Collection<T> findBy(String property, Object value) {
        return executeWithRollback(session -> session
                .createCriteria(managedEntity)
                .add(Restrictions.eq(property, value))
                .list());
    }

    /**
     * Executes the given job in a transaction, with rollback
     * if an exception occurs
     *
     * @param job The job to run in a transaction
     * @param <R> The result type
     * @return The callback result
     */
    protected <R> R executeWithRollback(Callback<Session, R> job) {
        Transaction transaction = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            R result = job.call(session);
            transaction.commit();
            return result;
        } catch (Throwable throwable) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw throwable;
        }
    }
}

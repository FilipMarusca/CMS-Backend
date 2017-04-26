import java.util.List;

/**
 * Created by Raul on 24/04/2017.
 */
public interface IRepository<T> {

    void add(T entity);
    void delete(int key);
    List<T> getAll();
    T findById(int key);
    void update(int key, T newEntity);
}

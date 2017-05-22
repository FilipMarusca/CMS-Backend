package repository;

import com.ubb.cms.User;

import org.hibernate.SessionFactory;


/**
 * @author Marius Adam
 */

public class UserRepositorySpring extends AbstractRepository<User> {

    public UserRepositorySpring(SessionFactory sessionFactory) {
        super(sessionFactory, User.class);
    }
}

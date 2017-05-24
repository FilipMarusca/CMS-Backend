package repository;

import com.ubb.cms.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Raul on 24/04/2017.
 */
@Repository
public class UserRepository extends AbstractRepository<User>{
    @Autowired
    public UserRepository(SessionFactory sessionFactory) {
        super(sessionFactory, User.class);
    }

    public User checkUser(String username, String password)
    {
        List<User> users = this.getAll();
        for(User user: users)
        {
            logger.info(user.toString());
            if(user.getUsername().equals(username) && user.getPassword().equals(password))
            {
                return user;
            }
        }
        return null;
    }
}

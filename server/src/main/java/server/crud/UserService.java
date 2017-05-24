package server.crud;

import com.ubb.cms.User;
import com.ubb.cms.utils.UserTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.IRepository;
import repository.UserRepository;
import server.validator.ValidatorInterface;
import service.exception.ServiceException;

import java.util.List;

/**
 * Created by Raul on 24/04/2017.
 */
@Component
public class UserService extends BaseService<User> {
    private UserRepository userRepository;

    @Autowired
    public UserService(ValidatorInterface validator, UserRepository userRepository) {
        super(validator);
        this.userRepository = userRepository;
    }

    @Override
    IRepository<User> getRepository() {
        return userRepository;
    }

    public User checkUser(User user) {
        return userRepository.checkUser(user.getUsername(), user.getPassword());
    }
}

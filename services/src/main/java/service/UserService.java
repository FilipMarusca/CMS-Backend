package service;

import com.ubb.cms.User;
import com.ubb.cms.utils.UserTag;
import repository.UserRepository;

import java.util.List;

/**
 * Created by Raul on 24/04/2017.
 */

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public void addUser(int id, String username, String password, String email, String name, String surname, UserTag tag) {
        userRepository.add(new User(id, username, password, email, name, surname, tag));
    }

    public void addUser(User user) {
        userRepository.add(user);
    }


    public void deleteUser(int key) {
        userRepository.delete(key);
    }

    public User findById(int key) {
        return userRepository.findById(key);
    }


    public void updateUser(int key, User newUser) {
        userRepository.update(key, newUser);
    }

    public User checkUser(User user) {
        return userRepository.checkUser(user.getUsername(), user.getPassword());
    }


}

package server;

import client.IConferenceClient;
import com.ubb.cms.*;
import exception.ServiceException;

import java.util.List;

/**
 * Created by Raul on 25/04/2017.
 */
public interface IConferenceServer {

    List<User> getAllUser();

    List<Edition> getAllEditions();

    String login(User user, IConferenceClient client) throws ServiceException;

    void addUser(User user) throws ServiceException;

    void updateUser(User newUser, int key) throws ServiceException;

}

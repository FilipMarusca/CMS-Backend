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

    User login(User user, IConferenceClient client) throws ServiceException;

    void logout(String username) throws ServiceException;

    void addUser(User user) throws ServiceException;

    void addPaper(Paper paper) throws ServiceException;

    void updateUser(User newUser, int key) throws ServiceException;

    List<Conference> getAllConferences();

    List<Paper> getAllPapers();

    User getUserById(int userId);

    Edition getEditionById(int editionId);



}

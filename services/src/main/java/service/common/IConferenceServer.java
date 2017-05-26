package service.common;

import com.ubb.cms.Conference;
import com.ubb.cms.Edition;
import com.ubb.cms.Paper;
import com.ubb.cms.User;
import service.exception.ServiceException;

import java.util.Date;
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

    void updateUser(User newUser) throws ServiceException;

    List<Conference> getAllConferences();

    List<Paper> getAllPapers();

    User getUserById(int userId);

    Edition getEditionById(int editionId);

    void addConference(Conference conference) throws ServiceException;

    void addEdition(Edition edition) throws ServiceException;

    List<Edition> getEditionAfterDate(Date date);


}

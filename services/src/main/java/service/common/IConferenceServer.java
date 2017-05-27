package service.common;

import com.ubb.cms.*;
import com.ubb.cms.utils.ReviewStatus;
import service.exception.ServiceException;

import java.util.Date;
import java.util.List;

/**
 * Created by Raul on 25/04/2017.
 */
public interface IConferenceServer {

    List<User> getAllUser();

    List<Edition> getAllEditions();

    List<Review> getAllReviews();

    User login(User user, IConferenceClient client) throws ServiceException;

    void logout(String username) throws ServiceException;

    void addUser(User user) throws ServiceException;

    void addPaper(Paper paper) throws ServiceException;

    void updateUser(User newUser) throws ServiceException;

    void addReview(Review review) throws ServiceException;

    List<Conference> getAllConferences();

    List<Paper> getAllPapers();

    User getUserById(int userId);

    Edition getEditionById(int editionId);
    Paper getPaperById(int paperId);
    void addConference(Conference conference) throws ServiceException;

    void addEdition(Edition edition) throws ServiceException;
    List<Review> getReviewByReviewerAndStatus(User user,ReviewStatus status);

    Conference getConferenceById(int userId);
    List<Edition> getEditionAfterDate(Date date);
}

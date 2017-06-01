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

    List<SessionChair> getAllSessionChairs();

    List<Paper> getPapersToBeReviewed(User u,ReviewStatus s);
    User login(User user, IConferenceClient client) throws ServiceException;

    void logout(String username) throws ServiceException;

    void addUser(User user) throws ServiceException;

    void addPaper(Paper paper) throws ServiceException;

    void updateUser(User newUser) throws ServiceException;

    void addReview(Review review) throws ServiceException;

    void addSessionChair(SessionChair sessionChair) throws ServiceException;

    void updatePaper(Paper newPaper) throws ServiceException;

    List<Conference> getAllConferences();

    List<Paper> getAllPapers();

    User getUserById(int userId);

    Edition getEditionById(int editionId);
    Paper getPaperById(int paperId);
    List<Paper> getPapersFromAuthor(User author);
    void addConference(Conference conference) throws ServiceException;

    Integer addEdition(Edition edition) throws ServiceException;
    List<Review> getReviewByReviewerAndStatus(User user,ReviewStatus status);

    List<Review> getReviewsByReviewer(User user);

    Conference getConferenceById(int userId);
    List<Edition> getEditionAfterDate(Date date);
    List<Paper> getPapersReviewer(User u);
    void changeReviewToConfirmedToBeReviewed(Review review);
    void changeReviewToRefusedToBeReviewed(Review review);

    void updateReview(Review review) throws ServiceException;

    Review getReviewByReviewerAndPaper(User u,Paper p);

    List<Paper> getPapersNotReviewed(User u);

    void deleteReview(Review r);

    void addParticipation(Participation participation) throws ServiceException;

}

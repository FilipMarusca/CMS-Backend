package service.common;

import com.ubb.cms.*;
import com.ubb.cms.utils.ReviewStatus;
import service.exception.ServiceException;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Raul on 25/04/2017.
 */
public interface IConferenceServer {
    void addSession(ConferenceSession s) throws ServiceException;
    List<User> getAllUser();
    List<Edition> getEditionForChair(User u);
    List<Edition> getAllEditions();
    List<ConferenceSession> getAllSessions();
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

    Integer addEdition(Edition edition, User editionCreator) throws ServiceException;
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

    /**
     * @param conference The conference for which to retrieve editions
     * @return The editions of the given conference
     * @throws ServiceException .
     */
    Collection<Edition> getEditions(Conference conference) throws ServiceException;

    /**
     * @param sessionChair The session chair which created the editions
     * @return The editions created by the given session chair
     * for which the submission deadline has passed
     */
    Collection<Edition> getPastSubmissionEditions(User sessionChair) throws ServiceException;

    /**
     *
     * @param paper The paper
     * @return The reviews for the given paper
     */
    Collection<Review> getReviews(Paper paper) throws ServiceException;

    /**
     *
     * @param edition The edition for which to retrieve papers
     * @return The papers for the given edition
     */
    Collection<Paper> getPapers(Edition edition) throws ServiceException;
}

package client;

import com.sun.javafx.stage.StageHelper;
import com.ubb.cms.*;
import com.ubb.cms.utils.PaperStatus;
import com.ubb.cms.utils.ReviewStatus;
import com.ubb.cms.utils.UserPaperEmb;
import javafx.application.Platform;
import service.common.IConferenceClient;
import service.common.IConferenceServer;
import service.common.Observer;
import service.exception.ServiceException;
import utils.DateUtils;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Raul on 26/04/2017.
 */
public class ClientController extends UnicastRemoteObject implements IConferenceClient {
    private IConferenceServer server;
    private List<Observer<?>> observers = new ArrayList<>();

    public ClientController(IConferenceServer server) throws RemoteException {
        this.server = server;
    }

    public void addUser(User user) throws ServiceException {
        server.addUser(user);
    }
    public void addSession(ConferenceSession conf) throws ServiceException{
        server.addSession(conf);
    }
    public List<ConferenceSession> getAllSessions(){
        return server.getAllSessions();
    }

    public List<User> getAllUsers() {
        return server.getAllUser();
    }

    public User getUserById(int userId) {
        return server.getUserById(userId);
    }

    public Conference getConferenceById(int userId) {
        return server.getConferenceById(userId);
    }


    public Edition getEditionById(int editionId) {
        return server.getEditionById(editionId);
    }

    public void addPaper(Paper paper) throws ServiceException {
        server.addPaper(paper);
    }


    public void addParticipation(Participation participation) throws ServiceException {
        server.addParticipation(participation);
    }


    public List<Conference> getAllConferences() {
        return server.getAllConferences();
    }

    public List<Edition> getAllEdition() {
        return server.getAllEditions();
    }


    public List<Paper> getAllPapers() {
        return server.getAllPapers();
    }

    public List<Paper> getPapersFromAuthor(User author) {
        return server.getPapersFromAuthor(author);
    }


    public void updateUser(User newUser) throws ServiceException {
        server.updateUser(newUser);
    }

    public List<Edition> getEditionAfterDate(Date date) {
        return server.getEditionAfterDate(date);
    }


    public User login(String username, String password) throws ServiceException {
        User user = new User(username, password);
        return server.login(user, this);
        /*try {

        }
        catch (ServiceException exception)
        {
            logger.info(exception.getStackTrace());
        }*/

        //user = userL;
    }
    public List<Edition> getEditionForChair(User u){
        return server.getEditionForChair(u);
    }
    public void logout(String username) throws ServiceException {
        try {
            server.logout(username);
        } catch (ServiceException ex) {
            throw new ServiceException("Err");
        }
    }


    @Override
    public void showUpdated() throws RemoteException {
        //logger.info(updateType);
        Platform.runLater(this::notifyObservers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void ping() throws RemoteException {
        if (StageHelper.getStages().size() == 0) {
            System.exit(0);
        }
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {

    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public List<Review> getReviewByReviewerAndStatus(User user, ReviewStatus status) {
        return server.getReviewByReviewerAndStatus(user, status);
    }

    public void addConference(String conferenceName) throws ServiceException {
        server.addConference(new Conference(conferenceName));
    }

    public List<Paper> getPapersToBeReviewed(User u, ReviewStatus s) {
        return server.getPapersToBeReviewed(u, s);
    }

    public List<Paper> getPapersNotReviewed(User u) {
        return server.getPapersNotReviewed(u);
    }

    public Integer addEdition(
            Conference conference,
            LocalDate beginningDate,
            LocalDate endingDate,
            String name,
            LocalDate paperSubmissionDeadline,
            LocalDate finalDeadline,
            User loggedUser
    ) throws ServiceException {
        Edition edition = new Edition(
                conference,
                DateUtils.asDate(beginningDate),
                DateUtils.asDate(endingDate),
                name,
                DateUtils.asDate(paperSubmissionDeadline),
                DateUtils.asDate(finalDeadline)
        );

        return server.addEdition(edition, loggedUser);
    }


    public List<Review> getAllReviews() {
        return server.getAllReviews();
    }


    public void addReview(User user, Paper paper, ReviewStatus status, String comment) throws ServiceException {
        Review review = new Review(new UserPaperEmb(user, paper), status, comment);
        server.addReview(review);

    }

    public Paper getPaperById(int id) throws ServiceException {
        return server.getPaperById(id);

    }

    public void deleteReview(Review r) {
        server.deleteReview(r);
    }

    public Review getReviewByReviewerAndPaper(User u, Paper p) {
        return server.getReviewByReviewerAndPaper(u, p);
    }

    public void updateReview(User user, Paper paper, ReviewStatus status, String comment) throws ServiceException {
        Review review = new Review(new UserPaperEmb(user, paper), status, comment);
        server.updateReview(review);
    }


    public void updateReview(Review review) throws ServiceException {

        server.updateReview(review);
    }


    public void updatePaper(Paper newPaper) throws ServiceException {
        server.updatePaper(newPaper);
    }


    public synchronized List<SessionChair> getAllSessionChairs() {
        return server.getAllSessionChairs();
    }


    public void addSessionChair(SessionChair sessionChair) throws ServiceException {
        server.addSessionChair(sessionChair);
    }
    public List<Paper> getAllPapersFromEndedConferenceByChair(User u){
        List<Paper> lista=new ArrayList<>();
        for (Paper p:server.getAllPapers()
             ) {
            for (Edition e:
                 server.getEditionForChair(u)) {
                if(p.getEdition().getId()==e.getId() && p.getSession()==null){
                    lista.add(p);
                }
            }

        }
        return lista;
    }

    /**
     * @param conference The conference for which to retrieve editions
     * @return The editions of the given conference
     */
    public Collection<? extends Edition> getEditions(Conference conference) throws ServiceException {
        return server.getEditions(conference);
    }

    public void disconnect() throws NoSuchObjectException {
        unexportObject(this, true);

        System.out.println("Disconnecting from server");
    }

    /**
     * @param sessionChair The session chair which created the editions
     * @return The editions created by the given session chair
     * for which the submission deadline has passed
     */
    public Collection<Edition> getPastSubmissionEditions(User sessionChair) throws ServiceException {
        return server.getPastSubmissionEditions(sessionChair);
    }

    /**
     *
     * @param paper The paper
     * @return The reviews for the given paper
     */
    public Collection<Review> getReviews(Paper paper) throws ServiceException {
        return server.getReviews(paper);
    }

    /**
     *
     * @param edition The edition for which to retrieve papers
     * @return The papers for the given edition
     */
    public Collection<Paper> getPapers(Edition edition) throws ServiceException {
        return server.getPapers(edition);
    }
}

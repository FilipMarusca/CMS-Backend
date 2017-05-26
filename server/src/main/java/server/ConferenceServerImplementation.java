package server;

import com.ubb.cms.Conference;
import com.ubb.cms.Edition;
import com.ubb.cms.Paper;
import com.ubb.cms.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import server.crud.ConferenceService;
import server.crud.EditionService;
import server.crud.PaperService;
import server.crud.UserService;
import service.common.IConferenceClient;
import service.common.IConferenceServer;
import service.exception.ServiceException;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;


/**
 * Created by Raul on 25/04/2017.
 */
@Component
public class ConferenceServerImplementation implements IConferenceServer {
    private final static Logger logger = Logger.getLogger(ConferenceServerImplementation.class.getName());
    private static final int THREADS_NUMBER = Runtime.getRuntime().availableProcessors();
    private final UserService                    userService;
    private final ConferenceService              conferenceService;
    private final EditionService                 editionService;
    private final PaperService                   paperService;
    private       Map<String, IConferenceClient> loggedClients;

    @Autowired
    public ConferenceServerImplementation(UserService userService, ConferenceService conferenceService, EditionService editionService, PaperService paperService) {
        this.userService = userService;
        this.conferenceService = conferenceService;
        this.editionService = editionService;
        this.paperService = paperService;

        loggedClients = new ConcurrentHashMap<>();
    }

    @Override
    public List<User> getAllUser() {
        return userService.getAll();
    }

    @Override
    public synchronized List<Edition> getAllEditions() {
        return editionService.getAll();
    }

    @Override
    public List<Edition> getEditionAfterDate(Date date)
    {
        return editionService.getEditionAfterDate(date);
    }


    //@Override
    public synchronized User login(User user, IConferenceClient client) throws ServiceException {
        User existsUser = userService.checkUser(user);
        logger.info(user.toString());

        //invalid user
        if (existsUser == null) {
            throw new ServiceException("Invalid username/password");
        }

        //already logged in
        if (loggedClients.get(user.getUsername()) != null) {
            throw new ServiceException("User is already logged in");
        }

        //we save the user in loggedClients HashMap
        loggedClients.put(user.getUsername(), client);
        return existsUser;

    }

    @Override
    public void logout(String username) throws ServiceException {
        loggedClients.remove(username);
    }

    @Override
    public void addUser(User user) throws ServiceException {
        userService.add(user);

    }

    @Override
    public void addPaper(Paper paper) throws ServiceException {
        paperService.add(paper);
    }

    @Override
    public synchronized void updateUser(User newUser) {
        userService.update(newUser);
        this.notifyAllViewers();
    }

    @Override
    public List<Conference> getAllConferences() {
        return conferenceService.getAll();
    }

    @Override
    public List<Paper> getAllPapers() {
        return paperService.getAll();
    }

    @Override
    public synchronized User getUserById(int userId) {
        return userService.findById(userId);
    }

    @Override
    public synchronized Edition getEditionById(int editionId) {
        return editionService.findById(editionId);
    }

    @Override
    public void addConference(Conference conference) throws ServiceException {
        conferenceService.add(conference);
    }

    @Override
    public void addEdition(Edition edition) throws ServiceException {
        editionService.add(edition);
    }

    private void notifyAllViewers() {
        ExecutorService executor = Executors.newFixedThreadPool(THREADS_NUMBER);
        logger.info("Started executor on " + THREADS_NUMBER + " threads");
        for (String username : loggedClients.keySet()) {
            logger.info("intra la notify");
            IConferenceClient client = loggedClients.get(username);
            {
                executor.execute(() -> {
                    try {
                        logger.info("Updating client " + username);
                        client.showUpdated();
                    } catch (Exception e) {
                        logger.warning("Error at notifying clients " + e);
                    }
                });
            }

        }

        executor.shutdown();
    }

}

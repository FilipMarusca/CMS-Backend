package server;

import client.IConferenceClient;
import com.ubb.cms.Conference;
import com.ubb.cms.Edition;
import com.ubb.cms.Paper;
import com.ubb.cms.User;
import exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ConferenceService;
import service.EditionService;
import service.PaperService;
import service.UserService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by Raul on 25/04/2017.
 */
@Component
public class ConferenceServerImplementation implements IConferenceServer {

    private final UserService userService;
    private final ConferenceService conferenceService;
    private final EditionService editionService;
    private final PaperService paperService;
    private       Map<String, IConferenceClient> loggedClients;

    private static final int DEFAULTTHREADNUMBER = 5;

    @Autowired
    public ConferenceServerImplementation(UserService userService, ConferenceService conferenceService,
                                          EditionService editionService, PaperService paperService)
    {
        this.userService = userService;
        this.conferenceService = conferenceService;
        this.editionService = editionService;
        this.paperService = paperService;

        loggedClients = new ConcurrentHashMap<>();
    }

    @Override
    public void addUser(User user)
    {
        userService.addUser(user);

    }

    @Override
    public void addPaper(Paper paper) throws ServiceException {
        paperService.addPaper(paper);
    }


    @Override
    public List<User> getAllUser() {
        return userService.getAll();
    }

    @Override
    public synchronized void updateUser(User newUser, int key) {
        userService.updateUser(key, newUser);
        this.notifyAllViewers();
    }

    @Override
    public List<Conference> getAllConferences() {
        if(conferenceService == null)
        {
            System.out.println("e null");
        }
        return conferenceService.getAllConferences();
    }

    @Override
    public List<Paper> getAllPapers() {
        return paperService.getAllPapers();
    }

    //@Override
    public synchronized User login(User user, IConferenceClient client) throws ServiceException{


        User existsUser = userService.checkUser(user);

        //invalid user
        if (existsUser == null){
            throw new ServiceException("Invalid username/password");
        }

        //already logged in
        if (loggedClients.get(user.getUsername()) != null){
            throw new ServiceException("User is already logged in");
        }

        //we save the user in loggedClients HashMap
        loggedClients.put(user.getUsername(), client);
        return existsUser;

    }

    @Override
    public synchronized User getUserById(int userId)
    {
        return userService.findById(userId);
    }

    @Override
    public synchronized Edition getEditionById(int editionId)
    {
        return editionService.findById(editionId);
    }

    @Override
    public synchronized List<Edition> getAllEditions()
    {
        return editionService.getAll();
    }


    private void notifyAllViewers(){

        ExecutorService executor= Executors.newFixedThreadPool(DEFAULTTHREADNUMBER);
        for(String username :loggedClients.keySet()){
            System.out.println("intra la notify");
            IConferenceClient client = loggedClients.get(username);
            {
                executor.execute(() -> {
                    try {
                        client.showUpdated();
                    } catch (Exception e) {

                        System.err.println("Error at notifyng clints " + e);
                    }
                });
            }

        }

        executor.shutdown();
    }

}

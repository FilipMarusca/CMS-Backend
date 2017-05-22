package server;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import client.IConferenceClient;
import com.ubb.cms.*;
import exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ConferenceService;
import service.UserService;


/**
 * Created by Raul on 25/04/2017.
 */



@Component
public class ConferenceServerImplementation implements IConferenceServer {

    private UserService userService;
    @Autowired
    private ConferenceService conferenceService;
    private Map<String, IConferenceClient> loggedClients;

    private static final int DEFAULTTHREADNUMBER = 5;

    @Autowired
    public ConferenceServerImplementation(UserService userService)
    {
        this.userService = userService;
        loggedClients = new ConcurrentHashMap<>();

    }

    @Override
    public void addUser(User user)
    {
        userService.addUser(user);

    }


    @Override
    public List<User> getAllUser() {
        return userService.getAll();
    }


    @Override
    public List<Edition> getAllEditions() {
        return null;
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

    //@Override
    public synchronized String login(User user, IConferenceClient client) throws ServiceException{


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
        return existsUser.getTag();

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

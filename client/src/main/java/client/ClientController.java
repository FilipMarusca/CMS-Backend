package client;

import com.ubb.cms.Conference;
import com.ubb.cms.User;
import client.*;
import exception.ServiceException;
import org.springframework.stereotype.Component;
import server.IConferenceServer;
import utils.Observer;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raul on 26/04/2017.
 */
public class ClientController extends UnicastRemoteObject implements IConferenceClient {

    private IConferenceServer server;
    //private List<Observer<User>> userObservers = new ArrayList<>();
    private List<Observer<?>> observers = new ArrayList<>();

    public ClientController(IConferenceServer server) throws RemoteException
    {
        this.server = server;


    }

    public void addUser(User user) throws ServiceException
    {
        server.addUser(user);
    }


    public List<User> getAllUsers()
    {
        List<User> users = server.getAllUser();
        return users;
    }

    public List<Conference> getAllConferences()
    {
        return server.getAllConferences();
    }



    public void updateUser(User newUser, int key) throws ServiceException{
        server.updateUser(newUser, key);
    }


    public String login(String username, String password) throws ServiceException {
        User user = new User(username, password);
        return server.login(user, this);
        /*try {

        }
        catch (ServiceException exception)
        {
            System.out.println(exception.getStackTrace());
        }*/

        //user = userL;
    }


    @Override
    public void showUpdated() throws RemoteException {
        //System.out.println(updateType);
        this.notifyObservers();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {

    }

    public void notifyObservers() {
        for(Observer observer: observers)
        {
            observer.update();
        }

    }
}

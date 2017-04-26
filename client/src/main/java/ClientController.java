import com.ubb.cms.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by Raul on 26/04/2017.
 */
public class ClientController extends UnicastRemoteObject implements IConferenceClient {

    private IConferenceServer server;

    public ClientController(IConferenceServer server) throws RemoteException
    {
        this.server = server;


    }

    public List<User> getAllUsers()
    {
        List<User> users = server.getAllUser();
        for(User user:users) {
            //System.out.println(user.getId() + " " + user.getUsername());
        }
        return users;
    }


    @Override
    public void showUpdated() throws RemoteException {

    }
}

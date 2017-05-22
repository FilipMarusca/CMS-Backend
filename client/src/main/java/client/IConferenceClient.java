package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Raul on 26/04/2017.
 */
public interface IConferenceClient extends Remote {


    void showUpdated() throws RemoteException;

}

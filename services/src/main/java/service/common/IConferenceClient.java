package service.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Raul on 26/04/2017.
 */
public interface IConferenceClient extends Remote {


    void showUpdated() throws RemoteException;

    /**
     * Convenience method to test that a client is still connected
     *
     * @throws RemoteException
     */
    void ping() throws RemoteException;
}

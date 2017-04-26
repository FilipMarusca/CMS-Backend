import com.ubb.cms.Participation;
import com.ubb.cms.User;
import com.ubb.cms.utils.UserEditionEmb;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Raul on 26/04/2017.
 */
public class StartClient {

    public static void main(String[] args) throws RemoteException{
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
        IConferenceServer server = (IConferenceServer)factory.getBean("conferenceService");

        ClientController clientController = new ClientController(server);
        List<User> users = server.getAllUser();
        for(User user:users)
        {
            System.out.println(user.getId() + " " + user.getUsername());
        }







    }
}

import java.util.List;
import com.ubb.cms.*;

/**
 * Created by Raul on 25/04/2017.
 */




public class ConferenceServerImplementation implements IConferenceServer {

    private UserService userService;


    public ConferenceServerImplementation(UserService userService)
    {
        this.userService = userService;
    }


    @Override
    public List<User> getAllUser() {
        return userService.getAll();
    }

    @Override
    public List<Edition> getAllEditions() {
        return null;
    }
}

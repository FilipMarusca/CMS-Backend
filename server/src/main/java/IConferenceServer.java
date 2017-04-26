import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import com.ubb.cms.*;

import java.util.List;

/**
 * Created by Raul on 25/04/2017.
 */
public interface IConferenceServer {

    List<User> getAllUser();

    List<Edition> getAllEditions();

}

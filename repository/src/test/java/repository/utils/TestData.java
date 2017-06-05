package repository.utils;

import com.ubb.cms.Conference;
import com.ubb.cms.Edition;
import com.ubb.cms.Paper;
import com.ubb.cms.User;
import com.ubb.cms.utils.PaperStatus;
import com.ubb.cms.utils.UserTag;

import java.util.Date;

/**
 * Created by Mihai Zăvoian on 05.06.2017.
 */
public class TestData {
    public static Conference[] conferences = {
            new Conference("Conferinta1"),
            new Conference("Coferinta2")
    };

    public static Edition[] editions = {
            new Edition(conferences[0], new Date(20), new Date(25),"Editie1", new Date(10), new Date(15)),
            new Edition(conferences[0], new Date(200), new Date(250),"Editie2", new Date(100), new Date(150))
    };

    public static User[] users = {
            new User("mihai", "12345", "mihaitszavo@gmail.com", "Mihai", "Zavoian", UserTag.Participant),
            new User("iurisniti", "54321", "iurisniti.robert@gmail.com", "Robert", "Iurisniti", UserTag.Participant),
            new User("nichi", "qwertyui", "nikita.utiu@gmail.com", "Nichita", "Utiu", UserTag.Reviewer),
            new User("andreea", "12345678", "andreea.butnariu@gmail.com", "Andreea", "Butnariu", UserTag.Author),
            new User("timii", "haxxor", "tbodica@gmail.com", "Septimiu", "Bodica", UserTag.Author)
    };

    public static Paper[] papers = {
            new Paper(users[3], editions[0], PaperStatus.InReview, "paper1", "topic1", "paperPDF1".getBytes(), "summaryPDF1".getBytes()),
            new Paper(users[4], editions[0], PaperStatus.Accepted, "paper2", "topic2", "paperPDF2".getBytes(), "summaryPDF2".getBytes())
    };
}

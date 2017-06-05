package repository;

import com.ubb.cms.User;
import com.ubb.cms.utils.UserTag;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mihai Zăvoian on 04.06.2017.
 */
public class UserRepositoryTest {
    SessionFactory sessionFactory;
    UserRepository userRepository;

    User user1;

    @Before
    public void setUp() throws Exception {
        sessionFactory = SessionFactoryUtil.createSessionFactory();
        sessionFactory.openSession();

        userRepository = new UserRepository(sessionFactory);

        user1 = new User("mihai", "12345", "mihaitszavo@gmail.com", "Mihai", "Zavoian", UserTag.Participant);
    }

    @Test
    public void tests() throws Exception {
        userRepository.save(user1);
        assertEquals(userRepository.getAll().size(), 1);
        //TODO: more tests
    }

    @After
    public void tearDown() {
        sessionFactory.close();
    }

}
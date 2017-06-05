package repository;

import com.ubb.cms.Edition;
import com.ubb.cms.utils.UserTag;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import repository.utils.SessionFactoryCMSTest;
import repository.utils.TestData;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static repository.utils.TestData.*;

/**
 * Created by Mihai Zăvoian on 05.06.2017.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RepositoryTests {
    SessionFactory sessionFactory;
    PaperRepository paperRepository;
    UserRepository userRepository;
    ConferenceRepository conferenceRepository;
    EditionRepository editionRepository;

    @Before
    public void setUp() throws Exception {
        sessionFactory = SessionFactoryCMSTest.getInstance();

        userRepository = new UserRepository(sessionFactory);
        paperRepository = new PaperRepository(sessionFactory);
        conferenceRepository = new ConferenceRepository(sessionFactory);
        editionRepository = new EditionRepository(sessionFactory);
    }

    @Test
    public void test1user() throws Exception {
        sessionFactory.openSession();

        //initial empty repo
        assertEquals(userRepository.getAll().size(), 0);

        //populated repo
        userRepository.save(users[0]);
        userRepository.save(users[1]);
        userRepository.save(users[2]);
        userRepository.save(users[3]);
        userRepository.save(users[4]);
        assertEquals(userRepository.getAll().size(), 5);

        //trying to add duplicate username
        try{
            userRepository.save(users[3]);
            assertTrue(false);
        }
        catch (org.hibernate.exception.ConstraintViolationException e){
            assertTrue(true);
        }

        //checkUser
        assertEquals(userRepository.checkUser("mihai", "12345"), users[0]);
        assertNull(userRepository.checkUser("mihai", "12344"));

        //findByProperty
        assertEquals(userRepository.findBy("username", "mihai").size(), 1);
        assertEquals(userRepository.findBy("email", "mihaitszavo@gmail.com").size(), 1);
        assertEquals(userRepository.findBy("name", "Mihai").size(), 1);
        assertEquals(userRepository.findBy("surname", "Zavoian").size(), 1);
        assertEquals(userRepository.findBy("tag", UserTag.Participant).size(), 2);
        assertEquals(userRepository.findBy("tag", UserTag.Author).size(), 2);

        //delete
        userRepository.delete(userRepository.findBy("username", "mihai").iterator().next().getId());
        assertEquals(userRepository.getAll().size(), 4);
    }

    @Test
    public void test2conference(){
        //empty
        assertEquals(conferenceRepository.getAll().size(), 0);

        //populate
        conferenceRepository.add(conferences[0]);
        conferenceRepository.add(conferences[1]);
        assertEquals(conferenceRepository.getAll().size(), 2);
    }

    @Test
    public void test3edition(){
        //empty
        assertEquals(editionRepository.getAll().size(), 0);

        //populate
        editionRepository.save(editions[0]);
        editionRepository.save(editions[1]);
        assertEquals(editionRepository.getAll().size(), 2);

        //findBy
        assertEquals(editionRepository.findBy(conferenceRepository.getAll().iterator().next()).size(), 2);
    }

    @Test
    public void test4paper() throws Exception {
        //empty repo
        assertEquals(paperRepository.getAll().size(), 0);

        //populate repo
        paperRepository.add(papers[0]);
        paperRepository.add(papers[1]);
        assertEquals(paperRepository.getAll().size(), 2);

        //findBy
        assertEquals(paperRepository.findBy(editions[0]).size(), 2);
        assertEquals(paperRepository.findBy(editions[1]).size(), 0);

        //getPapersFromAuthor
        assertEquals(paperRepository.getPapersFromAuthor(users[3]).size(), 1);
        assertEquals(paperRepository.getPapersFromAuthor(users[4]).size(), 1);
        assertEquals(paperRepository.getPapersFromAuthor(users[0]).size(), 0);

        sessionFactory.close();
    }

}
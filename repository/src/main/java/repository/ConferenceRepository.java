package repository;

import com.ubb.cms.Conference;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Raul on 24/04/2017.
 */

@Repository
public class ConferenceRepository extends AbstractRepository<Conference> {

    @Autowired
    public ConferenceRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Conference.class);
    }

}

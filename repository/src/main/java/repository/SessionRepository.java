package repository;

import com.ubb.cms.ConferenceSession;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Cosmin on 6/4/2017.
 */
@Repository
public class SessionRepository extends AbstractRepository<ConferenceSession>{
    @Autowired
    public SessionRepository(SessionFactory sessionFactory) {
        super(sessionFactory,ConferenceSession.class);
    }
}

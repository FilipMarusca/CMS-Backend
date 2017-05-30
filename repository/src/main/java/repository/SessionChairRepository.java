package repository;

import com.ubb.cms.Conference;
import com.ubb.cms.SessionChair;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Raul on 30/05/2017.
 */
@Repository
public class SessionChairRepository extends AbstractRepository<SessionChair> {

    @Autowired
    public SessionChairRepository(SessionFactory sessionFactory) {
        super(sessionFactory, SessionChair.class);
    }
}

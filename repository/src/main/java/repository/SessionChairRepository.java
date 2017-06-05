package repository;

import com.ubb.cms.SessionChair;
import com.ubb.cms.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by Raul on 30/05/2017.
 */
@Repository
public class SessionChairRepository extends AbstractRepository<SessionChair> {

    @Autowired
    public SessionChairRepository(SessionFactory sessionFactory) {
        super(sessionFactory, SessionChair.class);
    }

    @SuppressWarnings("unchecked")
    public Collection<SessionChair> findBy(User sessionChair) {
        return (Collection<SessionChair>) findBy("chair.user", sessionChair);
    }
}

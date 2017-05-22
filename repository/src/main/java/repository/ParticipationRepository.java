package repository;

import com.ubb.cms.Participation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Raul on 26/04/2017.
 */
@Repository
public class ParticipationRepository extends AbstractRepository<Participation> {
    @Autowired
    public ParticipationRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Participation.class);
    }
}


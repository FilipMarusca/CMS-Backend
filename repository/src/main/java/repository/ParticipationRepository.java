package repository;

import com.ubb.cms.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;

/**
 * Created by Raul on 26/04/2017.
 */
public class ParticipationRepository extends AbstractRepository<Participation> {

    public ParticipationRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Participation.class);
    }
}


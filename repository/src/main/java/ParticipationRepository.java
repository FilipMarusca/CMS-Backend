import com.ubb.cms.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;

/**
 * Created by Raul on 26/04/2017.
 */
public class ParticipationRepository implements IRepository<Participation> {

    Configuration connection = null;
    ServiceRegistry serviceRegistry;
    SessionFactory sessionFactory;


    public ParticipationRepository()
    {
        connection = new Configuration().configure("/hibernate.cfg.xml").addAnnotatedClass(Conference.class).addAnnotatedClass(Edition.class).addAnnotatedClass(ConferenceSession.class).addAnnotatedClass(User.class).addAnnotatedClass(SessionChair.class).addAnnotatedClass(Participation.class).addAnnotatedClass(Paper.class).addAnnotatedClass(Review.class);
        serviceRegistry = new ServiceRegistryBuilder().applySettings(connection.getProperties()).buildServiceRegistry();
        sessionFactory = connection.buildSessionFactory(serviceRegistry);
    }

    @Override
    public void add(Participation entity) {

    }

    @Override
    public void delete(int key) {

    }

    @Override
    public List<Participation> getAll() {
        return null;
    }

    @Override
    public Participation findById(int key) {
        return null;
    }

    @Override
    public void update(int key, Participation newEntity) {

    }
}

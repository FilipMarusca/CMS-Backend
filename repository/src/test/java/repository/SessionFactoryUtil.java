package repository;

import com.ubb.cms.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by Mihai Zăvoian on 04.06.2017.
 */
public class SessionFactoryUtil {

    public static SessionFactory createSessionFactory()
    {
        //add entity classes
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Conference.class);
        configuration.addAnnotatedClass(Edition.class);
        configuration.addAnnotatedClass(Paper.class);
        configuration.addAnnotatedClass(Participation.class);
        configuration.addAnnotatedClass(Review.class);
        configuration.addAnnotatedClass(SessionChair.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(ConferenceSession.class);

        //H2 in-memory database
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect"); //set dialect
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver"); //set driver
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:mem:test"); //set url
        configuration.setProperty("hibernate.hbm2ddl.auto", "create"); //set db update mode
        configuration.setProperty("hibernate.current_session_context_class", "thread"); //set context
        configuration.setProperty("hibernate.show_sql", "true"); //for debug info

        return configuration.buildSessionFactory(new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry());
    }
}

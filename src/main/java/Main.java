import com.ubb.cms.Conference;
import com.ubb.cms.Edition;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


/**
 * Created by Alexandra Muresan on 4/5/2017.
 */
public class Main {

    public static void main(String[] args){


        Edition e = new Edition();
        Configuration con = new Configuration().configure("/hibernate.cfg.xml").addAnnotatedClass(Conference.class).addAnnotatedClass(Edition.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        session.save(e);
        tx.commit();
        session.close();
        System.out.println("here");


    }
}

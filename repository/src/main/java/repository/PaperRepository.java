package repository;

import com.ubb.cms.Paper;
import javafx.application.Application;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Raul on 24/04/2017.
 */
public class PaperRepository extends AbstractRepository<Paper> {

    public PaperRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Paper.class);
    }


}

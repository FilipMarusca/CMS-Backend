package repository;

import com.ubb.cms.Edition;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Raul on 24/04/2017.
 */
public class EditionRepository extends AbstractRepository<Edition>{

    public EditionRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Edition.class);
    }


}

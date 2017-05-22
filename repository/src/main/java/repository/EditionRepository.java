package repository;

import com.ubb.cms.Edition;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Raul on 24/04/2017.
 */
@Repository
public class EditionRepository extends AbstractRepository<Edition>{

    @Autowired
    public EditionRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Edition.class);
    }


}

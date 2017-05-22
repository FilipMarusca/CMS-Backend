package repository;

import com.ubb.cms.Paper;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Raul on 24/04/2017.
 */
@Repository
public class PaperRepository extends AbstractRepository<Paper> {
    @Autowired
    public PaperRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Paper.class);
    }


}

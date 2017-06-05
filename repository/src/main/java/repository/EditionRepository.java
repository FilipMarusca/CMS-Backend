package repository;

import com.ubb.cms.Conference;
import com.ubb.cms.Edition;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Raul on 24/04/2017.
 */
@Repository
public class EditionRepository extends AbstractRepository<Edition> {

    @Autowired
    public EditionRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Edition.class);
    }


    public List<Edition> getEditionAfterDate(Date date)
    {
        //System.out.println("aunge la cautare dupa data");
        List<Edition> editions = this.getAll();
        List<Edition> upcomingEditions= new ArrayList<>();
        for(Edition edition: editions)
        {
            if(date.compareTo(edition.getPaperSubmissionDeadline()) < 0)
            {
                //System.out.println(edition);
                upcomingEditions.add(edition);
            }
        }
        return upcomingEditions;


    }

    @SuppressWarnings("unchecked")
    public Collection<Edition> findBy(Conference conference) {
        return (Collection<Edition>) findBy("conference", conference);
    }
}

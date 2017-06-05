package repository;

import com.ubb.cms.Edition;
import com.ubb.cms.Paper;
import com.ubb.cms.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Raul on 24/04/2017.
 */
@Repository
public class PaperRepository extends AbstractRepository<Paper> {
    @Autowired
    public PaperRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Paper.class);
    }

    public List<Paper> getPapersFromAuthor(User author) {
        List<Paper> lista = new ArrayList<>();
        for (Paper paper : getAll()) {

            if (paper.getAuthor().getId() == author.getId()) {
                lista.add(paper);
            }
        }
        return lista;
    }

    /**
     *
     * @param edition The edition for which to find papers
     * @return The papers submitted for given edition
     */
    @SuppressWarnings("unchecked")
    public Collection<Paper> findBy(Edition edition) {
        return findBy("edition", edition);
    }
}

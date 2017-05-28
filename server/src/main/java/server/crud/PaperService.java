package server.crud;

import com.ubb.cms.Paper;
import com.ubb.cms.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.IRepository;
import repository.PaperRepository;
import server.validator.ValidatorInterface;

import java.util.List;

/**
 * Created by Raul on 23/05/2017.
 */
@Component
public class PaperService extends BaseService<Paper> {
    private PaperRepository paperRepository;

    @Autowired
    public PaperService(ValidatorInterface validator, PaperRepository paperRepository) {
        super(validator);
        this.paperRepository = paperRepository;
    }

    @Override
    IRepository<Paper> getRepository() {
        return paperRepository;
    }

    public List<Paper> getPapersFromAuthor(User author) {
        return paperRepository.getPapersFromAuthor(author);
    }
}

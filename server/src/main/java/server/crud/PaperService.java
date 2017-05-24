package server.crud;

import com.ubb.cms.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.PaperRepository;
import server.validator.ValidatorInterface;
import service.exception.ServiceException;

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


    public void addPaper(Paper paper) throws ServiceException {
        validate(paper);
        paperRepository.add(paper);
    }


    public List<Paper> getAllPapers() {
        return paperRepository.getAll();
    }


}

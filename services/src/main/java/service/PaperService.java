package service;

import com.ubb.cms.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.PaperRepository;
import service.validator.ValidatorInterface;

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


    public void addPaper(Paper paper) {
        validator.validate(paper);
        paperRepository.add(paper);
    }


    public List<Paper> getAllPapers() {
        return paperRepository.getAll();
    }


}

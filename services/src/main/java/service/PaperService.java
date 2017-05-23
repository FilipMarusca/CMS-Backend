package service;

import com.ubb.cms.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.EditionRepository;
import repository.PaperRepository;

import java.util.List;

/**
 * Created by Raul on 23/05/2017.
 */
@Component
public class PaperService {

    private PaperRepository paperRepository;

    @Autowired
    public PaperService(PaperRepository paperRepository)
    {
        this.paperRepository = paperRepository;
    }


    public void addPaper(Paper paper)
    {
        paperRepository.add(paper);
    }


    public List<Paper> getAllPapers()
    {
        return paperRepository.getAll();
    }



}

package server.crud;

import com.ubb.cms.Conference;
import com.ubb.cms.Edition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.EditionRepository;
import server.validator.ValidatorInterface;
import service.exception.ServiceException;

import java.util.Date;
import java.util.List;

/**
 * Created by Raul on 26/04/2017.
 */
@Component
public class EditionService extends BaseService<Edition> {
    private EditionRepository editionRepository;

    @Autowired
    public EditionService(ValidatorInterface validator, EditionRepository editionRepository) {
        super(validator);
        this.editionRepository = editionRepository;
    }

    public List<Edition> getAll()
    {
        return editionRepository.getAll();

    }

    public void addEdition(int id, Conference conference, Date beginningDate, Date endingDate, String name, Date paperSubmissionDeadline, Date finalDeadline) throws ServiceException {
        Edition edition = new Edition(id, conference, beginningDate, endingDate, name, paperSubmissionDeadline, finalDeadline);
        validate(edition);
        editionRepository.add(edition);
    }

    public void addEdition(Edition edition) throws ServiceException {
        validate(edition);
        editionRepository.add(edition);
    }

    public void deleteUser(int key)
    {
        editionRepository.delete(key);
    }

    public Edition findById(int key)
    {
        return editionRepository.findById(key);
    }



}

package server.crud;

import com.ubb.cms.Conference;
import com.ubb.cms.Edition;
import com.ubb.cms.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.EditionRepository;
import repository.IRepository;
import server.validator.ValidatorInterface;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Raul on 26/04/2017.
 */
@Component
public class EditionService extends BaseService<Edition> {
    private EditionRepository   editionRepository;
    private SessionChairService sessionChairService;

    @Autowired
    public EditionService(
            ValidatorInterface validator, EditionRepository editionRepository, SessionChairService sessionChairService
    ) {
        super(validator);
        this.editionRepository = editionRepository;
        this.sessionChairService = sessionChairService;
    }

    public Collection<Edition> findBy(Conference conference) {
        return editionRepository.findBy(conference);
    }

    public Collection<Edition> getPastSubmissionEditions(User user) {
        return sessionChairService
                .findBy(user)
                .stream()
                .map(sessionChair1 -> sessionChair1.getChair().getEdition())
                .filter(edition -> edition.getPaperSubmissionDeadline().compareTo(new Date()) < 0)
                .collect(Collectors.toList());
    }

    @Override
    IRepository<Edition> getRepository() {
        return editionRepository;
    }



    public List<Edition> getEditionAfterDate(Date date)
    {
        return editionRepository.getEditionAfterDate(date);
    }
}

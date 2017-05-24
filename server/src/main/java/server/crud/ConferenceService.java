package server.crud;

import com.ubb.cms.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.ConferenceRepository;
import server.validator.ValidatorInterface;
import service.exception.ServiceException;

import java.util.List;

/**
 * Created by Raul on 22/05/2017.
 */

@Component
public class ConferenceService extends BaseService<Conference>{
    private ConferenceRepository conferenceRepository;

    @Autowired
    public ConferenceService(ValidatorInterface validator, ConferenceRepository conferenceRepository) {
        super(validator);
        this.conferenceRepository = conferenceRepository;
    }

    public void add(Conference conference) throws ServiceException {
        validate(conference);
        conferenceRepository.add(conference);
    }

    public List<Conference> getAllConferences() {
        return conferenceRepository.getAll();
    }
}

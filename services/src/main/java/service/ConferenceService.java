package service;

import com.ubb.cms.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.ConferenceRepository;
import service.validator.ValidatorInterface;

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

    public void add(Conference conference) {
        validate(conference);
        conferenceRepository.add(conference);
    }

    public List<Conference> getAllConferences() {
        return conferenceRepository.getAll();
    }
}

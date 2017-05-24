package server.crud;

import com.ubb.cms.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.ConferenceRepository;
import repository.IRepository;
import server.validator.ValidatorInterface;

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

    @Override
    IRepository<Conference> getRepository() {
        return conferenceRepository;
    }
}

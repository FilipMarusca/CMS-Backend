package service;

import com.ubb.cms.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.ConferenceRepository;

import java.util.List;

/**
 * Created by Raul on 22/05/2017.
 */

@Component
public class ConferenceService {


    private ConferenceRepository conferenceRepository;

    @Autowired
    public ConferenceService( ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;

    }

    public List<Conference> getAllConferences()
    {
        return conferenceRepository.getAll();
    }

}

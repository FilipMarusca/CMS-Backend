package service;

import com.ubb.cms.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import repository.ConferenceRepository;

/**
 * Created by Raul on 22/05/2017.
 */
public class ConferenceService {

    private ConferenceRepository conferenceRepository;

    @Autowired
    public ConferenceService( ConferenceRepository paperRepository) {


    }

}

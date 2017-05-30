package server.crud;

import com.ubb.cms.Conference;
import com.ubb.cms.SessionChair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.ConferenceRepository;
import repository.IRepository;
import repository.SessionChairRepository;
import server.validator.ValidatorInterface;

/**
 * Created by Raul on 30/05/2017.
 */
@Component
public class SessionChairService extends BaseService<SessionChair> {
    private SessionChairRepository sessionChairRepository;

    @Autowired
    public SessionChairService(ValidatorInterface validator, SessionChairRepository sessionChairRepository) {
        super(validator);
        this.sessionChairRepository = sessionChairRepository;
    }


    @Override
    IRepository<SessionChair> getRepository() {
        return sessionChairRepository;
    }


}




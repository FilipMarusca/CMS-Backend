package server.crud;

import com.ubb.cms.ConferenceSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.IRepository;
import repository.SessionRepository;
import server.validator.ValidatorInterface;

/**
 * Created by Cosmin on 6/4/2017.
 */
@Component
public class SessionService extends BaseService<ConferenceSession> {
    private SessionRepository sessionRepository;
    @Autowired
    SessionService(ValidatorInterface validator,SessionRepository sessionRepository){
        super(validator);
        this.sessionRepository=sessionRepository;}

    @Override
    IRepository<ConferenceSession> getRepository() {
        return sessionRepository;
    }
}

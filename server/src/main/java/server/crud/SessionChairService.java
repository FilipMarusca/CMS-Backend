package server.crud;

import com.ubb.cms.SessionChair;
import com.ubb.cms.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.IRepository;
import repository.SessionChairRepository;
import server.validator.ValidatorInterface;

import java.util.Collection;

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

    public Collection<SessionChair> findBy(User sessionChair) {
        return sessionChairRepository.findBy(sessionChair);
    }


    @Override
    IRepository<SessionChair> getRepository() {
        return sessionChairRepository;
    }


}




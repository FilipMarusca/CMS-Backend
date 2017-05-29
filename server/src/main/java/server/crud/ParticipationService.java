package server.crud;

import com.ubb.cms.Conference;
import com.ubb.cms.Paper;
import com.ubb.cms.Participation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.ConferenceRepository;
import repository.IRepository;
import repository.ParticipationRepository;
import server.validator.ValidatorInterface;

/**
 * Created by Raul on 29/05/2017.
 */
@Component
public class ParticipationService extends BaseService<Participation>{
    private ParticipationRepository participationRepository;

    @Autowired
    public ParticipationService(ValidatorInterface validator, ParticipationRepository participationRepository) {
        super(validator);
        this.participationRepository = participationRepository;
    }

    @Override
    IRepository<Participation> getRepository() {
        return participationRepository;
    }
}

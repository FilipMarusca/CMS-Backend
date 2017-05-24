package server.crud;

import com.ubb.cms.Edition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.EditionRepository;
import repository.IRepository;
import server.validator.ValidatorInterface;

/**
 * Created by Raul on 26/04/2017.
 */
@Component
public class EditionService extends BaseService<Edition> {
    private EditionRepository editionRepository;

    @Autowired
    public EditionService(ValidatorInterface validator, EditionRepository editionRepository) {
        super(validator);
        this.editionRepository = editionRepository;
    }

    @Override
    IRepository<Edition> getRepository() {
        return editionRepository;
    }
}

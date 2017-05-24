package server.crud;

import server.validator.ValidatorInterface;
import service.exception.ServiceException;

import javax.validation.ValidationException;

/**
 * @author Marius Adam
 */
public class BaseService<T> {
    /**
     * Do not use it directly, call this.validate instead from child class
     */
    private final ValidatorInterface validator;

    public BaseService(ValidatorInterface validator) {
        this.validator = validator;
    }

    protected void validate(T obj) throws ServiceException {
        try {
            validator.validate(obj);
        } catch (ValidationException e) {
            throw new ServiceException(e);
        }
    }
}

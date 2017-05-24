package service;

import service.validator.ValidatorInterface;

/**
 * @author Marius Adam
 */
public class BaseService<T> {
    protected ValidatorInterface validator;

    public BaseService(ValidatorInterface validator) {
        this.validator = validator;
    }

    protected void validate(T obj) {
        validator.validate(obj);
    }
}

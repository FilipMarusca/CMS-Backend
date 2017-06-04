package server.validator;

import service.exception.ValidationException;

/**
 * @author Marius Adam
 */
public interface ValidatorInterface {
    <T> void validate(T obj) throws ValidationException;
}
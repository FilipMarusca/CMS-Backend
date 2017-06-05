package server.validator;

import service.exception.ValidationException;

/**
 * @author Marius Adam
 */
public interface ValidatorInterface {
    /**
     *
     * @param obj The object to validate
     * @param <T> The object's type
     * @throws ValidationException If the object is invalid
     */
    <T> void validate(T obj) throws ValidationException;
}
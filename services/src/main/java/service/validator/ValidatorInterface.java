package service.validator;

/**
 * @author Marius Adam
 */
public interface ValidatorInterface {
    <T> void validate(T obj);
}
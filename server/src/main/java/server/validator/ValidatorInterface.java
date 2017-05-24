package server.validator;

/**
 * @author Marius Adam
 */
public interface ValidatorInterface {
    <T> void validate(T obj);
}
package server.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.exception.ValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author Marius Adam
 */
@Service
public class EntityValidator implements ValidatorInterface {
    private Validator javaxValidator;

    @Autowired
    public EntityValidator() {
        this.javaxValidator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * Validate the object based on javax annotations and a custom validation method
     *
     * @param obj The object to validate
     * @param <T> The object's type
     * @throws ValidationException If the object is invalid
     */
    @Override
    public <T> void validate(T obj) throws ValidationException {
        Set<ConstraintViolation<T>> constraintViolations = javaxValidator.validate(obj);
        ValidationException.Builder exceptionBuilder = ValidationException.newBuilder();
        constraintViolations.forEach(tConstraintViolation -> {
            String property = tConstraintViolation.getPropertyPath().toString();
            exceptionBuilder.addMessage(property, tConstraintViolation.getMessage());
        });
        customValidate(obj, exceptionBuilder);

        if (exceptionBuilder.hasErrors()) {
            throw exceptionBuilder.build(obj.getClass().getSimpleName());
        }
    }

    /**
     * This methods needs to be overridden in order to add validation
     * in addition to annotations for an entity
     *
     * @param obj The object to validate
     * @param exceptionBuilder The exception builder
     * @param <T> The object type
     */
    protected <T> void customValidate(T obj, ValidationException.Builder exceptionBuilder) {

    }
}
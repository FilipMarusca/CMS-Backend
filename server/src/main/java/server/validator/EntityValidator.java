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

    @Override
    public <T> void validate(T obj) throws ValidationException {
        Set<ConstraintViolation<T>> constraintViolations = javaxValidator.validate(obj);
        ValidationException.Builder exceptionBuilder = ValidationException.newBuilder();
        constraintViolations.forEach(tConstraintViolation -> {
            String property = tConstraintViolation.getPropertyPath().toString();
            exceptionBuilder.addMessage(property, tConstraintViolation.getMessage());
        });

        if (exceptionBuilder.hasErrors()) {
            throw exceptionBuilder.build(obj.getClass().getSimpleName());
        }
    }
}
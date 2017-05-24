package service.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.*;
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
    public <T> void validate(T obj) {
        Set<ConstraintViolation<T>> constraintViolations = javaxValidator.validate(obj);
        StringBuilder sb = new StringBuilder();
        constraintViolations.forEach(
                tConstraintViolation -> sb
                        .append(tConstraintViolation.getMessage())
                        .append(System.lineSeparator())
        );

        if (sb.length() > 0) {
            throw new ValidationException(sb.toString());
        }
    }
}
package service.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marius Adam
 */
public class ValidationException extends ServiceException {
    private Map<String, List<String>> messages;
    private String                    validatedEntityName;

    private ValidationException() {
        this(null, "");
    }

    private ValidationException(Map<String, List<String>> messages, String validatedEntity) {
        this.messages = messages;
        this.validatedEntityName = validatedEntity;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getMessagesAsString() {
        StringBuilder sb = new StringBuilder();
        messages.forEach((prop, messages) -> messages.forEach(msg -> sb.append(msg).append(System.lineSeparator())));
        return sb.toString();
    }

    public Map<String, List<String>> getMessages() {
        return messages;
    }

    public String getValidatedEntityName() {
        return validatedEntityName;
    }

    public static class Builder {
        private Map<String, List<String>> errors;

        private Builder() {
            this.errors = new HashMap<>();
        }

        public void addMessage(String property, String message) {
            errors.computeIfAbsent(property, k -> new ArrayList<>());
            errors.get(property).add(message);
        }

        public boolean hasErrors() {
            return errors.size() > 0;
        }

        public ValidationException build(String entityName) {
            return new ValidationException(errors, entityName);
        }
    }
}

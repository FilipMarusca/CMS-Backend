package service.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marius Adam
 */
public class ValidationException extends ServiceException {
    private Map<String, List<String>> messagesMap;
    private String                    validatedEntityName;

    private ValidationException(Map<String, List<String>> messages, String validatedEntity) {
        this.messagesMap = messages;
        this.validatedEntityName = validatedEntity;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getMessagesAsString() {
        StringBuilder sb = new StringBuilder();
        messagesMap.forEach((prop, messages) -> {
            sb.append(prop).append(":").append(System.lineSeparator());
            messages.forEach(msg -> sb.append("\t").append(msg).append(System.lineSeparator()));
        });
        return sb.toString();
    }

    @Override
    public String getMessage() {
        return getMessagesAsString();
    }

    /**
     * @return The error messagesMap for each of the properties
     */
    public Map<String, List<String>> getMessagesMap() {
        return messagesMap;
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

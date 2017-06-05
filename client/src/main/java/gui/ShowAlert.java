package gui;

import javafx.scene.control.Alert;
import service.exception.ValidationException;

/**
 * Created by Alexandra Muresan on 5/30/2017.
 */
public class ShowAlert {

    public static void showAlert(String message) {
        show(Alert.AlertType.ERROR, "Error", "Application error", message);
    }

    public static void showOnSucces(String message) {
        show(Alert.AlertType.INFORMATION, "Info", "Operation executed", message);
    }

    public static void show(Alert.AlertType type, String title, String header, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
//        alert.setWidth(1280D);
        alert.show();
    }

    public static void handle(Exception e) {
        handle(e, "");
    }

    public static void handle(Exception e, String description) {
        if (e instanceof ValidationException) {
            ValidationException validationException = (ValidationException) e;
            String title = "Validation error";
            String header = String.format("The %s is invalid.", validationException.getValidatedEntityName());
            show(Alert.AlertType.ERROR, title, header, validationException.getMessagesAsString());
        } else {
            if (description.isEmpty()) {
                showAlert(e.getMessage());
            } else {
                String msg = String.format(
                        "%s. Reason: %s%s",
                        description,
                        System.lineSeparator(),
                        e.getMessage()
                );
                showAlert(msg);
            }

            e.printStackTrace();
        }
    }
}

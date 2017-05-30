package gui;

import javafx.scene.control.Alert;

/**
 * Created by Alexandra Muresan on 5/30/2017.
 */
public class ShowAlert {

    public static void showAlert(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Application error");
        alert.setContentText(message);
        alert.show();
    }

    public static void showOnSucces(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succes");
        alert.setHeaderText("Operation executed");
        alert.setContentText(message);
        alert.show();
    }
}

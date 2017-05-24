package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Marius Adam
 */
public class CreateConferenceView extends BaseView {
    public TextField confNameField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onGotoEdition_clicked(ActionEvent actionEvent) throws IOException {
        switchToView("createEdition.fxml", "createEdition.css", "Create edition");
    }

    @FXML
    public void logOutHandler() throws Exception
    {
        defaultLogoutHandler();
    }

    public void onCreateConf_clicked(ActionEvent actionEvent) {
        try {
            String name = confNameField.getText();
            controller.addConference(name);
            ShowAlert.showOnSucces("Conference " + name + " added.");
        } catch (Exception e) {
            ShowAlert.showAlert(e.getMessage());
        }
    }
}

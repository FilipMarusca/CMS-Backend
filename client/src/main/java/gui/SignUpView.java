package gui;

import com.ubb.cms.User;
import com.ubb.cms.utils.UserTag;
import exception.ServiceException;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Raul on 22/05/2017.
 */
public class SignUpView extends BaseView {
    @FXML
    private TextField         username;
    @FXML
    private TextField         password;
    @FXML
    private TextField         name;
    @FXML
    private TextField         surname;
    @FXML
    private TextField         email;
    @FXML
    private ComboBox<UserTag> tag;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tag.getItems().addAll(UserTag.values());
        tag.getSelectionModel().select(UserTag.Participant);
    }

    public void addUser() {
        System.out.println(username.getText());
        System.out.println(password.getText());
        System.out.println(name.getText());
        System.out.println(surname.getText());
        System.out.println(email.getText());
        try {
            controller.addUser(new User(
                    username.getText(),
                    password.getText(),
                    email.getText(),
                    name.getText(),
                    surname.getText(),
                    tag.getSelectionModel().getSelectedItem()
            ));
            ShowAlert.showOnSucces("UserSuccesfully added");
            try {
                switchToView("login.fxml", "login.css", "Login", null);
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        } catch (ServiceException exception) {
            ShowAlert.showAlert(exception.getMessage());
        }
    }
}

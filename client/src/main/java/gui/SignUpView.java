package gui;

import client.ClientController;
import client.StartClient;
import com.ubb.cms.User;
import exception.ServiceException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Raul on 22/05/2017.
 */
public class SignUpView {

    private ClientController controller;
    private Stage currentStage;

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField email;
    @FXML
    private TextField tag;


    public void addUser()
    {
        System.out.println(username.getText());
        System.out.println(password.getText());
        System.out.println(name.getText());
        System.out.println(surname.getText());
        System.out.println(email.getText());
        try {
            controller.addUser(new User(username.getText(), password.getText(), email.getText(), name.getText(), surname.getText(), tag.getText()));
            ShowAlert.showOnSucces("UserSuccesfully added");
            try {
                //System.out.println("intra la schimbare de view");
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(StartClient.class.getClassLoader().getResource("login.fxml"));
                BorderPane root = loader.load();
                LoginView loginView = loader.getController();
                loginView.setController(controller, currentStage);
                Scene scene = new Scene(root);
                scene.getStylesheets().add(StartClient.class.getResource("/login.css").toString());
                currentStage.setScene(scene);
                currentStage.show();
            }
            catch (IOException exception)
            {
                System.out.println(exception.getMessage());
            }
            //(String username, String password, String email, String name, String surname, String tag)

        }
        catch (ServiceException exception)
        {
            ShowAlert.showAlert(exception.getMessage());
        }
    }




    public void setController(ClientController clientController, Stage currentStage)
    {
        this.controller = clientController;
        this.currentStage = currentStage;
    }

}

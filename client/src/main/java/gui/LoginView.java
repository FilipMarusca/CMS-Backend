package gui;

import client.ClientController;
import client.StartClient;
import com.ubb.cms.Conference;
import com.ubb.cms.User;
import exception.ServiceException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Raul on 22/05/2017.
 */
public class LoginView  {

    private ClientController controller;
    private Stage currentStage;

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button logInButton;


    public void buttonHandler()
    {
        //System.out.println(username.getText());
        //System.out.println(password.getText());
        for(Conference conference: controller.getAllConferences())
        {
            System.out.println(conference);
        }

        System.out.println("trece de getConferences");
        try {
            String tag = controller.login(username.getText(), password.getText());
            if(tag.equals("ADMIN"))
            {
                //System.out.println("intra in admin");
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(LoginView.class.getClassLoader().getResource("UserView.fxml"));
                    BorderPane root = loader.load();
                    UserView userView = loader.getController();
                    userView.setController(controller, currentStage);
                    controller.addObserver(userView);
                    Scene scene = new Scene(root);
                    //scene.getStylesheets().add(StartClient.class.getResource("/login.css").toString());
                    currentStage.setScene(scene);
                    currentStage.setTitle("Admin");
                    currentStage.show();
                }
                catch (IOException exception)
                {
                    System.out.println(exception.getMessage());
                }

            }
            if(tag.equals("PARTICIPANT"))
            {

            }
            if(tag.equals("AUTHOR")){
                System.out.println("intra in autor");
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(LoginView.class.getClassLoader().getResource("author.fxml"));
                    BorderPane root = loader.load();
                    AuthorView authorView = loader.getController();
                    authorView.setController(controller, currentStage);
                    controller.addObserver(authorView);
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add(LoginView.class.getResource("/author.css").toString());
                    currentStage.setScene(scene);
                    currentStage.setTitle("Author");
                    currentStage.show();
                }
                catch (IOException exception)
                {
                    System.out.println(exception.getMessage());
                }
            }
        }
        catch (ServiceException ex)
        {
            ShowAlert.showAlert(ex.getMessage());
        }


    }

    public void changeToSignUp()
    {
        try {
            System.out.println("intra la log in");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LoginView.class.getClassLoader().getResource("signup.fxml"));
            BorderPane root = loader.load();
            SignUpView signUpView = loader.getController();
            signUpView.setController(controller, currentStage);
            Scene scene = new Scene(root);
            scene.getStylesheets().add(LoginView.class.getResource("/signup.css").toString());
            System.out.println("trece de css");
            currentStage.setScene(scene);
            currentStage.show();
            System.out.println("trece de show");
        }
        catch (IOException exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    public void setController(ClientController clientController, Stage currentStage)
    {
        this.controller = clientController;
        this.currentStage = currentStage;

        //model = FXCollections.observableArrayList(controller.getAllUsers());
        //table.setItems(model);
    }

}

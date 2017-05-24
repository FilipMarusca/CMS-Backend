package gui;

import client.ClientController;
import com.ubb.cms.User;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import utils.Observer;

import java.io.IOException;

/**
 * @author Marius Adam
 */
public abstract class BaseView implements Initializable, Observer
{
    protected ClientController controller;
    protected Stage            currentStage;
    protected User             loggedUser;

    public ClientController getController() {
        return controller;
    }

    public void setController(ClientController controller) {
        this.controller = controller;
    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    protected void switchToView(String fxmlPath, String cssPath, String title) throws IOException {
        switchToView(fxmlPath, cssPath, title, loggedUser);
    }

    protected void switchToView(String fxmlPath, String cssPath, String title, User currentUser) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource(fxmlPath));
        BorderPane root = loader.load();
        BaseView baseView = loader.getController();
        baseView.setController(controller);
        baseView.setCurrentStage(currentStage);
        baseView.setLoggedUser(currentUser);
        controller.addObserver(baseView);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getClassLoader().getResource(cssPath).toString());
        System.out.println("trece de css " + title);
        currentStage.setScene(scene);
        currentStage.setTitle(title);
        currentStage.show();
        System.out.println("trece de show " + title);
    }

    @Override
    public void update() {

    }

    protected void defaultLogoutHandler() {
        try {
            String title = "Conference Management System";
            controller.logout(loggedUser.getUsername());
            switchToView("login.fxml", "login.css", title, null);
        } catch (Exception ex) {
            ShowAlert.showAlert("User not logged in!");
        }
    }
}

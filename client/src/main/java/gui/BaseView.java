package gui;

import client.ClientController;
import com.ubb.cms.User;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.common.Observer;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author Marius Adam
 */
public abstract class BaseView implements Initializable, Observer {
    protected static final Logger logger = Logger.getLogger(BaseView.class.getName());
    protected ClientController controller;
    protected Stage            currentStage;
    protected User             loggedUser;

    public ClientController getController() {
        return controller;
    }

    public void setController(ClientController controller) {
        this.controller = controller;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    @Override
    public void update() {}

    void switchToView(String fxmlPath, String cssPath, String title) {
        switchToView(fxmlPath, cssPath, title, loggedUser);
    }

    void switchToView(String fxmlPath, String cssPath, String title, User currentUser) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource(fxmlPath));
        try {
            Parent root = loader.load();
            BaseView baseView = loader.getController();
            baseView.setController(controller);
            baseView.setCurrentStage(currentStage);
            baseView.setLoggedUser(currentUser);
            controller.addObserver(baseView);
            Scene scene = new Scene(root);
//        currentStage.setResizable(false);
            scene.getStylesheets().add(getClass().getClassLoader().getResource(cssPath).toString());
            logger.info("trece de css " + title);
            currentStage.setScene(scene);
            currentStage.setTitle(title);
            currentStage.show();
            currentStage.sizeToScene();
            logger.info("trece de show " + title);

            logger.info("Calling update on the controller");
            baseView.update();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void defaultLogoutHandler() {
        try {
            String title = "Conference Management System";
            controller.logout(loggedUser.getUsername());
            switchToView("login.fxml", "login.css", title, null);
        } catch (Exception ex) {
            ShowAlert.showAlert("User not logged in!");
        }
    }

    /**
     * Default exception handler
     *
     * @param e The exception
     */
    void handle(Exception e) {
        handle(e, "");
    }

    void handle(Exception e, String description) {
        ShowAlert.handle(e, description);
    }
}

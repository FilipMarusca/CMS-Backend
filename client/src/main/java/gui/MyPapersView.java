package gui;

import client.ClientController;
import com.ubb.cms.Edition;
import com.ubb.cms.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import utils.Observer;

/**
 * Created by Alexandra Muresan on 5/23/2017.
 */
public class MyPapersView implements Observer<User> {

    private ClientController controller;
    private Stage currentStage;
    private int authorId;
    @Override
    public void update() {

    }

    @FXML
    public void initialize(){

    }

    public void setController(ClientController clientController, Stage currentStage, int authorId)
    {
        this.controller = clientController;
        this.currentStage = currentStage;
        this.authorId = authorId;

    }

    @FXML
    public void goBackHandler(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MyPapersView.class.getClassLoader().getResource("author.fxml"));
            BorderPane root = loader.load();
            AuthorView authorView = loader.getController();
            authorView.setController(controller, currentStage, authorId);
            controller.addObserver(authorView);
            Scene scene = new Scene(root);
            scene.getStylesheets().add(LoginView.class.getResource("/author.css").toString());
            currentStage.setScene(scene);
            currentStage.show();
        }catch(Exception ex){
            ShowAlert.showAlert(ex.getMessage());
        }
    }
}

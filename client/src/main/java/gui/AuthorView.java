package gui;

import client.ClientController;
import com.ubb.cms.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utils.Observer;

import java.io.File;

/**
 * Created by Alexandra Muresan on 5/22/2017.
 */
public class AuthorView implements Observer<User> {

    private ClientController controller;

    private Stage currentStage;

    @FXML
    Button uploadBtn;

    @FXML
    public void uploadHandler(){

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(currentStage);
        System.out.println(file);
    }

    public void setController(ClientController clientController, Stage currentStage)
    {
        this.controller = clientController;
        this.currentStage = currentStage;

    }
    @Override
    public void update() {

    }
}

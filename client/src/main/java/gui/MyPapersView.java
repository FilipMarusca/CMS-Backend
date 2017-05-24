package gui;

import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Alexandra Muresan on 5/23/2017.
 */
public class MyPapersView extends BaseView {

    @Override
    public void update() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void goBackHandler(){
        try{
            switchToView("author.fxml", "author.css", "Author");
        }catch(Exception ex){
            ShowAlert.showAlert(ex.getMessage());
        }
    }
}

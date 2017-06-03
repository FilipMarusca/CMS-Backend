package gui;

import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Alexandra Muresan on 6/3/2017.
 */
public class SessionAddPapersView extends BaseView {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void backBtnHandler(){
        try{
            switchToView("session_edition.fxml","session_edition.css","Manage Editions",loggedUser);
        }catch(Exception ex){

        }
    }
}

package gui;

import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Alexandra Muresan on 6/3/2017.
 */
public class SessionEditionView extends BaseView {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void backBtnHandler(){
        try{
            switchToView("create.fxml","create.css","Session Chair: "+loggedUser.getUsername(),loggedUser);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    @FXML
    public void addPapersBtnHandler(){
        try{
            switchToView("session_add_papers.fxml","session_add_papers.css","Add papers",loggedUser);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

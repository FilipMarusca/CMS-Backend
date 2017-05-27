package gui;

import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Alexandra Muresan on 5/27/2017.
 */
public class ReviewerView extends BaseView {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void logOutBtnHandler(){
        try{
            controller.logout(loggedUser.getUsername());
            defaultLogoutHandler();
        }catch(Exception ex){
            ShowAlert.showAlert(ex.getMessage());
        }
    }
    @FXML
    public void myAisgnBtnHandler(){
        try{
            switchToView("reviewer_assign.fxml","reviewer_assign.css","My Assignments",loggedUser);
        }catch(Exception ex){
            ShowAlert.showAlert(ex.getMessage());
        }
    }
}

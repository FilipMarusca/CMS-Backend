package gui;

import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Alexandra Muresan on 5/30/2017.
 */
public class ReviewerReviewsView extends BaseView {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void backBtnHandler(){
        try{
            switchToView("reviewer_main.fxml","reviewer_main.css","Reviewer: "+loggedUser.getUsername(),loggedUser);
        }catch(Exception ex){
            ShowAlert.showAlert(ex.getMessage());
        }
    }
}

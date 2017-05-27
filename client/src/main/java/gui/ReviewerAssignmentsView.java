package gui;

import com.ubb.cms.utils.ReviewStatus;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Alexandra Muresan on 5/27/2017.
 */
public class ReviewerAssignmentsView extends BaseView{

    @FXML
    private ComboBox<String> reviewCombo;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(ReviewStatus status : ReviewStatus.values()){
            reviewCombo.getItems().add(status.toString());
        }
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

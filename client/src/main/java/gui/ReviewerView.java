package gui;

import com.ubb.cms.Paper;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Alexandra Muresan on 5/27/2017.
 */
public class ReviewerView extends BaseView {

    @FXML
    private TableView<Paper> availablePapers;

    @FXML
    private TableView<Paper> chosenPapers;

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

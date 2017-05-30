package gui;

import com.ubb.cms.User;
import com.ubb.cms.utils.UserTag;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Alexandra Muresan on 5/29/2017.
 */
public class SessionAssignView extends BaseView {
    @FXML
    ComboBox<String> combo;
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        for(User u : controller.getAllUsers()){
            System.out.println(u);
        }

    }

    @FXML
    public void backBtnHandler(){
        try{
            switchToView("session_requests.fxml","session_requests.css","Review Requests",loggedUser);
        }catch(Exception ex){
            ShowAlert.showAlert(ex.getMessage());
        }
    }
}

package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Marius Adam
 */
public class CreateView extends BaseView {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onCreateConf_clicked(ActionEvent actionEvent) {
        switchToView("createConf.fxml", "createConf.css", "Create conference");
    }

    public void onCreateEdition_clicked(ActionEvent actionEvent) {
        switchToView("createEdition.fxml", "createEdition.css", "Create edition");
    }

    @FXML
    public void logOutBtnHandler(){

        try{
            controller.logout(loggedUser.getUsername());
            switchToView("login.fxml","login.css","Conference Management System");
        }catch(Exception ex){
            handle(ex);
        }
    }

    @FXML
    public void reviewBtnHandler()
    {
        switchToView("session_requests.fxml", "session_requests.css", "ReviewRequest", loggedUser);
    }

    @FXML
    public void manageBtnHandler(){
        switchToView("session_edition.fxml", "session_edition.css", "Manage Editions", loggedUser);
    }


    public void pastDeadlineEditionsBtnHandler(ActionEvent actionEvent) {
        switchToView("past_deadline_editions.fxml", "past_deadline_editions.css", "Past deadline editions");
    }
}

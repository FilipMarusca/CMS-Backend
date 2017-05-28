package gui;

import com.ubb.cms.Conference;
import com.ubb.cms.Paper;
import com.ubb.cms.Review;
import com.ubb.cms.utils.ReviewStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import service.exception.ServiceException;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Alexandra Muresan on 5/27/2017.
 */
public class ReviewerAssignmentsView extends BaseView{

    @FXML
    private ComboBox<String> reviewCombo;
    @FXML
    private TableView<Paper> tabel;
    @FXML
    private TableColumn namePaperColumn;
    @FXML
    private TableColumn topicPaperColumn;
    @FXML
    private TextArea commentText;

    public ObservableList<Paper> model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(ReviewStatus status : ReviewStatus.values()){
            if(!(status==ReviewStatus.ConfirmedToBeReviewed ||status==ReviewStatus.WaitingForConfirmation))
                reviewCombo.getItems().add(status.toString());
        }
        namePaperColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        topicPaperColumn.setCellValueFactory(new PropertyValueFactory<>("topic"));
        model = FXCollections.observableArrayList();
        tabel.setItems(model);
    }

    @FXML
    public void backBtnHandler(){
        try{
            switchToView("reviewer_main.fxml","reviewer_main.css","Reviewer: "+loggedUser.getUsername(),loggedUser);
        }catch(Exception ex){
            ShowAlert.showAlert(ex.getMessage());
        }
    }
    @Override
    public void update() {
        model.clear();
        model.addAll(controller.getPapersToBeReviewed(loggedUser,ReviewStatus.ConfirmedToBeReviewed));
    }
    @FXML
    public void addReviewBtnHandler() {
        //the review has been already initialized when the reviewer made the request

        String s = commentText.getText().toString();
        Paper p = tabel.getSelectionModel().getSelectedItem();
        if (p != null) {
            try {


                ReviewStatus rs = ReviewStatus.valueOf(reviewCombo.getSelectionModel().getSelectedItem());
                try {
                    controller.updateReview(loggedUser, p, rs, s);
                } catch (ServiceException ex) {
                    ShowAlert.showAlert(ex.getMessage());
                }
                update();
                commentText.setText("");

            } catch (Exception ex) {
                ShowAlert.showAlert("Please select a tag for the review!");
            }
        } else {
            ShowAlert.showAlert("Please select the paper you want to review!");
        }
    }
}

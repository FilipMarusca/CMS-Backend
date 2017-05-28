package gui;

import com.ubb.cms.Paper;
import com.ubb.cms.Review;
import com.ubb.cms.utils.ReviewStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.exception.ServiceException;

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

    private ObservableList<Paper> availablePapersModel;
    private ObservableList<Paper> chosenPapersModel;

    @FXML
    private TableColumn namePaperColumnAvailable;
    @FXML
    private TableColumn topicPaperColumnAvailable;

    @FXML
    private TableColumn namePaperColumnChosen;
    @FXML
    private TableColumn topicPaperColumnChosen;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        namePaperColumnAvailable.setCellValueFactory(new PropertyValueFactory<>("title"));
        topicPaperColumnAvailable.setCellValueFactory(new PropertyValueFactory<>("topic"));
        namePaperColumnChosen.setCellValueFactory(new PropertyValueFactory<>("title"));
        topicPaperColumnChosen.setCellValueFactory(new PropertyValueFactory<>("topic"));

        availablePapersModel = FXCollections.observableArrayList();
        availablePapers.setItems(availablePapersModel);

        chosenPapersModel=FXCollections.observableArrayList();
        chosenPapers.setItems(chosenPapersModel);
    }
    @FXML
    public void addBtnHandler(){
        Paper p=availablePapers.getSelectionModel().getSelectedItem();
        if(p!=null){
            try {

                controller.addReview(loggedUser, p, ReviewStatus.WaitingForConfirmation, null);
                update();
            }catch(ServiceException ex){
                ShowAlert.showAlert(ex.getMessage());
            }
        }
        else{
            ShowAlert.showAlert("Please select one of available Papers!");
        }

    }
    @FXML
    public void deleteBtnHandler(){
        Paper p=chosenPapers.getSelectionModel().getSelectedItem();
        if(p!=null){
            Review r=controller.getReviewByReviewerAndPaper(loggedUser,p);
            if(r!=null){
                controller.deleteReview(r);
                update();
            }else{
                ShowAlert.showAlert("Ceva nu e bine!");
            }

        }
        else{
            ShowAlert.showAlert("Please select one of chosen Papers you want to remove!");
        }
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
            ShowAlert.showAlert(ex.getMessage()+ex.getStackTrace());
        }
    }
    @Override
    public void update(){
        availablePapersModel.clear();
        chosenPapersModel.clear();
        chosenPapersModel.addAll(controller.getPapersToBeReviewed(loggedUser, ReviewStatus.WaitingForConfirmation));
        availablePapersModel.addAll(controller.getPapersNotReviewed(loggedUser));

    }
}

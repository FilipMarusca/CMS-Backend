package gui;

import com.ubb.cms.Paper;
import com.ubb.cms.Review;
import com.ubb.cms.SessionChair;
import com.ubb.cms.User;
import com.ubb.cms.utils.PaperStatus;
import com.ubb.cms.utils.ReviewStatus;
import com.ubb.cms.utils.UserPaperEmb;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import service.exception.ServiceException;


import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Alexandra Muresan on 5/29/2017.
 */
public class SessionRequestsView extends BaseView {


    @FXML
    private TableView<Review> table;

    @FXML
    private TableColumn<Review, String> paperColumn;
    @FXML
    private TableColumn<Review, String> userColumn;

    @FXML
    private ObservableList<Review> model;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //paperColumn.setCellValueFactory(new PropertyValueFactory<>("UserPaperEmb.getUser().getUsername()"));


        //TableColumn<RelationManager, String> firstNameColumn = new TableColumn<>("First Name");
        /*paperColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Review,String>, ObservableValue<String>>() {


            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Review, String> data) {
                return data.getValue().getValue().getUserPaper().getPaper().getTopic();
            }
        });*/

         paperColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Review, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Review, String> c) {
                return new SimpleStringProperty(c.getValue().getUserPaper().getPaper().getTitle());
            }
        });

        userColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Review, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Review, String> c) {
                return new SimpleStringProperty(c.getValue().getUserPaper().getUser().getUsername());
            }
        });










        model = FXCollections.observableArrayList();
        table.setItems(model);
    }

    @FXML
    public void backBtnHandler(){
        try{
            switchToView("create.fxml","create.css","Session Chair",loggedUser);
        }catch(Exception ex){
            ShowAlert.showAlert(ex.getMessage());
        }
    }

    @FXML
    public void assignBtnHandler(){
        try{
            switchToView("session_assign.fxml","session_assign.css","Assign Reviewers",loggedUser);
        }catch(Exception ex){
            ShowAlert.showAlert(ex.getMessage());
        }
    }

    @FXML
    public void acceptButtonHandler()
    {
        if(table.getSelectionModel().getSelectedItem() == null)
        {
            ShowAlert.showAlert("No review selected");
        }
        try {
            Review review = table.getSelectionModel().getSelectedItem();
            System.out.println("initial: " + review);
            review.setStatus(ReviewStatus.ConfirmedToBeReviewed);
            System.out.println("after: " + review);
            controller.updateReview(review);

        }
        catch (ServiceException exception)
        {
            ShowAlert.showAlert("Review could not be confirmed");
        }


    }


    @Override
    public void update() {
        List<SessionChair> sessionChairs = controller.getAllSessionChairs();
        HashSet<Integer> editionIds = new HashSet<>();
        for(SessionChair sessionChair: sessionChairs)
        {
            if(sessionChair.getChair().getUser().getId() == loggedUser.getId())
            {
                int idEdition = sessionChair.getChair().getEdition().getId();
                editionIds.add(idEdition);
            }
        }
        System.out.println(editionIds);
        model.clear();
        for(Review review: controller.getAllReviews())
        {

            if(review.getStatus().equals(ReviewStatus.WaitingForConfirmation))
            {
                int editionId = review.getUserPaper().getPaper().getEdition().getId();
                System.out.println(editionId);
                if(editionIds.contains(editionId))
                {
                    model.add(review);
                }

            }
        }
    }



}

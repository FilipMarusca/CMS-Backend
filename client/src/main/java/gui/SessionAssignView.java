package gui;

import com.ubb.cms.Paper;
import com.ubb.cms.Review;
import com.ubb.cms.SessionChair;
import com.ubb.cms.User;
import com.ubb.cms.utils.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.jws.soap.SOAPBinding;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Alexandra Muresan on 5/29/2017.
 */
public class SessionAssignView extends BaseView {
    @FXML
    ComboBox<String> combo;
    @FXML
    private TableView<Paper> table;
    @FXML
    private TableColumn<Paper, String> paperNameColumn;
    @FXML
    private TableColumn<Paper, String> paperTopicColumn;


    @FXML
    private ObservableList<Paper> model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paperNameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        paperTopicColumn.setCellValueFactory(new PropertyValueFactory<>("topic"));



        model = FXCollections.observableArrayList();
        table.setItems(model);

    }


    public void assignButtonHandler()
    {
        if(table.getSelectionModel().getSelectedItem() == null)
        {
            ShowAlert.showAlert("No Paper selected");
            return;
        }

        if(combo.getSelectionModel().getSelectedItem() == null)
        {
            ShowAlert.showAlert("No Reviewer selected");
            return;
        }

        try {
            Paper paper = table.getSelectionModel().getSelectedItem();
            String username = combo.getSelectionModel().getSelectedItem();
            User selectedUser = null;
            for(User user: controller.getAllUsers())
            {
                if(user.getUsername().equals(username))
                {
                    selectedUser = user;
                    break;
                }

            }
            //WARNING: Because of primary key composed of two foreign keys, adding a review can fail
            controller.addReview(selectedUser, paper, ReviewStatus.ConfirmedToBeReviewed, null);
            ShowAlert.showOnSucces("Review assignement Done");
        }
        catch (Exception exception)
        {
            System.out.println(exception.getMessage());
            ShowAlert.showAlert(exception.getMessage());
        }
    }



    @FXML
    public void backBtnHandler(){
        try{
            switchToView("session_requests.fxml","session_requests.css","Review Requests",loggedUser);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            //ShowAlert.showAlert(ex.getMessage());
        }
    }
    @Override
    public void update() {
        model.clear();
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
        List<Paper> paperList = new ArrayList<>();
        for(Paper paper: controller.getAllPapers())
        {
            try {
                Integer editionId = paper.getEdition().getId();

                if(editionId != null && editionIds.contains(editionId) && paper.getStatus().equals(PaperStatus.InReview))
                {
                    System.out.println(editionId);
                    paperList.add(paper);
                }
            }
            catch (Exception exception)
            {
                System.out.println(exception.getMessage());
            }
        }
       model.setAll(paperList);




        for(User user: controller.getAllUsers())
        {

            if(user.getTag().toString().equals(UserTag.Reviewer.toString()))
            {
                combo.getItems().add(user.getUsername());
            }

        }


    }
}

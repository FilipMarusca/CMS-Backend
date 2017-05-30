package gui;

import com.ubb.cms.Paper;
import com.ubb.cms.Review;
import com.ubb.cms.utils.ReviewStatus;
import com.ubb.cms.utils.UserPaperEmb;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Alexandra Muresan on 5/30/2017.
 */
public class ReviewerReviewsView extends BaseView {

    @FXML
    TableView tabel;
    @FXML
    TableColumn paperName;
    @FXML
    TableColumn topic;
    @FXML
    TableColumn author;
    @FXML
    TextArea textArea;
    @FXML
    ObservableList<Paper> model;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        paperName.setCellValueFactory(new PropertyValueFactory<Paper,String>("title"));
        topic.setCellValueFactory(new PropertyValueFactory<Paper,String>("topic"));
        author.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Paper, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Paper, String> c) {
                return new SimpleStringProperty(c.getValue().getAuthor().getUsername());
            }
        });

        model = FXCollections.observableArrayList();
        tabel.setItems(model);
        tabel.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Paper>() {
            @Override
            public void changed(ObservableValue<? extends Paper> observable, Paper oldValue, Paper newValue) {
                seeStatus(newValue);
            }
        });
    }

    @FXML
    public void backBtnHandler(){
        try{
            switchToView("reviewer_main.fxml","reviewer_main.css","Reviewer: "+loggedUser.getUsername(),loggedUser);
        }catch(Exception ex){
            ShowAlert.showAlert(ex.getMessage());
        }
    }

    public List<Paper> myReviews(){

        List<Paper> result = new ArrayList<>();
        for(Review r : controller.getAllReviews()){
            UserPaperEmb emb = r.getUserPaper();
            if(emb.getUser().getUsername().compareTo(loggedUser.getUsername())==0 && (r.getStatus().compareTo(ReviewStatus.ConfirmedToBeReviewed)!=0 && r.getStatus().compareTo(ReviewStatus.WaitingForConfirmation)!=0)){
                result.add(emb.getPaper());
            }
        }

        return result;
    }

    @Override
    public void update(){
        model.clear();
        List<Paper> papers = myReviews();
        model.addAll(papers);
    }

    public void seeStatus(Paper paper){

        List<String> reviews = new ArrayList<>();
        int paperId = paper.getId();
        for(Review r : controller.getAllReviews()){
            UserPaperEmb emb = r.getUserPaper();
            Paper p = emb.getPaper();
            if(p.getId() == paperId && (r.getStatus().compareTo(ReviewStatus.ConfirmedToBeReviewed)!=0 && r.getStatus().compareTo(ReviewStatus.WaitingForConfirmation)!=0)){
                reviews.add(r.getStatus().toString());
            }
        }

        String result="";
        for(String rev : reviews){
            result+= rev+"\n";
        }
        textArea.setText(result);

    }
}

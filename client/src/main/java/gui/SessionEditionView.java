package gui;

import com.ubb.cms.ConferenceSession;
import com.ubb.cms.Edition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.exception.ServiceException;
import utils.DateUtils;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Alexandra Muresan on 6/3/2017.
 */
public class SessionEditionView extends BaseView {

    @FXML
    private TableView<Edition> editionTable;
    @FXML
    private TableColumn<Edition,String> nameEditionColumn;

    private ObservableList<Edition> model;

    @FXML
    private TextField location;
    @FXML
    public DatePicker dateSession;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameEditionColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        model= FXCollections.observableArrayList();
        editionTable.setItems(model);
    }


    @FXML
    public void backBtnHandler(){
        try{
            switchToView("create.fxml","create.css","Session Chair: "+loggedUser.getUsername(),loggedUser);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    @FXML
    public void addBtnHandler(){
        Edition e=editionTable.getSelectionModel().getSelectedItem();
        if((!(location.getText().equals("")))||location!=null){
            if(dateSession.getValue()!=null){
                if(e!=null) {
                    try {
                        controller.addSession(new ConferenceSession(controller.getAllSessions().size()+1,e, DateUtils.asDate(dateSession.getValue()),location.getText()));
                        ShowAlert.showOnSucces("Session succesfully created!");
                    } catch (ServiceException ex) {
                        ShowAlert.showAlert(ex.getMessage());
                    }
                }else{
                    ShowAlert.showAlert("Please select an edition!");
                }}
            else{
                ShowAlert.showAlert("Please select a date!");
            }
        }
        else{
            ShowAlert.showAlert("Please enter the location!");
        }
    }


    @FXML
    public void addPapersBtnHandler(){
        try{
            switchToView("session_add_papers.fxml","session_add_papers.css","Add papers",loggedUser);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    @Override
    public void update(){
        model.clear();
        model.addAll(controller.getEditionForChair(loggedUser));
    }
}

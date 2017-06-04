package gui;

import com.ubb.cms.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.hibernate.Session;
import service.exception.ServiceException;

import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * Created by Alexandra Muresan on 6/3/2017.
 */
public class SessionAddPapersView extends BaseView {

    @FXML
    private TableView<ConferenceSession> sessionTableView;
    @FXML
    private TableView<Paper> paperTableView;
    @FXML
    private TableColumn<ConferenceSession,String> sessionLocation;
    @FXML
    private TableColumn<ConferenceSession,String> editionName;
    @FXML
    private TableColumn<Paper,String> paperNameColumn;
    @FXML
    private TableColumn<Paper,String> topicColumn;
    private ObservableList<ConferenceSession> modelSession;
    private ObservableList<Paper> modelPaper;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sessionLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        paperNameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        topicColumn.setCellValueFactory(new PropertyValueFactory<>("topic"));
        editionName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ConferenceSession, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ConferenceSession, String> param) {
                return new SimpleStringProperty(param.getValue().getEdition().getName());
            }
        });
        modelSession= FXCollections.observableArrayList();
        sessionTableView.setItems(modelSession);

        modelPaper=FXCollections.observableArrayList();
        paperTableView.setItems(modelPaper);
    }
    @FXML
    public void changeSessionForPaper(){
        ConferenceSession s=sessionTableView.getSelectionModel().getSelectedItem();
        Paper p=paperTableView.getSelectionModel().getSelectedItem();
        if(p!=null){
            if(s!=null){
                p.setSession(s);

                try {
                    controller.updatePaper(p);
                    ShowAlert.showOnSucces("Paper Session was Defined!");
                    update();
                } catch (ServiceException e) {
                    ShowAlert.showAlert(e.getMessage());
                }
            }
            else{
                ShowAlert.showAlert("Select a session!!!");
            }
        }else{
            ShowAlert.showAlert("Select a paper!!");
        }
    }
    @FXML
    public void backBtnHandler(){
        try{
            switchToView("session_edition.fxml","session_edition.css","Manage Editions",loggedUser);
        }catch(Exception ex){
            ex.getMessage();
        }
    }
    @FXML
    public void getPapersByEdition(){
        ConferenceSession s=sessionTableView.getSelectionModel().getSelectedItem();
        modelPaper.clear();
        for (Paper p:
                controller.getAllPapersFromEndedConferenceByChair(loggedUser)){
            if(p.getEdition().getId()==s.getEdition().getId()){
                modelPaper.add(p);
            }
        }
    }
    @Override
    public void update(){
        modelSession.clear();
        modelPaper.clear();
        List<Edition> editions=controller.getEditionForChair(loggedUser);
        for (Edition e:
             editions) {
            for (ConferenceSession s:controller.getAllSessions()
                 ) {
                if(s.getEdition().getId()==e.getId()){
                    modelSession.add(s);
                }
            }
        }
    }

}

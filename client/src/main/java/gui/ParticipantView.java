package gui;

import com.ubb.cms.Conference;
import com.ubb.cms.Edition;
import com.ubb.cms.Participation;
import com.ubb.cms.utils.UserEditionEmb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.exception.ServiceException;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Alexandra Muresan on 5/27/2017.
 */
public class ParticipantView extends BaseView {

    @FXML
    private TableColumn<Edition, Date> endingDateColumn;
    @FXML
    private TableColumn<Edition, Date> beginningDateColumn;
    @FXML
    private TableColumn<Edition, String> editionNameColumn;
    @FXML
    private ObservableList<Edition> editions;
    @FXML
    private TableView<Edition> editionTable;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editionNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        endingDateColumn.setCellValueFactory(new PropertyValueFactory<>("beginningDate"));
        beginningDateColumn.setCellValueFactory(new PropertyValueFactory<>("endingDate"));


        editions = FXCollections.observableArrayList();
        editions.clear();
        editionTable.setItems(editions);
    }


    @Override
    public void update() {
        try {
            editions.clear();
            editions.addAll(controller.getAllEdition());
        } catch (Exception exception) {
            ShowAlert.showAlert("Failed to load papers");
            ShowAlert.showAlert(exception.getMessage());
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
    public void confirmParticipation()
    {
        if(editionTable.getSelectionModel().getSelectedItem() == null)
        {
            ShowAlert.showOnSucces("No edition selected");
            return;
        }
        Edition edition = editionTable.getSelectionModel().getSelectedItem();
        UserEditionEmb userEditionEmb = new UserEditionEmb(loggedUser, edition);
        Participation participation = new Participation(userEditionEmb, false);
        try {
            controller.addParticipation(participation);
            ShowAlert.showOnSucces("Participation Added");

        }
        catch (ServiceException serviceException)
        {
            ShowAlert.showAlert("Participation could not be added");
        }

    }




}

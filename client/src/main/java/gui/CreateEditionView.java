package gui;

import com.ubb.cms.Conference;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Marius Adam
 */
public class CreateEditionView extends BaseView {
    public DatePicker                      submissionDeadlineField;
    public DatePicker                      finalDeadlineField;
    public DatePicker                      startDateField;
    public DatePicker                      endDateField;
    public TextField                       editionNameField;
    public TableColumn<Conference, String> confNameColumn;
    public ObservableList<Conference>      conferences;
    public TableView<Conference>           conferencesTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conferences = FXCollections.observableArrayList();
        confNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        conferencesTable.setItems(conferences);
    }

    public void onBackBtn_clicked(ActionEvent actionEvent) throws IOException {
        switchToView("create.fxml", "create.css", "Session chair");
    }

    public void onCreateBtn_clicked(ActionEvent actionEvent) {
        try {
//            controller.addEdition();
        } catch (Exception e) {
            ShowAlert.showAlert(e.getMessage());
        }
    }

    @Override
    public void update() {
        loadConferences();
    }

    protected void loadConferences() {
        try {
            conferences.clear();
            conferences.addAll(controller.getAllConferences());
        } catch (Exception e) {
            ShowAlert.showOnSucces("Failed to load conferences." + System.lineSeparator());
            ShowAlert.showOnSucces(e.getMessage());
            e.printStackTrace();
        }
    }
}

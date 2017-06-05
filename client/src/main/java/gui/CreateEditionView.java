package gui;

import com.ubb.cms.Conference;
import com.ubb.cms.Edition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.DateUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Marius Adam
 */
public class CreateEditionView extends BaseView {
    public DatePicker                   submissionDeadlineField;
    public DatePicker                   finalDeadlineField;
    public DatePicker                   startDateField;
    public DatePicker                   endDateField;
    public TextField                    editionNameField;
    public ComboBox<Conference>         conferencesComboBox;
    public TableColumn<Edition, String> nameCol;
    public TableColumn<Edition, String> submitDeadlineCol;
    public TableColumn<Edition, String> finalDeadlineCol;
    public TableColumn<Edition, String> startDateCol;
    public TableColumn<Edition, String> endDateCol;
    public TableView<Edition>           conferenceEditionsTable;

    private ObservableList<Conference> conferences;
    private ObservableList<Edition>    conferenceEditions;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conferences = FXCollections.observableArrayList();
        conferencesComboBox.setItems(conferences);

        conferenceEditions = FXCollections.observableArrayList();
        conferenceEditionsTable.setItems(conferenceEditions);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        submitDeadlineCol.setCellValueFactory(DateUtils.dateAsStringValueFactory("paperSubmissionDeadline"));
        finalDeadlineCol.setCellValueFactory(DateUtils.dateAsStringValueFactory("finalDeadline"));
        startDateCol.setCellValueFactory(DateUtils.dateAsStringValueFactory("beginningDate"));
        endDateCol.setCellValueFactory(DateUtils.dateAsStringValueFactory("endingDate"));
    }

    public void onBackBtn_clicked(ActionEvent actionEvent) throws IOException {
        switchToView("create.fxml", "create.css", "Session chair");
    }

    public void onCreateBtn_clicked(ActionEvent actionEvent) {
        try {
            Conference selectedConference = conferencesComboBox.getSelectionModel().getSelectedItem();
            Integer editionId = controller.addEdition(
                    selectedConference,
                    startDateField.getValue(),
                    endDateField.getValue(),
                    editionNameField.getText(),
                    submissionDeadlineField.getValue(), finalDeadlineField.getValue(), loggedUser
            );

            Edition addedEdition = controller.getEditionById(editionId);
            ShowAlert.showOnSucces(String.format(
                    "Edition %s for conference %s, was created.",
                    addedEdition.getName(),
                    addedEdition.getConference().getName()
            ));
        } catch (Exception e) {
            ShowAlert.handle(e);
        }
    }

    @Override
    public void update() {
        Conference selected = conferencesComboBox.getSelectionModel().getSelectedItem();
        loadConferences();
        conferencesComboBox.getSelectionModel().select(selected);
        loadRelatedEditions();
    }

    public void onConferenceSelected(ActionEvent actionEvent) {
        loadRelatedEditions();
    }

    private void loadRelatedEditions() {
        Conference selected = conferencesComboBox.getSelectionModel().getSelectedItem();
        if (selected != null) {
            conferenceEditions.clear();
            try {
                conferenceEditions.addAll(controller.getEditions(selected));
            } catch (Exception e) {
                handle(e, String.format("Failed to load conference '%s' editions", selected.getName()));
            }
        }
    }

    private void loadConferences() {
        try {
            conferences.clear();
            conferences.addAll(controller.getAllConferences());
        } catch (Exception e) {
            handle(e, "Failed to load conferences");
        }
    }
}

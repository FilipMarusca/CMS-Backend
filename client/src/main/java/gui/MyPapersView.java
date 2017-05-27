package gui;

import com.ubb.cms.Edition;
import com.ubb.cms.Paper;
import com.ubb.cms.utils.PaperStatus;
import com.ubb.cms.utils.PaperTopics;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Alexandra Muresan on 5/23/2017.
 */
public class MyPapersView extends BaseView {

    @FXML
    private TableView<Paper> table;
    @FXML
    private TableColumn<Paper, String> paperNameColumn;
    @FXML
    private TableColumn<Paper, PaperTopics> topicColumn;
    @FXML
    private TableColumn<Paper, Edition> editionColumn;
    @FXML
    private TableColumn<Paper, PaperStatus> statusColumn;
    @FXML
    private ObservableList<Paper> model;

    private Stage currentStage;

    @Override
    public void update() {
        try {
            model.clear();
            model.addAll(controller.getAllPapers());
        } catch (Exception exception) {
            ShowAlert.showAlert("Failed to load papers");
            ShowAlert.showAlert(exception.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paperNameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        topicColumn.setCellValueFactory(new PropertyValueFactory<>("topic"));
        editionColumn.setCellValueFactory(new PropertyValueFactory<>("edition_id"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        model = FXCollections.observableArrayList();
        table.setItems(model);
    }

    @FXML
    public void goBackHandler() {
        try {
            switchToView("author.fxml", "author.css", "Author");
        } catch (Exception ex) {
            ShowAlert.showAlert(ex.getMessage());
        }
    }

    @FXML
    public void downloadHandler() {
        if (table.getSelectionModel().getSelectedItem() == null) {
            ShowAlert.showAlert("Select a paper first");
            return;
        }

        showSaveFileDialog();
    }

    private void showSaveFileDialog() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(currentStage);

        if(file != null){
            System.out.println("SAVE FILE!!!");
        }
    }
}

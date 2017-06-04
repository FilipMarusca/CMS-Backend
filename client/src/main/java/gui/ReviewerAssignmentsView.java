package gui;

import com.ubb.cms.Paper;
import com.ubb.cms.utils.ReviewStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import service.exception.ServiceException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Alexandra Muresan on 5/27/2017.
 */
public class ReviewerAssignmentsView extends BaseView {

    public ObservableList<Paper> model;
    @FXML
    private ComboBox<String> reviewCombo;
    @FXML
    private TableView<Paper> tabel;
    @FXML
    private TableColumn namePaperColumn;
    @FXML
    private TableColumn topicPaperColumn;
    @FXML
    private TextArea commentText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (ReviewStatus status : ReviewStatus.values()) {
            if (!(status == ReviewStatus.ConfirmedToBeReviewed || status == ReviewStatus.WaitingForConfirmation))
                reviewCombo.getItems().add(status.toString());
        }
        namePaperColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        topicPaperColumn.setCellValueFactory(new PropertyValueFactory<>("topic"));
        model = FXCollections.observableArrayList();
        tabel.setItems(model);
    }

    @FXML
    public void backBtnHandler() {
        try {
            switchToView("reviewer_main.fxml", "reviewer_main.css", "Reviewer: " + loggedUser.getUsername(), loggedUser);
        } catch (Exception ex) {
            ShowAlert.showAlert(ex.getMessage());
        }
    }

    @Override
    public void update() {
        model.clear();
        model.addAll(controller.getPapersToBeReviewed(loggedUser, ReviewStatus.ConfirmedToBeReviewed));
    }

    @FXML
    public void addReviewBtnHandler() {
        //the review has been already initialized when the reviewer made the request

        String s = commentText.getText().toString();
        Paper p = tabel.getSelectionModel().getSelectedItem();
        if (p != null) {
            try {


                ReviewStatus rs = ReviewStatus.valueOf(reviewCombo.getSelectionModel().getSelectedItem());
                try {
                    controller.updateReview(loggedUser, p, rs, s);
                } catch (ServiceException ex) {
                    ShowAlert.showAlert(ex.getMessage());
                }
                update();
                commentText.setText("");

            } catch (Exception ex) {
                ShowAlert.showAlert("Please select a tag for the review!");
            }
        } else {
            ShowAlert.showAlert("Please select the paper you want to review!");
        }
    }

    @FXML
    public void downloadSummaryHandler() {
        if (tabel.getSelectionModel().getSelectedItem() == null) {
            ShowAlert.showAlert("Select a summary first");
            return;
        }

        showSaveFileDialog(tabel.getSelectionModel().getSelectedItem().getSummaryPDF());
    }

    @FXML
    public void downloadBtnHandler() {
        if (tabel.getSelectionModel().getSelectedItem() == null) {
            ShowAlert.showAlert("Select a paper first");
            return;
        }

        showSaveFileDialog(tabel.getSelectionModel().getSelectedItem().getPaperPDF());
    }

    private void showSaveFileDialog(byte[] pdfData) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(currentStage);

        if (file != null) {
            saveFile(pdfData, file);
        }
    }

    private void saveFile(byte[] pdfData, File file) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(pdfData);
            fileOutputStream.close();

            System.out.println("SAVE SUCCESFUL");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("SAVE FAILED");
        }
    }
}

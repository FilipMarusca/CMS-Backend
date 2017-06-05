package gui;

import com.ubb.cms.Edition;
import com.ubb.cms.Paper;
import com.ubb.cms.Review;
import com.ubb.cms.User;
import com.ubb.cms.utils.PaperStatus;
import com.ubb.cms.utils.PaperTopics;
import com.ubb.cms.utils.ReviewStatus;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import service.exception.ServiceException;
import utils.DateUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * @author Marius Adam
 */
public class PastDeadlineEditionsView extends BaseView {
    private final List<Edition>           editionList                   = new ArrayList<>();
    private final List<Paper>             paperList                     = new ArrayList<>();
    private final List<Review>            reviewList                    = new ArrayList<>();
    private final ObservableList<Edition> filteredEditionObservableList = FXCollections.observableArrayList();
    private final ObservableList<Paper>   filteredPaperObservableList   = FXCollections.observableArrayList();
    private final ObservableList<Review>  filteredReviewObservableList  = FXCollections.observableArrayList();
    public TextField                         searchEditionsField;
    public TableView<Edition>                editionsTable;
    public TableColumn<Edition, String>      editionNameCol;
    public TableColumn<Edition, String>      editionSubDeadlineCol;
    public TextField                         searchPapersField;
    public TableView<Paper>                  papersTable;
    public TableColumn<Paper, String>        paperTitleCol;
    public TableColumn<Paper, PaperTopics>   paperTopicCol;
    public TableColumn<Paper, PaperStatus>   paperStatusCol;
    public TableColumn<Paper, User>          paperAuthorCol;
    public TableView<Review>                 reviewsTable;
    public TableColumn<Review, ReviewStatus> reviewStatusCol;
    public TableColumn<Review, String>       reviewerCol;
    public TextArea                          commentTextArea;
    public TextField                         searchReviewsField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editionNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        editionSubDeadlineCol.setCellValueFactory(DateUtils.dateAsStringValueFactory("paperSubmissionDeadline"));

        paperTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        paperTopicCol.setCellValueFactory(new PropertyValueFactory<>("topic"));
        paperStatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        paperAuthorCol.setCellValueFactory(new PropertyValueFactory<>("author"));

        reviewStatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        reviewerCol.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(param.getValue().getUserPaper().getUser().toString());
            return property;
        });

        editionsTable.setItems(filteredEditionObservableList);
        papersTable.setItems(filteredPaperObservableList);
        reviewsTable.setItems(filteredReviewObservableList);
    }

    public void backBtnHandler(ActionEvent actionEvent) {
        switchToView("create.fxml", "create.css", "Session Chair: " + loggedUser.getUsername());
    }

    public void searchEditionsHandler(KeyEvent keyEvent) {
        searchEditions();
    }

    private void searchEditions() {
        String text = searchEditionsField.getText().toUpperCase().trim();
        Edition selected = editionsTable.getSelectionModel().getSelectedItem();

        filteredEditionObservableList.clear();
        if (!text.isEmpty()) {
            List<Edition> filtered = editionList
                    .stream()
                    .filter(edition -> edition.getName().toUpperCase().contains(text))
                    .collect(Collectors.toList());

            filteredEditionObservableList.addAll(filtered);
        } else {
            filteredEditionObservableList.addAll(editionList);
        }

        editionsTable.getSelectionModel().select(selected);
    }

    public void searchPapersHandler(KeyEvent keyEvent) {
        searchPapers();
    }

    private void searchPapers() {
        String text = searchPapersField.getText().toUpperCase().trim();
        Paper selected = papersTable.getSelectionModel().getSelectedItem();

        filteredPaperObservableList.clear();
        if (!text.isEmpty()) {
            filteredPaperObservableList.addAll(paperList.stream().filter(paper -> {
                if (paper.getAuthor().toString().toUpperCase().contains(text)) {
                    return true;
                }
                if (paper.getTitle().toUpperCase().contains(text)) {
                    return true;
                }
                if (paper.getTopic().toString().toUpperCase().contains(text)) {
                    return true;
                }
                if (paper.getStatus().toString().toUpperCase().contains(text)) {
                    return true;
                }
                return false;
            }).collect(Collectors.toList()));
        } else {
            filteredPaperObservableList.addAll(paperList);
        }

        papersTable.getSelectionModel().select(selected);
    }

    public void searchReviewsHandler(KeyEvent keyEvent) {
        searchReviews();
    }

    private void searchReviews() {
        String text = searchReviewsField.getText().toUpperCase().trim();
        Review selected = reviewsTable.getSelectionModel().getSelectedItem();

        filteredReviewObservableList.clear();
        if (!text.isEmpty()) {
            List<Review> filtered = reviewList.stream().filter(review -> {
                if (review.getStatus().toString().toUpperCase().contains(text)) {
                    return true;
                }
                if (review.getUserPaper().getUser().toString().toUpperCase().contains(text)) {
                    return true;
                }
                return false;
            }).collect(Collectors.toList());

            filteredReviewObservableList.addAll(filtered);
        } else {
            filteredReviewObservableList.addAll(reviewList);
        }

        reviewsTable.getSelectionModel().select(selected);
    }

    @Override
    public void update() {
        loadEditions();
        loadPapers();
        loadReviews();
        searchEditions();
        searchPapers();
        searchReviews();
    }

    private void loadEditions() {
        editionList.clear();
        try {
            editionList.addAll(controller.getPastSubmissionEditions(loggedUser));
        } catch (ServiceException e) {
            handle(e, "Failed to lad editions.");
        }
    }

    private void loadPapers() {
        Edition selectedEdition = editionsTable.getSelectionModel().getSelectedItem();
        if (selectedEdition == null) {
            return;
        }

        paperList.clear();
        try {
            paperList.addAll(controller.getPapers(selectedEdition));
        } catch (ServiceException e) {
            handle(e, "Failed to load papers.");
        }
    }

    private void loadReviews() {
        Paper selectedPaper = papersTable.getSelectionModel().getSelectedItem();
        if (selectedPaper == null) {
            return;
        }

        reviewList.clear();
        try {
            reviewList.addAll(controller.getReviews(selectedPaper));
        } catch (ServiceException e) {
            handle(e, "Failed to load reviews.");
        }

    }

    public void onReviewSelectedHandler(MouseEvent mouseEvent) {
        System.out.println("entered onReviewSelectedHandler");
        Review selected = reviewsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            commentTextArea.setText(selected.getComment());
        }
    }

    public void onPaperSelectedHandler(MouseEvent mouseEvent) {
        System.out.println("entered onPaperSelectedHandler");
        loadReviews();
        searchReviews();
    }

    public void editionSelectedHandler(MouseEvent mouseEvent) {
        System.out.println("entered editionSelectedHandler");
        loadPapers();
        searchPapers();
    }
}

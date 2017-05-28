package gui;

import com.ubb.cms.Edition;
import com.ubb.cms.Paper;
import com.ubb.cms.User;
import com.ubb.cms.utils.PaperStatus;
import com.ubb.cms.utils.PaperTopics;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Alexandra Muresan on 5/22/2017.
 */
public class AuthorView extends BaseView {
    private static final String DEFAULTPAPERSTATUS = "WaitingForReview";

    @FXML
    private TextField titleText;
    @FXML
    private Button uploadBtn;
    @FXML
    private TableView<Edition> table;
    @FXML
    private TableColumn<Edition, String> nameColumn;
    @FXML
    private TableColumn<Edition, java.util.Date> submissionDeadlineColumn;
    @FXML
    private TableColumn<Edition, java.util.Date> beginingDateColumn;
    @FXML
    private TableColumn<Edition, java.util.Date> endingDateColumn;
    @FXML
    private ObservableList<Edition> model;
    @FXML
    private ComboBox<String> topicComboBox;
    @FXML
    private Button logOutBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logger.info("intra la initialize");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        submissionDeadlineColumn.setCellValueFactory(new PropertyValueFactory<>("paperSubmissionDeadline"));
        beginingDateColumn.setCellValueFactory(new PropertyValueFactory<>("beginningDate"));
        endingDateColumn.setCellValueFactory(new PropertyValueFactory<>("endingDate"));
        model = FXCollections.observableArrayList();
        table.setItems(model);

        for (PaperTopics topic : PaperTopics.values()) {
            topicComboBox.getItems().add(topic.toString());
        }
    }

    @FXML
    public void uploadHandler() {
        String topic = topicComboBox.getSelectionModel().getSelectedItem();
        logger.info(topic);
        String title = titleText.getText();


        //TODO fix the bug where the application crashes when no file is selected
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(currentStage);
        logger.info(Long.toString(file.length()));


        try {
            final byte[] buffer = new byte[(int) (file.length())];
            final InputStream in = new FileInputStream(file);
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            logger.info(file.toString());

            int read = -1;
            while ((read = in.read(buffer)) > 0) {
                baos.write(buffer, 0, read);
            }
            in.close();

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

            byte[] pdfData = new byte[(int) file.length()];
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            dis.readFully(pdfData);  // read from file into byte[] array
            dis.close();

            User author = controller.getUserById(loggedUser.getId());

            Paper paper = new Paper(author, null, PaperStatus.InReview, title, topic, pdfData);
            controller.addPaper(paper);

        } catch (Exception exception) {
            logger.warning(exception.getMessage());
        }
    }

    @Override
    public void update() {
        try {
            //System.out.println("nr editii" + controller.getAllEdition().size());
            model.clear();
            model.addAll(controller.getEditionAfterDate(new Date()));

        } catch (Exception exception) {
            ShowAlert.showAlert("Failed to load editions");
            ShowAlert.showAlert(exception.getMessage());
        }

    }

    @FXML
    public void logOutHandler() {
        defaultLogoutHandler();
    }

    @FXML
    public void myPapersBtnHandler() throws IOException {
        switchToView("myPapers.fxml", "create.css", "Author");
    }
}

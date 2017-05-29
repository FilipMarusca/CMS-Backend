package gui;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMessages_zh_CN;
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
import org.springframework.stereotype.Service;
import service.exception.ServiceException;

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

    byte[] pdfData;


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

        //logger.info(topic);



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

            pdfData = new byte[(int) file.length()];
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            dis.readFully(pdfData);  // read from file into byte[] array
            dis.close();




           // Paper paper = new Paper(author, edition, PaperStatus.InReview, title, topic, pdfData);
            //controller.addPaper(paper);

        } catch (Exception exception) {
            logger.warning(exception.getMessage());
        }
    }


    @FXML
    void submitPaper()
    {
        if(table.getSelectionModel().getSelectedItem() == null)
        {
            ShowAlert.showAlert("There is no selected Edition");
            return;
        }
        if(pdfData == null || pdfData.length == 0)
        {
            ShowAlert.showAlert("No PDF uploaded");
            return;
        }
        if(topicComboBox.getSelectionModel().getSelectedItem() == null)
        {
            ShowAlert.showAlert("No topic selected");
            return;
        }

        if(titleText.getText() == null)
        {
            ShowAlert.showAlert("No title selected");
            return;
        }

        try {
            String title = titleText.getText();
            String topic = topicComboBox.getSelectionModel().getSelectedItem();
            Edition edition = table.getSelectionModel().getSelectedItem();
            User author = controller.getUserById(loggedUser.getId());
            Paper paper = new Paper(author, edition, PaperStatus.InReview, title, topic, pdfData);
            controller.addPaper(paper);
            ShowAlert.showOnSucces("Paper Succesfully added");
        }
        catch (ServiceException exception)
        {
            ShowAlert.showAlert("Paper could not be added");
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

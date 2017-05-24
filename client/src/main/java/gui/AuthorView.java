package gui;

import client.ClientController;
import com.ubb.cms.Edition;
import com.ubb.cms.utils.PaperTopics;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Alexandra Muresan on 5/22/2017.
 */
public class AuthorView extends BaseView {

    private static final String DEFAULTPAPERSTATUS = "WaitingForReview";
    @FXML
    TextField titleText;
    @FXML
    Button    uploadBtn;
    private ClientController                     controller;
    private Stage                                currentStage;
    private int                                  authorId;
    @FXML
    private TableView<Edition>                   table;
    @FXML
    private TableColumn<Edition, String>         nameColumn;
    @FXML
    private TableColumn<Edition, java.util.Date> submissionDeadlineColumn;
    @FXML
    private TableColumn<Edition, java.util.Date> beginingDateColumn;
    @FXML
    private TableColumn<Edition, java.util.Date> endingDateColumn;
    @FXML
    private ObservableList<Edition>              model;
    @FXML
    private ComboBox<String>                     topicComboBox;

    @FXML
    private Button logOutBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("intra la initialize");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        submissionDeadlineColumn.setCellValueFactory(new PropertyValueFactory<>("paperSubmissionDeadline"));
        beginingDateColumn.setCellValueFactory(new PropertyValueFactory<>("beginningDate"));
        endingDateColumn.setCellValueFactory(new PropertyValueFactory<>("endingDate"));

        for (PaperTopics topic : PaperTopics.values()) {
            topicComboBox.getItems().add(topic.toString());
        }
    }

    @FXML
    public void uploadHandler() {
        String topic = topicComboBox.getSelectionModel().getSelectedItem();
        System.out.println(topic);
        String title = titleText.getText();


        //TODO fix the bug where the application crashes when no file is selected
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(currentStage);
        System.out.println(file.length());


        try {
            final byte[] buffer = new byte[(int) (file.length())];
            final InputStream in = new FileInputStream(file);
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            System.out.println(file);

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


            //System.out.println(bais);
            //System.out.println(bais.toString());

            /*User author = controller.getUserById(authorId);
            System.out.println(author);
            Blob blob = new javax.sql.rowset.serial.SerialBlob(pdfData);
            Paper paper = new Paper(author, null, PaperStatus.InReview, title, topic, blob );
            controller.addPaper(paper);
            System.out.println("trece de add");

            */

        } catch (Exception exception) {
            System.out.println("intra la exceptie");
            System.out.println(exception.getMessage());
        }


    }


    /*private byte[] getByteArrayFromFile(final Document handledDocument) throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final InputStream in = new FileInputStream(handledDocument);
        final byte[] buffer = new byte[500];
        int read = -1;
        while ((read = in.read(buffer)) > 0) {
            baos.write(buffer, 0, read);
        }
        in.close();
        return baos.toByteArray();
    }*/
    public void setController(ClientController clientController, Stage currentStage, int authorId) {
        this.controller = clientController;
        this.currentStage = currentStage;
        this.authorId = authorId;

        for (Edition edition : controller.getAllEdition()) {
            //System.out.println(edition);
        }

        model = FXCollections.observableArrayList(controller.getAllEdition());
        table.setItems(model);
    }

    @Override
    public void update() {

    }

    @FXML
    public void logOutHandler() {

    }

    @FXML
    public void myPapersBtnHandler() {
        defaultLogoutHandler();
    }
}

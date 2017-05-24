package gui;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Marius Adam
 */
public class CreateView extends BaseView {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onCreateConf_clicked(ActionEvent actionEvent) {
        try {
            switchToView("createConf.fxml", "createConf.css", "Create conference");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onCreateEdition_clicked(ActionEvent actionEvent) {
        try {
            switchToView("createEdition.fxml", "createEdition.css", "Create edition");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

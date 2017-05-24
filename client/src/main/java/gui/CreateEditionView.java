package gui;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Marius Adam
 */
public class CreateEditionView extends BaseView {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onBackBtn_clicked(ActionEvent actionEvent) throws IOException {
        switchToView("create.fxml", "create.css", "Session chair");
    }
}

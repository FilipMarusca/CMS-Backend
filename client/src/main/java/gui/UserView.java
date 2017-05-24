package gui;

import client.ClientController;
import com.ubb.cms.User;
import com.ubb.cms.utils.UserTag;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by Raul on 02/05/2017.
 */
public class UserView extends BaseView {
    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, String> tagColumn;

    @FXML
    private ComboBox<UserTag> tagComboBox;
    @FXML
    private Button            updateButton;


    @FXML
    private ObservableList<User> model;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        tagColumn.setCellValueFactory(new PropertyValueFactory<>("tag"));
        tagComboBox.getItems().addAll(UserTag.values());
    }


    public void updateButtonHandler() {
        boolean userSelected = table.getSelectionModel().getSelectedItem() != null;
        boolean tagSelected = tagComboBox.getSelectionModel().getSelectedItem() != null;
        if (userSelected && tagSelected) {
            User user = table.getSelectionModel().getSelectedItem();
            try {
                //System.out.println(tagComboBox.getSelectionModel().toString());
                user.setTag(tagComboBox.getSelectionModel().getSelectedItem());
                controller.updateUser(user, user.getId());

            }
            catch (Exception exception) {
                ShowAlert.showAlert(exception.getMessage());

            }
        } else {
            ShowAlert.showAlert("There is no User or no Tag selected");
        }

    }


    @Override
    public void setController(ClientController controller) {
        super.setController(controller);
        model = FXCollections.observableArrayList(controller.getAllUsers());
        table.setItems(model);
    }

    @Override
    public void update() {
        model = FXCollections.observableArrayList(controller.getAllUsers());
        table.setItems(model);
    }

    @FXML
    public void logOutBtnHandler() {
        defaultLogoutHandler();
    }
}

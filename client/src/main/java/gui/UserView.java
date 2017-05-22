package gui;

import client.ClientController;
import com.ubb.cms.User;
import com.ubb.cms.utils.UserTags;
import exception.ServiceException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utils.Observer;


/**
 * Created by Raul on 02/05/2017.
 */




public class UserView implements Observer<User> {
    private ClientController controller;

    private Stage currentStage;

    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, String> tagColumn;

    @FXML
    private ComboBox<String> tagComboBox;
    @FXML
    private Button updateButton;



    @FXML
    private ObservableList<User> model;


    @FXML
    public void initialize() {

        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        tagColumn.setCellValueFactory(new PropertyValueFactory<User, String>("tag"));

        for(UserTags userTags: UserTags.values())
        {
            tagComboBox.getItems().add(userTags.toString());
        }


    }

    public void updateButtonHandler()
    {
        if(table.getSelectionModel().getSelectedItem() == null || tagComboBox.getSelectionModel().getSelectedItem() == null)
        {
            ShowAlert.showAlert("There is no User or no Tag selected");
            return;
        }
        else
        {
            User user = table.getSelectionModel().getSelectedItem();
            try {
                //System.out.println(tagComboBox.getSelectionModel().toString());
                String newTag = tagComboBox.getSelectionModel().getSelectedItem();
                //System.out.println(newTag);
                user.setTag(newTag);
                controller.updateUser(user, user.getId());

            }
            //catch (ServiceException exception)
                    catch (Exception exception)
            {
                ShowAlert.showAlert(exception.getMessage());

            }

        }

    }


    public void setController(ClientController clientController, Stage currentStage)
    {
        this.controller = clientController;
        this.currentStage = currentStage;

        model = FXCollections.observableArrayList(controller.getAllUsers());
        table.setItems(model);

    }


    @Override
    public void update() {
        model = FXCollections.observableArrayList(controller.getAllUsers());
        table.setItems(model);

    }
}

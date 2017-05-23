package gui;

import client.ClientController;
import client.StartClient;
import com.ubb.cms.User;
import com.ubb.cms.utils.UserTags;
import exception.ServiceException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import utils.Observer;


/**
 * Created by Raul on 02/05/2017.
 */




public class UserView implements Observer<User> {
    private ClientController controller;

    private Stage currentStage;

    private int userId;

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


    public void setController(ClientController clientController, Stage currentStage,int userId)
    {
        this.controller = clientController;
        this.currentStage = currentStage;
        this.userId = userId;

        model = FXCollections.observableArrayList(controller.getAllUsers());
        table.setItems(model);

    }


    @Override
    public void update() {
        model = FXCollections.observableArrayList(controller.getAllUsers());
        table.setItems(model);

    }

    @FXML
    public void logOutBtnHandler(){
        try{

            User user = controller.getUserById(userId);
            controller.logout(user.getUsername());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UserView.class.getClassLoader().getResource("login.fxml"));
            BorderPane root = loader.load();
            currentStage.setTitle("Conference Management System");
            LoginView loginView = loader.getController();
            loginView.setController(controller, currentStage);
            Scene scene = new Scene(root);
            scene.getStylesheets().add(UserView.class.getResource("/login.css").toString());
            currentStage.setScene(scene);
            currentStage.show();
        }catch(Exception ex){
            ShowAlert.showAlert(ex.getMessage());
        }
    }
}

package client;

import gui.LoginView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.common.IConferenceServer;

/**
 * Created by Raul on 26/04/2017.
 */
public class StartClient extends Application{
    private static ClientController clientController;

    public static void main(String[] args) throws Exception {
        launch(args);
        clientController.disconnect();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
        IConferenceServer server = (IConferenceServer)factory.getBean("conferenceService");
        clientController = new ClientController(server);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(StartClient.class.getClassLoader().getResource("login.fxml"));
        BorderPane root = loader.load();
        primaryStage.setTitle("Hello World");
        LoginView loginView = loader.getController();
        loginView.setController(clientController);
        loginView.setCurrentStage(primaryStage);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(StartClient.class.getResource("/login.css").toString());
        primaryStage.setScene(scene);
        primaryStage.show();

        /*FXMLLoader loader = new FXMLLoader();
        loader.setLocation(StartClient.class.getResource("/UserView.fxml")); //URL
        BorderPane borderPane = (BorderPane) loader.load();
        UserView userView = loader.getController();
        userView.setController(clientController);


        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Update Users");
        primaryStage.show();*/



    }
}

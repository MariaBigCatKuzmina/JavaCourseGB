package ru.kuzmina.client;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.kuzmina.client.controllers.AuthController;

import java.io.IOException;


public class ClientChat extends Application {

    public static final String CONNECTION_ERROR_MESSAGE = "Невозможно установить  сетевое соединение";

    private Stage primaryStage;
    private Network network;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("chat-template.fxml"));

        Parent load = fxmlLoader.load();
        Scene scene = new Scene(load);

        this.primaryStage.setTitle("Сетевой чат GeekBrains");
        this.primaryStage.setScene(scene);

        ClientController controller = fxmlLoader.getController();
        controller.userList.getItems().addAll("User1", "User2");

        this.primaryStage.show();
        connectToServer(controller);

        FXMLLoader authLoader = new FXMLLoader();
        authLoader.setLocation(getClass().getResource("authDialog.fxml"));
        AnchorPane authDialogPanel = authLoader.load();

        Stage authStage = new Stage();
        authStage.initOwner(primaryStage);
        authStage.initModality(Modality.WINDOW_MODAL);
        authStage.setScene(new Scene(authDialogPanel));

        AuthController authController = authLoader.getController();
        authController.setClientChat(this);
        authController.setNetwork(network);

        authStage.show();

    }

    private void connectToServer(ClientController controller) {
        network = new Network();
        boolean hasConnected = network.connect();
        if (!hasConnected) {
            System.err.println(CONNECTION_ERROR_MESSAGE);
            showErrorDialog(CONNECTION_ERROR_MESSAGE);
            return;
        }
        controller.setNetwork(network);
        controller.setApplication(this);

        this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                network.close();
            }
        });
    }

    public void showErrorDialog(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
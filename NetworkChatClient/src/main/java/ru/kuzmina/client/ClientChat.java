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
import ru.kuzmina.client.controllers.ClientController;
import ru.kuzmina.client.model.Network;

import java.io.IOException;


public class ClientChat extends Application {
    public static ClientChat INSTANCE;

    public static final String CONNECTION_ERROR_MESSAGE = "Невозможно установить  сетевое соединение";

    private Stage primaryStage;
    private Stage authStage;
    private ClientController clientController;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        ClientController controller = createChatDialog();
        createAuthDialog();
        controller.initializeMessageHandler();
    }

    @Override
    public void init() throws Exception {
        INSTANCE = this;
    }

    private void createAuthDialog() throws IOException {
        FXMLLoader authLoader = new FXMLLoader();
        authLoader.setLocation(getClass().getResource("authDialog.fxml"));
        AnchorPane authDialogPanel = authLoader.load();

        authStage = new Stage();
        authStage.initOwner(primaryStage);
        authStage.initModality(Modality.WINDOW_MODAL);
        authStage.setScene(new Scene(authDialogPanel));

        AuthController authController = authLoader.getController();
        authController.setClientChat(this);
        authController.initializeMessageHandler();

        authStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                primaryStage.close();
            }
        });
        authStage.showAndWait();

    }

    private ClientController createChatDialog() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("chat-template.fxml"));

        Parent load = fxmlLoader.load();
        Scene scene = new Scene(load);

        this.primaryStage.setTitle("Сетевой чат GeekBrains");
        this.primaryStage.setScene(scene);

        this.clientController = fxmlLoader.getController();

        this.clientController.userList.getItems().addAll("user1", "user2", "user3");

        this.primaryStage.show();
        connectToServer(this.clientController);
        return this.clientController;
    }

    private void connectToServer(ClientController controller) {
        boolean hasConnected = Network.getInstance().connect();
        if (!hasConnected) {
            System.err.println(CONNECTION_ERROR_MESSAGE);
            showErrorDialog(CONNECTION_ERROR_MESSAGE);
            return;
        }
        controller.setApplication(this);

        this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Network.getInstance().close();
            }
        });
    }

    public void showErrorDialog(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    public Stage getAuthStage() {
        return authStage;
    }

    public static void main(String[] args) {
        Application.launch();
    }

    public Stage getChatStage() {
        return this.primaryStage;
    }

    public ClientController getClientController() {
        return clientController;
    }
}
package Java2.Lesson6.Client;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


import java.io.IOException;


public class ClientChat extends Application {

    public static final String CONNECTION_ERROR_MESSAGE = "Невозможно установить  сетевое соединение";

    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("chat-template.fxml"));

        Parent load = fxmlLoader.load();
        Scene scene = new Scene(load);

        this.stage.setTitle("Сетевой чат GeekBrains");
        this.stage.setScene(scene);

        ClientController controller = fxmlLoader.getController();
        controller.userTree.getItems().addAll("User1", "User2");

        stage.show();
        connectToServer(controller);
    }

    private void connectToServer(ClientController controller) {
        Network network = new Network();
        boolean hasConnected = network.connect();
        if (!hasConnected) {
            System.err.println(CONNECTION_ERROR_MESSAGE);
            showErrorDialog(CONNECTION_ERROR_MESSAGE);
            return;
        }
        controller.setNetwork(network);
        controller.setApplication(this);

        this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
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
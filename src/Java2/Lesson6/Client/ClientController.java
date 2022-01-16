package Java2.Lesson6.Client;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Consumer;

public class ClientController {

    @FXML
    public ListView<String> userTree;
    @FXML
    public TextArea chatArea;
    @FXML
    private TextField messageField;
    @FXML
    private Button sendButton;

    private Network network;
    private ClientChat application;

    public void onSendButtonClick() {
        String message = messageField.getText();
        appendMessageToChat(message);
        try {
            network.sendMessage(message);
        } catch (IOException e) {
            application.showErrorDialog("Ошибка передачи данных по сети");
            e.printStackTrace();
        }
    }

    private void appendMessageToChat(String message) {
        if (!message.isEmpty()) {
            chatArea.appendText(message);
            chatArea.appendText(System.lineSeparator());
            messageField.clear();
            chatArea.requestFocus();
        }
    }

    public void onClose() {
        ((Stage) sendButton.getScene().getWindow()).close();
    }

    public void setNetwork(Network network) {
        this.network = network;
        this.network.waitMessages(new Consumer<String>() {
            @Override
            public void accept(String message) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        appendMessageToChat(message);
                    }
                });
            }
        });
    }

    public void setApplication(ClientChat application) {
        this.application = application;
    }
}
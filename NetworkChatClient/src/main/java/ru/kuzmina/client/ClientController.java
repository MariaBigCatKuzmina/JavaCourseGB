package ru.kuzmina.client;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.function.Consumer;

public class ClientController {

    @FXML public ListView<String> userList;
    @FXML public TextArea chatArea;
    @FXML private TextField messageField;
    @FXML private Button sendButton;

    private Network network;
    private ClientChat application;

    public void sendMessage() {

        String message = messageField.getText().trim();
        if (message.isEmpty()) {
            messageField.clear();
            return;
        }

        String sender = null;
        if (!userList.getSelectionModel().isEmpty()) {
            sender = userList.getSelectionModel().getSelectedItem();
        }
        try {
            message = sender != null ? String.join(": ", sender, message) : message;
            network.sendMessage(message);
        } catch (IOException e) {
            application.showErrorDialog("Ошибка передачи данных по сети");
            e.printStackTrace();
        }
        appendMessageToChat("Я", message);
    }

    private void appendMessageToChat(String sender, String message) {
        chatArea.appendText(DateFormat.getDateTimeInstance().format(new Date()));
        chatArea.appendText(System.lineSeparator());

        if (sender != null) {
            chatArea.appendText(sender + ":");
            chatArea.appendText(System.lineSeparator());
        }
        chatArea.appendText(message);
        chatArea.appendText(System.lineSeparator());
        chatArea.appendText(System.lineSeparator());
        messageField.setFocusTraversable(true);
        messageField.clear();
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
                        appendMessageToChat("Server", message);
                    }
                });
            }
        });
    }

    public void setApplication(ClientChat application) {
        this.application = application;
    }
}
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

    private ClientChat application;

    public void sendMessage() {

        String message = messageField.getText().trim();
        if (message.isEmpty()) {
            messageField.clear();
            return;
        }

        String recipient = null;
        if (!userList.getSelectionModel().isEmpty()) {
            recipient = userList.getSelectionModel().getSelectedItem();
        }
        try {
            String outMessage = recipient != null ? String.format("/private %s %s", recipient, message) : message;
            Network.getInstance().sendMessage(outMessage);
        } catch (IOException e) {
            application.showErrorDialog("Ошибка передачи данных по сети");
            e.printStackTrace();
        }
        appendMessageToChat("Я",recipient, message);
    }

    private void appendMessageToChat(String sender, String recipient, String message) {
        chatArea.appendText(DateFormat.getDateTimeInstance().format(new Date()));
        chatArea.appendText(System.lineSeparator());

        String messageHeader;
        messageHeader = sender != null ? sender : "no sender";
        messageHeader = recipient != null && sender != null ? messageHeader + "->"  + recipient : messageHeader;

        chatArea.appendText(messageHeader);
        chatArea.appendText(System.lineSeparator());
        chatArea.appendText(message);
        chatArea.appendText(System.lineSeparator());
        chatArea.appendText(System.lineSeparator());
        messageField.setFocusTraversable(true);
        messageField.clear();
    }

    public void onClose() {
        ((Stage) sendButton.getScene().getWindow()).close();
    }

    public void setApplication(ClientChat application) {
        this.application = application;
    }

    public void initializeMessageHandler() {
        Network.getInstance().waitMessages(new Consumer<String>() {
            @Override
            public void accept(String message) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("ClientController.initializeMessageHandler.message"+message);
                        String sender, finalMessage;
                        if (message.startsWith("/private")) {
                            String[] parts = message.split(" ");
                            sender = parts[1];
                            finalMessage = parts[2];
                        } else {
                            sender = "Server";
                            finalMessage = message;
                        }
                        appendMessageToChat(sender, null, finalMessage);
                    }
                });
            }
        });
    }
}
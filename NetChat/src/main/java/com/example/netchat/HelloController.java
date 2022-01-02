package com.example.netchat;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class HelloController {

    @FXML public ListView<String> userTree;
    @FXML public TextArea chatArea;
    @FXML private TextField messageField;
    @FXML private Button sendButton;

    public void onSendButtonClick() {
        if (!messageField.getText().isEmpty()) {
            chatArea.appendText(messageField.getText());
            chatArea.appendText(System.lineSeparator());
            messageField.clear();
            chatArea.requestFocus();
        }
    }

    public void onClose() {
        ((Stage) sendButton.getScene().getWindow()).close();
    }
}
package ru.kuzmina.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ru.kuzmina.client.ClientChat;
import ru.kuzmina.client.Network;

import java.io.IOException;

public class AuthController {

    public static final String AUTH_COMMAND = "/auth";
    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;
    @FXML private Button authButton;

    private ClientChat clientChat;
    private Network network;

    @FXML
    public void executeAuth(ActionEvent actionEvent) {
        String login = loginField.getText();
        String password = passwordField.getText();

        if (login == null || login.isBlank() || password == null || password.isBlank()) {
            clientChat.showErrorDialog("Логин и пароль не должны быть пустыми");
            return;
        }

        String authCommandMessage = String.format("%s %s %s", AUTH_COMMAND, login, password);
        try {
            network.sendMessage(authCommandMessage);
        } catch (IOException e) {
            clientChat.showErrorDialog("Ошибка передачи данных по сети");
            e.printStackTrace();
        }
    }

    public void setClientChat(ClientChat clientChat) {
        this.clientChat = clientChat;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }
}

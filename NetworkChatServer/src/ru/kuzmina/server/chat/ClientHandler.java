package ru.kuzmina.server.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

//Класс отвечающий за клиентское соединение
public class ClientHandler {
    public static final String AUTH_OK = "/authOk";
    public static final String AUTH = "/auth";
    public static final String AUTH_DUPLICATE = "/authDuplicate";

    private final MyServer server;
    private final Socket clientSocket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private String userName;


    public ClientHandler(MyServer myServer, Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.server = myServer;
    }

    public void handle() throws IOException {

        inputStream = new DataInputStream(clientSocket.getInputStream());
        outputStream = new DataOutputStream(clientSocket.getOutputStream());

        new Thread(() -> {
            try {
                authenticate();
                readMessages();
            } catch (IOException e) {
                System.err.println("Failed to read incoming message from client");
                e.printStackTrace();
            } finally {
                try {
                    closeConnection();
                } catch (IOException e) {
                    System.err.println("Failed to close connection");
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void authenticate() throws IOException {
        while (true) {
            String message = inputStream.readUTF();
            if (message.startsWith(AUTH)) {
                String[] authParts = message.split(" ");
                String login = authParts[1];
                String password = authParts[2];

                this.userName = server.getAuthService().getUserNameByLoginAndPassword(login, password);

                if (userName == null) {
                    sendMessage("Некорректный логин и пароль");
                } else if (!server.isClientConnected(this)) {
                    sendMessage(String.format("%s %s", AUTH_OK, userName));
                    server.subscribe(this);
                    return;
                } else {
                    sendMessage(String.format("%s %s", AUTH_DUPLICATE, "Пользователь с таким логином и паролем уже подключен"));
                }
            }
        }
    }

    private void readMessages() throws IOException {
        while (true) {
            String incomingMessage = inputStream.readUTF().trim();
            if (incomingMessage.startsWith("/end")) {
                return;
            } else {
                processMessage(incomingMessage);
            }
        }
    }

    private void processMessage(String incomingMessage) throws IOException {
        if (incomingMessage.startsWith("/private")) {
            String[] parts = incomingMessage.split(" ");
            if (!parts[2].isEmpty()) {
                this.server.sendPrivateMessage(parts[2], this, parts[1]);
            }
        } else {
            this.server.broadcastMessage(incomingMessage, this);
        }
    }


    public void sendMessage(String outcomingMessage) throws IOException {
        this.outputStream.writeUTF(outcomingMessage);
    }

    private void closeConnection() throws IOException {
        server.unsubscribe(this);
        clientSocket.close();
    }

    public String getUserName() {
        return userName;
    }

}

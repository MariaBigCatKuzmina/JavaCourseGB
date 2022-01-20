package ru.kuzmina.server.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

//Класс отвечающий за клиентское соединение
public class ClientHandler {
    public static final String AUTH_OK = "/authOk";
    public static final String AUTH = "/auth";
    private MyServer server;
    private final Socket clientSocket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

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
        String message = inputStream.readUTF();
        if (message.startsWith(AUTH)) {
            String[] authParts = message.split(" ");
            String login = authParts[1];
            String password = authParts[2];

            String userName = server.getAuthService().getUserNameByLoginAndPassword(login, password);

            if (userName == null){
                sendMessage("Некорректный логин и пароль");
            } else {
                sendMessage(String.format("%s %s",AUTH_OK, userName));
                server.subscribe(this);
            }
        }
    }

    private void readMessages() throws IOException {
        while (true){
            String incomingMessage = inputStream.readUTF().trim();
            System.out.println("incomingMessage = " + incomingMessage);
            if (incomingMessage.startsWith("/end")) {
                return;
            } else {
                processMessage(incomingMessage);
            }
        }
    }

    private void processMessage(String incomingMessage) throws IOException {
        this.server.broadcastMessage(incomingMessage, this);
    }


    public void sendMessage(String outcomingMessage) throws IOException {
        this.outputStream.writeUTF(outcomingMessage);
    }

    private void closeConnection() throws IOException {
        server.unsubscribe(this);
        clientSocket.close();
    }
}

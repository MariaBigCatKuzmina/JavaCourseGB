package ru.kuzmina.server.chat;

import ru.kuzmina.server.chat.auth.AuthService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {

    private final List<ClientHandler> clients = new ArrayList<>();
    private AuthService authService;

    public AuthService getAuthService() {
        return authService;
    }

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server has been started");
            authService = new AuthService();
            while (true) {
                waitAndProcessClientConnection(serverSocket);
            }
        } catch (IOException e) {
            System.err.println("Failed to bind port " + port);
            e.printStackTrace();
        }
    }

    private void waitAndProcessClientConnection(ServerSocket serverSocket) throws IOException {
        System.out.println("Waiting for new connections");
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client has been connected");
        ClientHandler clientHandler = new ClientHandler(this, clientSocket);
        clientHandler.handle();
    }

    public void broadcastMessage(String message, ClientHandler sender) throws IOException {
        for (ClientHandler client : clients) {
            if (client != sender){
                client.sendMessage(message);
            }
        }
    }

    public void sendPrivateMessage(String message, ClientHandler sender, String recipientName) throws IOException {
        for (ClientHandler client : clients) {
            if (client.getUserName().equals(recipientName)){
                System.out.println("recipientName="+recipientName+",message="+message);
                client.sendMessage(String.format("/private %s %s ", sender.getUserName(), message));
                return;
            }
        }
    }

    public  boolean isClientConnected (ClientHandler client)
    {
        for (ClientHandler clientItm : this.clients) {
            if (clientItm.equals(client)) {
                return true;
            }
        }
        return false;

    }

    public void subscribe(ClientHandler client) {
        this.clients.add(client);
        System.out.println("Client has connected");
    }


    public void unsubscribe(ClientHandler client) {
        this.clients.remove(client);
    }


}

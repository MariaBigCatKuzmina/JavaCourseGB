package ru.kuzmina.client.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.function.Consumer;

public class Network {
    public static final String SERVER_HOST = "localhost";
    public static final int SERVER_PORT = 8189;

    private int port;
    private String host;
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    private static Network INSTANCE;
    private boolean isConnected;

    public static Network getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Network();
        }
        return INSTANCE;
    }

    private Network(int port, String host) {
        this.port = port;
        this.host = host;
    }

    private Network() {
        this(SERVER_PORT, SERVER_HOST);
    }

    public boolean connect() {
        try {
            this.socket = new Socket(this.host, this.port);
            this.inputStream = new DataInputStream(socket.getInputStream());
            this.outputStream = new DataOutputStream(socket.getOutputStream());
            this.isConnected = true;
            return true;
        } catch (IOException e) {
            System.err.println("Ошибка подключения к серверу");
            e.printStackTrace();
            return false;
        }
    }

    public void sendMessage(String message) throws IOException {
        try {
            outputStream.writeUTF(message);
        } catch (IOException e) {
            System.err.println("Не удалось отправить сообщение");
            throw e;
        }
    }

    public void waitMessages(Consumer<String> messageHandler) {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    if (Thread.currentThread().isInterrupted()) {
                        return;
                    }
                    String message = inputStream.readUTF();
                    messageHandler.accept(message);
                } catch (IOException e) {
                    System.err.println("Не удалось получить сообщение");
                    e.printStackTrace();
                    break;
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        return isConnected;
    }
}

package Java2.Lesson6.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    static final int PORT = 8189;

    private static Socket socket = null;

    private static void setConnection() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер начал работу.");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            inputOutput();
        } catch (IOException e) {
            System.out.println("Ошибка подключения к порту " + PORT);
            e.printStackTrace();
        }
    }

    private static void inputOutput() {
        try {
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            while (true) {
                String inMessage = input.readUTF();
                if (inMessage.contains("/end")) {
                    System.out.println("Клиент закончил работу");
                    break;
                }
                System.out.printf("Сообщение от клиента: %s%n", inMessage);
                output.writeUTF("Echo: " + inMessage);
            }
        } catch (IOException e) {
            System.out.println("Соединение было закрыто");
        }
    }

    public static void main(String[] args) {
        setConnection();
    }
}

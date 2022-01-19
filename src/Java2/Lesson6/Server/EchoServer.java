package Java2.Lesson6.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
    static final int PORT = 8189;

    private static Socket socket = null;
    private static DataInputStream input;
    private static DataOutputStream output;

    private static void setConnection() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер начал работу.");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");

            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            System.out.println("Ошибка подключения к порту " + PORT);
            e.printStackTrace();
        }
    }

    private static void waitMessagesFromClient() {
        new Thread(() -> {
            try {
                while (true) {
                    String inMessage = input.readUTF();
                    if (inMessage.contains("/end")) {
                        System.out.println("Клиент закончил работу");
                        break;
                    }
                    System.out.printf("Сообщение от клиента: %s%n", inMessage);

                    sendMessageToClient("Echo:" + inMessage);
                }
            } catch (IOException e) {
                System.err.println("Соединение было закрыто");
                e.printStackTrace();
            } finally {
                close();
            }
        }).start();
    }

    private static void sendMessageToClient(String message) throws IOException {
        output.writeUTF(message);
    }

    private static void waitMessagesFromConsole() {
        Scanner scanner = new Scanner(System.in);
        new Thread(() -> {
            while (true) {
                System.out.print("Введите сообщение клиенту: ");
                String consoleMessage = scanner.nextLine();
                if (!consoleMessage.isEmpty()) {
                    try {
                        sendMessageToClient("Console: " + consoleMessage);
                    } catch (IOException e) {
                        System.err.println("Ошибка отправки сообщения клиенту из консоли");
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private static void close() {
        try {
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Ошибка закрытия соединения");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        setConnection();
        waitMessagesFromClient();
        waitMessagesFromConsole();
    }
}

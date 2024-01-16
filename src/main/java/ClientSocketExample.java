import systemMessage.ClientMessage;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocketExample {
    //це створене саме тому, що в мене були проблеми з терміналом,
    //він не хоче працювати, тому є що є
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Адреса сервера
        int serverPort = 8081; // Порт сервера

//використовуєм try-with-resources для роботи з ресурсами які потрібно закривати
        try (Socket socket = new Socket(serverAddress, serverPort); //з'єднуєм порт і адресу сервера в socket
             InputStream in = socket.getInputStream();// читаємо данні з сервера
             OutputStream out = socket.getOutputStream();//відправляємо данні з сервера
             BufferedReader reader = new BufferedReader(new InputStreamReader(in));//створюєм обгортку для читання тексту з потоку
             PrintWriter writer = new PrintWriter(out, true);//створюєм обгортку для запису даних
             Scanner scanner = new Scanner(System.in)) // Сканер є сканер
        {

            String serverMessage = reader.readLine();
            System.out.println(ClientMessage.MESSAGE_FROM_SERVER + serverMessage);

            System.out.print(ClientMessage.MESSAGE_CLIENT);
            String clientMessage = scanner.nextLine();
            writer.println(clientMessage);

            if (containsRussianCharacters(clientMessage)) {

                serverMessage = reader.readLine();
                System.out.println(ClientMessage.MESSAGE_FROM_SERVER + serverMessage);

                for (int i = 0; i < 3; i++) {
                    serverMessage = reader.readLine();
                    System.out.println(serverMessage);
                }

                String answer = scanner.nextLine();
                writer.println(answer);

                serverMessage = reader.readLine();
                System.out.println(ClientMessage.MESSAGE_FROM_SERVER + serverMessage);

                if (serverMessage.startsWith("Correct")) {

                    System.out.println(ClientMessage.MESSAGE_FROM_SERVER + reader.readLine());
                } else {

                    System.out.println(ClientMessage.MESSAGE_FROM_SERVER + reader.readLine());
                }
            }

            System.out.println(ClientMessage.CONNECTED_CLOSE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean containsRussianCharacters(String text) {
        return text.matches(".*[ыъЁёє].*");
    }
}

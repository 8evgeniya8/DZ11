import systemMessage.ServerMessage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServerSocketExample {
    public static void main(String[] args) {
        //сервер який тут написаний працює лише з одним клієнтом і він постійно відкритий
        // він не керує пулом потоків.
        //було б непогано зробити його безпечним,
        // тобто використовувати SSL/TLS (створити приватний ключ для сервера,
        // відкритий для клієнта, сертифікат і файл або базу данних де це все зберігати)


        int port = 8081;
        //створюєм сервер ServerSocket для прослуховування вхідних підключень клієнта.
        // Вказуєм у ньому порт,який слухаєм
        try (ServerSocket serverSocket = new ServerSocket(port);
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println(port);
//використовуєм try-with-resources блок
            while (true) {
                //чекаємо на клієнта
                try (Socket clientSocket = serverSocket.accept();
//дозволяємо серверу отримувати дані від клієнта
                     InputStream in = clientSocket.getInputStream();
                     //дозволяємо серверу відправляти дані клієнту
                     OutputStream out = clientSocket.getOutputStream();
                     //обгортка для зчитування данних з клієнта
                     BufferedReader clientReader = new BufferedReader(new InputStreamReader(in));
                     //обгортка для відправки назад данних клієнту
                     PrintWriter writer = new PrintWriter(out, true)) {

// Повідомляємо собі що клієнта підключено. Забираємо собі IP-адресу клієнта.
                    System.out.println(ServerMessage.CLIENT_UP + clientSocket.getInetAddress().getHostAddress());

                    // привітання
                    writer.println(ServerMessage.HELLO);

                    // читаємо повідомлення від клієнта
                    String clientMessage = clientReader.readLine();
                    // якщо попався на вшивість даємо шанс
                    if (clientMessage != null && clientMessage.matches(".*[ыъЁёє].*")) {
                        writer.println(ServerMessage.PALYANITSA_QUESTION);
                        writer.println(ServerMessage.CHOICE_1);
                        writer.println(ServerMessage.CHOICE_2);
                        writer.println(ServerMessage.CHOICE_3);

                        boolean attempts = true;
// Зчитуєм вхідний потік та перевіряємо
                        while (attempts) {
                            String answer = reader.readLine();
                            if (answer != null) {
                                if (answer.equals("1")) {
                                    writer.println(ServerMessage.CORRECT_ANSWER + getDateTime());
                                    break;
                                } else if (answer.equals("2") || answer.equals("3")) {
                                    writer.println(ServerMessage.INCORRECT_ANSWER);
                                    break;
                                } else {
                                    writer.println(ServerMessage.TRY_AGAIN);
                                    break;
                                }
                            }
                        }
                    } else {
                        //якщо перевірки на вшивість немає, слухаємо повідомлення та вимикаємо
                        System.out.println(ServerMessage.MESSAGE_CLIENT + clientMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // контейнер для перевірки ру
    private static boolean characterСheckerRu(String text) {
        return text.matches(".*[ыъЁёє].*");
    }

    // метод з отримання дати
    private static String getDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
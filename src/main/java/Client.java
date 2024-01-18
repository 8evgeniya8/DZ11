import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static message.ClientMessage.CONNECTED_SERVER;
import static message.ErrorMessage.CONNECTION_ERROR;

public class Client {
    public static void main(String[] args) {
        String hostName = "localhost";
        int portNumber = 8081;

        try (Socket socket = new Socket(hostName, portNumber);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println(CONNECTED_SERVER);

            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("Сервер каже: " + in.readLine());

                if ("exit".equalsIgnoreCase(userInput)) {
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println(CONNECTION_ERROR + hostName + " на порту " + portNumber);
            System.out.println(e.getMessage());
        }
    }
}


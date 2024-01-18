import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

import command.CommandFactory;

import static message.ErrorMessage.ERROR_ATTEMPTING_CONNECT_PORTCONNECTION_ERROR;
import static message.ErrorMessage.FAILED_LISTEN_PORT;
import static message.ServerMessage.CLIENT_CONNECTED;
import static message.ServerMessage.WELCOME;

public class Server {
    public static void main(String[] args) {
        int port = 8081;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println(WELCOME + " " + port);

            try (Socket socket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                System.out.println(CLIENT_CONNECTED);

                String clientMessage = in.readLine();
                System.out.println("Клієнт каже: " + clientMessage);

                var command = CommandFactory.createCommand(clientMessage, out, in);
                command.execute();

            } catch (IOException e) {
                System.out.println(ERROR_ATTEMPTING_CONNECT_PORTCONNECTION_ERROR + " " + port);
                System.out.println(e.getMessage());
            }

        } catch (IOException e) {
            System.out.println(FAILED_LISTEN_PORT + " " + port);
            System.out.println(e.getMessage());
        }
    }
}
import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) {

        String host = "localhost";
        int port = 2090;
        try (Socket clientSocket = new Socket(host, port);
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            System.out.println("Соединение с сервером установлено");

            while (!clientSocket.isClosed()) {

                String serverMsg = in.readLine();
                System.out.println(serverMsg);

                String clientMsg = reader.readLine();
                out.write(clientMsg + "\n");
                out.flush();

                String serverMsg2 = in.readLine();
                System.out.println(serverMsg2);

            }
        } catch (IOException e) {
            System.out.println("Соединение с сервером прервано");
        }
    }
}
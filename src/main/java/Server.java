import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(2090);
             Socket clientSocket = serverSocket.accept();
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
        ) {
            System.out.println("Сервер запущен, соединение с клиентом установлено");

            while (!clientSocket.isClosed()) {

                ArrayList<String> arr = new ArrayList<>();

                String input = name(in, out);
                arr.add(input);

                input = surname(in, out);
                arr.add(input);

                input = adult(in, out);
                arr.add(input);

                printInput(in, out, arr);
            }
        } catch (IOException e) {
            System.out.println("Соединение с клиентом прервано");
        }
    }

    public static String name(BufferedReader in, BufferedWriter out) throws IOException {
        out.write("Write your name\n");
        out.flush();
        String respond = in.readLine();
        out.write("Name " + respond + " added\n");
        out.flush();
        return respond;
    }

    public static String surname(BufferedReader in, BufferedWriter out) throws IOException {
        out.write("Write your surname\n");
        out.flush();
        String respond = in.readLine();
        out.write("Surname " + respond + " added\n");
        out.flush();
        return respond;
    }

    public static String adult(BufferedReader in, BufferedWriter out) throws IOException {
        out.write("Are you adult? (yes/no)\n");
        out.flush();
        String respond = in.readLine();

        if (respond.equalsIgnoreCase("no")) {
            out.write("Status kid added\n");
            out.flush();
            respond = "kid";
        } else if (respond.equalsIgnoreCase("yes")) {
            out.write("Status adult added\n");
            out.flush();
            respond = "adult";
        } else {
            out.write("Unexpected input. Status N/A added\n");
            out.flush();
            respond = "N/A";
        }

        return respond;
    }

    public static void printInput(BufferedReader in, BufferedWriter out, ArrayList<String> arr) throws IOException {

        out.write("Success. You added: ");

        for (String item : arr) {
            out.write(item + " ");
        }

        out.flush();
    }
}
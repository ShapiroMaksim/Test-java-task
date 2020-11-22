import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket firstSocket = new ServerSocket(7999);
        ServerSocket secondSocket = new ServerSocket(8000);

        System.out.println("Server started!");

        Socket socket1 = firstSocket.accept();
        Socket socket2 = secondSocket.accept();
        System.out.println("Both clients connected!");

        BufferedWriter writer1 =
                new BufferedWriter(
                        new OutputStreamWriter(
                                socket1.getOutputStream()));
        BufferedReader reader1 =
                new BufferedReader(
                        new InputStreamReader(
                                socket1.getInputStream()));

        BufferedWriter writer2 =
                new BufferedWriter(
                        new OutputStreamWriter(
                                socket2.getOutputStream()));
        BufferedReader reader2 =
                new BufferedReader(
                        new InputStreamReader(
                                socket2.getInputStream()));
        String message;
        while (true) {
            message = reader1.readLine();
            if (message.equals("end"))
                break;
            writer2.write(message);
            writer2.newLine();
            writer2.flush();

            message = reader2.readLine();
            if (message.equals("end"))
                break;
            writer1.write(message);
            writer1.newLine();
            writer1.flush();
        }

        writer1.write("end");
        writer1.newLine();
        writer1.flush();
        writer2.write("end");
        writer2.newLine();
        writer2.flush();
        firstSocket.close();
        secondSocket.close();
    }
}

import java.io.*;
import java.net.Socket;

public class SecondClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",8000);

        BufferedWriter writer =
                new BufferedWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream()));
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));

        BufferedReader messageReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Waiting for message");
            String message = reader.readLine();

            if (message.equals("end"))
                break;

            System.out.println("Message:");
            System.out.println(message);

            System.out.println("Write message");
            message = messageReader.readLine();

            writer.write(message);
            writer.newLine();
            writer.flush();
        }
        System.out.println("end!");
    }
}

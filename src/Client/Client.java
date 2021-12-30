package Client;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static final int PORT = 6000;
    public static final String HOST = "LocalHost";

    public static void main(String[] args) throws IOException {
        try(Socket socket = new Socket()){
            socket.connect(new InetSocketAddress(HOST, PORT));
            Scanner sc = new Scanner(socket.getInputStream());
            while(sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        }

    }
}

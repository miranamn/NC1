package Client;

import controller.Controller;
import model.FileLoader;
import model.Loader;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.JsonHelper;
import view.ConsoleView;
import view.View;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import static model.Values.TRACK;

public class Client {
    public static final int PORT = 6000;
    public static final String HOST = "LocalHost";

    private static Socket socket;
    private static BufferedReader reader;
    private static ObjectInputStream in = null;
    private static ObjectOutputStream out = null;

    public static void main(String[] args) throws IOException {
        try {
            try {
                socket = new Socket(HOST, PORT);
                reader = new BufferedReader(new InputStreamReader(System.in));
                in = new ObjectInputStream(socket.getInputStream());
                out = new ObjectOutputStream(socket.getOutputStream());
                Controller controller = new Controller();
                View view = new ConsoleView(controller);
                controller.setOut(out);
                view.init(reader);
                out.flush();
            } finally {
                System.out.println("Client was closed...");
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException | ParseException | ClassNotFoundException | InterruptedException e) {
            System.err.println(e);
        }
    }
}



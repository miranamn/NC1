package view;

import controller.Controller;
import org.json.simple.parser.ParseException;
import view.util.JsonHelper;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class ConsoleView implements View, Serializable {
    private Controller controller;

    public ConsoleView(){}

    public ConsoleView(Controller controller) {
        this.controller = controller;
    }

    public void init() throws IOException, ParseException, ClassNotFoundException {
        System.out.println("Program is running. Do you want to continue working or start a new list? ");
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();
        switch (choice){
            case "continue":
                controller.ListInstall();
                System.out.println("Data uploaded. Select the command");
                break;
            default:
                System.out.println("start new list");
        }
        String buff = sc.nextLine(); //ожидание ввода команды
        String[] str = buff.split(" ");
        switch (str[0]){
            case "add":
                controller.addEntity(JsonHelper.addTrackArrayJson(str));
                break;
            case "search":
                System.out.println("Name of performer/ name of song OR name of genre");
                //controller.searchEntity(JsonHelper.addTrackArrayJson());
                break;
            default:
                System.out.println("Where is an action?");
        }
        System.out.println("Mission complete. Do you want to repeat?");
        String answer = sc.nextLine();
        switch (answer){
            case "yes":
                init();
            case "no" :
                break;
            default:
                System.out.println("Invalid answer. Output");
        }
    }
}


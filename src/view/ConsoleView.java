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
        System.out.println("Program is running");
        Scanner sc = new Scanner(System.in);
        String buff = sc.nextLine(); //ожидание ввода команды
        String[] str = buff.split(" ");
        switch (str[0]){
            case "add":
                controller.addEntity(JsonHelper.AddEntityMakeJson(str));
                break;
            case "search":
                //вызов метода поиска
                break;
            default:
                System.out.println("Where is an action?");
        }
        System.out.println("Mission complete. Do you want to repeat?");
        String answer = sc.nextLine();
        switch (answer){
            case "yes":
                controller.ListInstall(); //десериализация листа из файла
                init();
            case "no" :
                break;
            default:
                System.out.println("Invalid answer. Output");
        }
    }
}


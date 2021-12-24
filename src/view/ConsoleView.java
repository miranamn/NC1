package view;

import controller.Controller;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.JsonHelper;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class ConsoleView implements View, Serializable {
    private Controller controller;

    public ConsoleView() {
    }

    public ConsoleView(Controller controller) {
        this.controller = controller;
    }

    public void init() throws IOException, ParseException, ClassNotFoundException {
        System.out.println("Program is running.\n" + "Do you want to continue working or Make " +
                "an operation with list? |Add, Delete, Set, Search, Watch, Help|");
        Scanner sc = new Scanner(System.in);
        String buff = sc.nextLine(); //ожидание ввода команды
        String[] str = buff.split(" ");
        switch (str[0]) {
            case "Add":
                controller.addEntity(JsonHelper.addEntityArrayJson(str));
                break;
            case "Watch":
                controller.getEntity(JsonHelper.watchEntity(str));
                break;
            case "Search":
                controller.searchEntity(JsonHelper.searchEntityJson(str));
                break;
            case "Delete":
                controller.deleteEntity(JsonHelper.deleteEntityJson(str));
                break;
            case "Set":
                controller.setEntity(JsonHelper.setEntityJson(str));
                break;
            case "Help":
                System.out.println("List of operations:\n" + "Add - добавление сущности или сущностей в список : Entity|name of track or name of genre|name of performer|name of album|name of genre\n"
                        + "Delete - удаление сущности из списка : Entity|name of track or genre|name of performer\n" + "Set - изменение сущности по входным данным : Entity|name of track (search) or name of genre(search)|name of performer(search)|name of track|name of performer|name of album|name of genre\n"
                        + "Search - поиск сущности по ключам : Entity|name of track or genre|name of performer" + "Watch - просмотр списка сущностей : Entity\n" + "Help - справка по командам\n");
                break;
            default:
                System.out.println("Where is an action?");
        }
        System.out.println("Operation is complete. Do you want to repeat? yes|no");
        String answer = sc.nextLine();
        switch (answer) {
            case "yes":
                init();
            case "no":
                break;
            default:
                System.out.println("Invalid answer. Output");
        }
    }

    public void stringList(JSONObject object) throws IOException, ParseException {
        System.out.println("Results:\n " + JsonHelper.viewModel(object).toString());
    }
}
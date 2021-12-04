package controller;

import model.Loader;
import model.Streams;
import model.music.Entity;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import view.util.JsonHelper;
import view.*;
import java.io.*;

public class Controller implements Serializable {
    private Loader list;
    private View view;

    public Controller(){}

    public Controller(Loader tempList, View view) {
        this.list = tempList;
        this.view = view;
    }

    public void setList(Loader list) {
        this.list = list;
    }

    public Loader getList() {
        return list;
    }

    public void addEntity(JSONObject js) throws IOException, ParseException {
        Entity temp = JsonHelper.getEntityFromJson(js);
        list.addEntity(temp);
        FileOutputStream os = new FileOutputStream("tracks.txt");
        Streams.serializeList(list, os); //сериализация
        System.out.println(list.toString()); //если вон та строчка выполнена норм, то списочек на экран выведется

    }

    public void ListInstall() throws IOException, ClassNotFoundException {
        FileInputStream in = new FileInputStream("tracks.txt");
        Streams.deserializeList(in); //рота подъем!!!!
    }

}


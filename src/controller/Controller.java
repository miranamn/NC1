package controller;

import model.Loader;
import model.Streams;
import model.music.Entity;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import view.util.JsonHelper;
import view.*;
import java.io.*;
import java.util.ArrayList;

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
        ArrayList<Entity> temp = JsonHelper.getArrayFromJson(js);
        list.setEntityList(temp);
        FileOutputStream os = new FileOutputStream("tracks.txt");
        Streams.serializeList(list, os);
        System.out.println(list.toString());

    }
    public void searchEntity(JSONObject js, String[] str) throws IOException, ParseException {
        ArrayList<Entity> arr = JsonHelper.getArrayFromJson(js);
        list.setEntityList(arr);
        list.searchEntity(str);
        FileOutputStream os = new FileOutputStream("tracks.txt");
        Streams.serializeList(list, os);
        System.out.println(list.toString());
    }

    public void ListInstall() throws IOException, ClassNotFoundException {
        FileInputStream in = new FileInputStream("tracks.txt");
        list = Streams.deserializeList(in);
    }

}


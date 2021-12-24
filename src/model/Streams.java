package model;

import model.music.Entity;

import java.io.*;
import java.util.ArrayList;

public abstract class Streams {

    public static void serializeList(ArrayList list, String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream of = new ObjectOutputStream(fos);
        of.writeObject(list);
    }

    public static ArrayList deserializeList(String fileName) throws IOException, ClassNotFoundException {
        File file = new File(fileName);
        if (file.length() == 0) return new ArrayList<>();
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream is = new ObjectInputStream(fis);
        ArrayList list = (ArrayList) is.readObject();
        return list;
    }
}

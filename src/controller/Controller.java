package controller;

import model.Loader;
import model.music.MusicGenres;
import model.music.MusicTrack;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.JsonHelper;
import view.*;

import java.io.*;
import java.util.ArrayList;

import static model.Values.*;

public class Controller implements Serializable {
    private Loader loader;
    private View view;

    public Controller() {
    }

    public void setLoader(Loader loader1) {
        this.loader = loader1;
    }

    public Loader getList() {
        return loader;
    }

    public void addEntity(JSONObject js) throws IOException, ParseException, ClassNotFoundException {
        ArrayList temp = JsonHelper.getArrayFromJson(js);
        if (js.containsValue(TRACK)) loader.addTracks(temp);
        else loader.addGenres(temp);
    }

    public void getEntity(JSONObject js) throws IOException, ClassNotFoundException, ParseException {
        if (js.containsValue(TRACK)) loader.getTrackList();
        else loader.getGenresList();

    }

    public void deleteEntity(JSONObject js) throws IOException, ParseException, ClassNotFoundException {
        ArrayList<String> str = JsonHelper.parseJsonToDeleteArr(js);
        String name = str.get(0);
        if (js.containsValue(TRACK)) {
            String author = str.get(1);
            loader.delTrack(author, name);
        } else loader.delGenre(name);
    }

    public void searchEntity(JSONObject js) throws IOException, ParseException, ClassNotFoundException {
        ArrayList<String> keys = JsonHelper.parseJsonToSearchArr(js);
        String name, author, album, genre = " ";
        if (js.containsValue(TRACK)) {
            if (!keys.get(0).contains("*") && !keys.get(1).contains("*")){
                name = keys.get(0);
                author = keys.get(1);
                loader.searchTrackByNameAndPerformer(name, author);
            }
            else if (!keys.get(0).contains("*")) {
                name = keys.get(0);
                loader.searchTrackName(name);
            }
            else if (!keys.get(1).contains("*")) {
                author = keys.get(1);
                loader.searchTrackAuthor(author);
            }
            else if (!keys.get(2).contains("*")) {
                album = keys.get(2);
                loader.searchTrackAlbum(album);
            }
            else if (!keys.get(3).contains("*")) {
                genre = keys.get(3);
                loader.searchTrackByGenre(genre);
            }
        }else {
            genre = keys.get(0);
            loader.searchGenre(genre);
        }
    }


    public void setEntity(JSONObject js) throws IOException, ClassNotFoundException, ParseException {
        ArrayList<String> arr = JsonHelper.parseJsonToSetArr(js);
        String nameSearch = arr.get(0);
        if (js.containsValue(TRACK)) {
            int i = 1;
            String authorSearch = arr.get(i++);
            String name = arr.get(i++);
            String author = arr.get(i++);
            String album = arr.get(i++);
            String genre = arr.get(i);
            MusicTrack o = new MusicTrack(name, author, album, genre);
            loader.setTrack(authorSearch, nameSearch, o);
        } else {
            String genre = arr.get(1);
            MusicGenres o = new MusicGenres(genre);
            loader.setGenre(nameSearch, o);
        }
    }

}


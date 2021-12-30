package controller;

import model.Loader;
import model.music.MusicGenres;
import model.music.MusicTrack;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import view.*;

import java.io.*;

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
        if (js.containsValue(TRACK)) loader.addTracks(js);
        else loader.addGenres(js);
    }

    public void getEntity(JSONObject js) throws IOException, ClassNotFoundException, ParseException {
        if (js.containsValue(TRACK)) loader.getTrackList();
        else loader.getGenresList();

    }

    public void deleteEntity(JSONObject js) throws IOException, ParseException, ClassNotFoundException {
        if (js.containsValue(TRACK)) loader.delTrack(js);
        else loader.delGenre(js);
    }

    public void searchEntity(JSONObject js) throws IOException, ParseException, ClassNotFoundException {
        loader.searchEntityTemp(js);
    }


    public void setEntity(JSONObject js) throws IOException, ClassNotFoundException, ParseException {
        loader.setEntityTemp(js);
    }

}


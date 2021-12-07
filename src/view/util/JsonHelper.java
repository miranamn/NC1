package view.util;

import model.music.Entity;
import model.music.MusicGenres;
import model.music.MusicTrack;
import org.json.simple.*;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.ArrayList;

public abstract class JsonHelper {

    public static JSONObject addTrackArrayJson(String[] str) {
        JSONObject obj = new JSONObject();
        JSONObject temp;
        JSONArray arr = new JSONArray();
        obj.put("command", "add");
        obj.put("Entity", str[1]);
        String entity = str[1];
        String sub = "Track";
        if (entity.contains(sub)) {
            for (int i = 2; i < str.length; i++) {
                temp = new JSONObject();
                temp.put("idOfTrack", Integer.parseInt(str[i++]));
                temp.put("nameOfTrack", str[i++]);
                temp.put("nameOfPerformer", str[i++]);
                temp.put("nameOfAlbum", str[i++]);
                temp.put("idOfGenre", Integer.parseInt(str[i++]));
                temp.put("nameOfGenre", str[i]);
                arr.add(temp);
            }
        }
        else{
            for (int i = 2; i < str.length; i++) {
                temp = new JSONObject();
                temp.put("idOfGenre", Integer.parseInt(str[i++]));
                temp.put("nameOfGenre", str[i]);
                arr.add(temp);
            }
        }
        obj.put("Array", arr );
        return obj;
    }

    public static JSONObject addTrackArrayJson(ArrayList<MusicTrack> arr) {
        JSONObject obj = new JSONObject();
        obj.put("command", "add");
        obj.put("Entity", arr.getClass().arrayType());
        JSONArray jsArr = new JSONArray();
        JSONObject js;
        for (int i = 0; i < arr.size(); i++) {
            js = new JSONObject();
            js.put("idOfTrack", arr.get(i).getId());
            js.put("nameOfTrack", arr.get(i).getName());
            js.put("nameOfPerformer", arr.get(i).getNameOfPerformer());
            js.put("nameOfAlbum", arr.get(i).getNameOfAlbum());
            js.put("idOfGenre", arr.get(i).getGenreOfTrack().getId());
            js.put("nameOfGenre", arr.get(i).getGenreOfTrack().getName());
            jsArr.add(js);
        }
        obj.put("List", jsArr);
        return obj;
    }

    public static JSONObject addGenreArrayJson(ArrayList<MusicGenres> arr) {
        JSONObject obj = new JSONObject();
        obj.put("command", "add");
        obj.put("Entity", arr.getClass().arrayType());
        JSONArray jsArr = new JSONArray();
        JSONObject buff;
        for (int i = 0; i < arr.size(); i++) {
            buff = new JSONObject();
            buff.put("idOfGenre", arr.get(i).getId());
            buff.put("nameOfGenre", arr.get(i).getName());
            jsArr.add(buff);
        }
        obj.put("List", jsArr);
        return obj;
    }

    public static ArrayList<Entity> getArrayFromJson(JSONObject obj) throws IOException, ParseException {
        ArrayList<Entity> arr = new ArrayList<>();
        String command = (String) obj.get("command");
        String entity = (String) obj.get("Entity");
        MusicGenres genre;
        MusicTrack track;
        String sub = "Genre";
        JSONArray jsArr;
        JSONObject buff;
        if (entity.contains(sub)) {
            jsArr = (JSONArray) obj.get("Array");
            for(int i = 0; i < jsArr.size(); i++) {
                buff = (JSONObject) jsArr.get(i);
                int idOfGenre = (int) buff.get("idOfGenre");
                String nameOfGenre = (String) buff.get("nameOfGenre");
                genre = new MusicGenres(idOfGenre, nameOfGenre);
                arr.add(genre);
            }
            return arr;
        }
        jsArr = (JSONArray) obj.get("Array");
        for(int i = 0; i < jsArr.size(); i++) {
            buff = (JSONObject) jsArr.get(i);
            int idOfTrack = (int) buff.get("idOfTrack");
            String nameOfTrack = (String) buff.get("nameOfTrack");
            String nameOfPerformer = (String) buff.get("nameOfPerformer");
            String nameOfAlbum = (String) buff.get("nameOfAlbum");
            int idOfGenre = (int) buff.get("idOfGenre");
            String nameOfGenre = (String) buff.get("nameOfGenre");
            genre = new MusicGenres(idOfGenre, nameOfGenre);
            track = new MusicTrack(idOfTrack, nameOfTrack, nameOfPerformer, nameOfAlbum, genre);
            arr.add(track);
        }
        return arr;
    }


}

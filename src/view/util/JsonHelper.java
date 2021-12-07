package view.util;

import model.Loader;
import model.music.Entity;
import model.music.MusicGenres;
import model.music.MusicTrack;
import org.json.simple.*;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.ArrayList;

public abstract class JsonHelper {

    /* public static Entity getEntityFromJson(JSONObject obj) throws IOException, ParseException {
        String command = (String) obj.get("command");
        String entity = (String) obj.get("Entity");
        MusicGenres genre;
        String sub = "Genre";
        if (entity.contains(sub)) {
            int idOfGenre = (int) obj.get("idOfGenre");
            String nameOfGenre = (String) obj.get("nameOfGenre");
            genre = new MusicGenres(idOfGenre, nameOfGenre);
            return genre;
        }
        int idOfTrack = (int) obj.get("idOfTrack");
        String nameOfTrack = (String) obj.get("nameOfTrack");
        String nameOfPerformer = (String) obj.get("nameOfPerformer");
        String nameOfAlbum = (String) obj.get("nameOfAlbum");
        int idOfGenre = (int) obj.get("idOfGenre");
        String nameOfGenre = (String) obj.get("nameOfGenre");
        genre = new MusicGenres(idOfGenre, nameOfGenre);
        return new MusicTrack(idOfTrack, nameOfTrack, nameOfPerformer, nameOfAlbum, genre);
    }

    public static JSONObject AddEntityMakeJson(String[] str){
        JSONObject obj = new JSONObject();
        obj.put("command", "add");
        obj.put("Entity", str[1]);
        int i = 2;
        String entity = str[1];
        String sub = "Track";
        if (entity.contains(sub)) {
            obj.put("idOfTrack", Integer.parseInt(str[i++]));
            obj.put("nameOfTrack", str[i++]);
            obj.put("nameOfPerformer", str[i++]);
            obj.put("nameOfAlbum", str[i++]);
        }
        obj.put("idOfGenre", Integer.parseInt(str[i++]));
        obj.put("nameOfGenre", str[i]);
        return obj;
    }*/

    //json из array листа
    public static JSONObject addTrackArrayJson(String[] str) {
        JSONObject obj = new JSONObject();
        JSONObject temp;
        JSONArray arr = new JSONArray();
        obj.put("command", "add");
        obj.put("Entity", str[1]);
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
            jsArr = (JSONArray) obj.get("List");
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

   /* public static ArrayList<MusicTrack> getTrackArrayFromJson(JSONObject obj) throws IOException, ParseException {
        ArrayList<MusicTrack> arr = new ArrayList<>();
        String command = (String) obj.get("command");
        String entity = (String) obj.get("Entity");
        MusicGenres genre;
        MusicTrack track;
        JSONArray jsArr;
        JSONObject buff;
        jsArr = (JSONArray) obj.get("List");
        for(int i = 0; i <jsArr.size(); i++) {
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
    public static ArrayList<MusicGenres> getGenreArrayFromJson(JSONObject obj) throws IOException, ParseException {
        ArrayList<MusicGenres> arr = new ArrayList<>();
        String command = (String) obj.get("command");
        String entity = (String) obj.get("Entity");
        MusicGenres genre;
        JSONArray jsArr;
        JSONObject buff;
        jsArr = (JSONArray) obj.get("List");
        for(int i = 0; i < jsArr.size(); i++) {
            buff = (JSONObject) jsArr.get(i);
            int idOfGenre = (int) buff.get("idOfGenre");
            String nameOfGenre = (String) buff.get("nameOfGenre");
            genre = new MusicGenres(idOfGenre, nameOfGenre);
            arr.add(genre);
        }
        return arr;
    }*/

    /*public static JSONObject addEntityArrayJson(ArrayList<Entity> arr){
        JSONObject obj = new JSONObject();
        obj.put("command", "add");
        obj.put("Entity", arr.getClass().arrayType());
        JSONArray jsArr = new JSONArray();
        JSONObject js;
        if(arr.getClass().arrayType() == MusicTrack.class){
            ArrayList<MusicTrack> buff = (MusicTrack) arr;
            for (int i = 0; i < arr.size(); i++) {
                js = new JSONObject();
                js.put("idOfTrack", ((MusicTrack) arr).get(i).getId());
                js.put("nameOfTrack", arr.get(i).getName());
                js.put("nameOfPerformer", arr.get(i).getNameOfPerformer());
                js.put("nameOfAlbum", arr.get(i).getNameOfAlbum());
                js.put("idOfGenre", arr.get(i).getGenreOfTrack().getId());
                js.put("nameOfGenre", arr.get(i).getGenreOfTrack().getName());
                jsArr.add(js);
            }
        }
    }*/

}

package view.util;

import model.music.Entity;
import model.music.MusicGenres;
import model.music.MusicTrack;
import org.json.simple.*;
import org.json.simple.parser.ParseException;
import java.io.IOException;

public abstract class JsonHelper {

    //метод парсинга JSONа на поля для создания объекта музыкального трека
    public static Entity getEntityFromJson(JSONObject obj) throws IOException, ParseException {
        String command = (String) obj.get("command");
        String entity = (String) obj.get("Entity");
        MusicGenres genre = new MusicGenres();
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
    }
    //json из array листа

}

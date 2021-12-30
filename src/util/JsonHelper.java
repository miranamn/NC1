package util;

import model.music.Entity;
import model.music.MusicGenres;
import model.music.MusicTrack;
import org.json.simple.*;
import org.json.simple.parser.ParseException;
import static model.Values.*;
import java.io.IOException;
import java.util.ArrayList;

public abstract class JsonHelper {

    public static JSONObject addEntityArrayJson(String[] str) {
        JSONObject obj = new JSONObject();
        JSONObject temp;
        JSONArray arr = new JSONArray();
        obj.put(COMMAND, ADD);
        obj.put(ENTITY, str[1]);
        String entity = str[1];
        if (entity.contains(TRACK)) {
            for (int i = 2; i < str.length; i++) {
                temp = new JSONObject();
                temp.put(NAME_OF_TRACK, str[i++]);
                temp.put(NAME_OF_PERFORMER, str[i++]);
                temp.put(NAME_OF_ALBUM, str[i++]);
                temp.put(NAME_OF_GENRE, str[i]);
                arr.add(temp);
            }
        } else {
            for (int i = 2; i < str.length; i++) {
                temp = new JSONObject();
                temp.put(NAME_OF_GENRE, str[i]);
                arr.add(temp);
            }
        }
        obj.put(ARRAY, arr);
        return obj;
    }

    public static ArrayList<Entity> getArrayFromJson(JSONObject obj) throws IOException, ClassNotFoundException {
        ArrayList<Entity> arr = new ArrayList<>();
        String command = (String) obj.get(COMMAND);
        String entity = (String) obj.get(ENTITY);
        MusicGenres genre;
        MusicTrack track = new MusicTrack();
        String sub = GENRE;
        JSONArray jsArr;
        JSONObject buff;
        if (entity.contains(sub)) {
            jsArr = (JSONArray) obj.get(ARRAY);
            for (int i = 0; i < jsArr.size(); i++) {
                buff = (JSONObject) jsArr.get(i);
                String nameOfGenre = (String) buff.get(NAME_OF_GENRE);
                genre = new MusicGenres(nameOfGenre);
                arr.add(genre);
            }
            return arr;
        }
        jsArr = (JSONArray) obj.get(ARRAY);
        for (int i = 0; i < jsArr.size(); i++) {
            buff = (JSONObject) jsArr.get(i);
            String nameOfTrack = (String) buff.get(NAME_OF_TRACK);
            String nameOfPerformer = (String) buff.get(NAME_OF_PERFORMER);
            String nameOfAlbum = (String) buff.get(NAME_OF_ALBUM);
            String nameOfGenre = (String) buff.get(NAME_OF_GENRE);
            if (track.createTrack(nameOfGenre)) {
                track = new MusicTrack(nameOfTrack, nameOfPerformer, nameOfAlbum, nameOfGenre);
                arr.add(track);
            }
        }
        return arr;
    }

    public static JSONObject watchEntity(String[] str) {
        JSONObject obj = new JSONObject();
        obj.put(COMMAND, WATCH);
        obj.put(ENTITY, str[1]);
        return obj;
    }

    //json метод поиска
    public static JSONObject searchEntityJson(String[] str) {
        JSONObject obj = new JSONObject();
        obj.put(COMMAND, str[0]);
        obj.put(ENTITY, str[1]);
        String entity = str[1];
        if (entity.contains(TRACK)) {
            obj.put(NAME_OF_TRACK, str[2]);
            obj.put(NAME_OF_PERFORMER, str[3]);
            obj.put(NAME_OF_ALBUM, str[4]);
            obj.put(NAME_OF_GENRE, str[5]);
        } else {
            obj.put(NAME_OF_GENRE, str[2]);
        }
        return obj;
    }

    public static ArrayList<String> parseJsonToSearchArr(JSONObject js) {
        ArrayList<String> keys = new ArrayList<>();
        String entity = (String) js.get(ENTITY);
        if (entity.contains(TRACK)) {
            keys.add((String) js.get(NAME_OF_TRACK));
            keys.add((String) js.get(NAME_OF_PERFORMER));
            keys.add((String) js.get(NAME_OF_ALBUM));
            keys.add((String) js.get(NAME_OF_GENRE));
        } else {
            keys.add((String) js.get(NAME_OF_GENRE));
        }
        return keys;
    }

    // джейсон для удаления
    public static JSONObject deleteEntityJson(String[] str) {
        JSONObject obj = new JSONObject();
        obj.put(COMMAND, str[0]);
        obj.put(ENTITY, str[1]);
        String entity = str[1];
        if (entity.contains(TRACK)) {
            obj.put(NAME_OF_TRACK, str[2]);
            obj.put(NAME_OF_PERFORMER, str[3]);
        } else obj.put(NAME_OF_GENRE, str[2]);
        return obj;
    }

    public static ArrayList<String> parseJsonToDeleteArr(JSONObject js) {
        ArrayList<String> keys = new ArrayList<>();
        String entity = (String) js.get(ENTITY);
        if (entity.contains(TRACK)) {
            keys.add((String) js.get(NAME_OF_TRACK));
            keys.add((String) js.get(NAME_OF_PERFORMER));
        } else {
            keys.add((String) js.get(NAME_OF_GENRE));
        }
        return keys;
    }

    public static JSONObject setEntityJson(String[] str) {
        JSONObject obj = new JSONObject();
        obj.put(COMMAND, SET);
        obj.put(ENTITY, str[1]);
        String entity = str[1];
        int i = 2;
        if (entity.contains(TRACK)) {
            obj.put(NAME_FOR_SEARCH, str[i++]);
            obj.put(PERFORMER_FOR_SEARCH, str[i++]);
            obj.put(NAME_OF_TRACK, str[i++]);
            obj.put(NAME_OF_PERFORMER, str[i++]);
            obj.put(NAME_OF_ALBUM, str[i++]);
            obj.put(NAME_OF_GENRE, str[i]);
        } else {
            obj = new JSONObject();
            obj.put(GENRE_FOR_SEARCH, str[i++]);
            obj.put(NAME_OF_GENRE, str[i]);
        }
        return obj;
    }

    public static ArrayList<String> parseJsonToSetArr(JSONObject js) {
        ArrayList<String> keys = new ArrayList<>();
        String entity = (String) js.get(ENTITY);
        if (entity.contains(TRACK)) {
            keys.add((String) js.get(NAME_FOR_SEARCH));
            keys.add((String) js.get(PERFORMER_FOR_SEARCH));
            keys.add((String) js.get(NAME_OF_TRACK));
            keys.add((String) js.get(NAME_OF_PERFORMER));
            keys.add((String) js.get(NAME_OF_ALBUM));
            keys.add((String) js.get(NAME_OF_GENRE));
        } else {
            keys.add((String) js.get(GENRE_FOR_SEARCH));
            keys.add((String) js.get(NAME_OF_GENRE));
        }
        return keys;
    }

    public static JSONObject modelView(ArrayList list) {
        JSONObject obj = new JSONObject();
        JSONObject temp;
        JSONArray arr = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            temp = new JSONObject();
            temp.put(ENTITY, list.get(i));
            arr.add(temp);
        }
        obj.put(ARRAY, arr);
        return obj;
    }

    public static ArrayList viewModel(JSONObject obj) throws IOException, ParseException {
        ArrayList arr = new ArrayList<>();
        Entity entity;
        JSONArray jsArr;
        JSONObject buff;
        jsArr = (JSONArray) obj.get(ARRAY);
        for (int i = 0; i < jsArr.size(); i++) {
            buff = (JSONObject) jsArr.get(i);
            entity = (Entity) buff.get(ENTITY);
            arr.add(entity);
        }
        return arr;
    }

    public static JSONObject errorJson(){
        JSONObject obj = new JSONObject();
        obj.put(COMMAND, INVALID_GENRE);
        return obj;
    }

}

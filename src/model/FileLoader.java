package model;

import model.music.Entity;
import model.music.MusicTrack;
import model.music.MusicGenres;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import view.View;
import util.JsonHelper;
import static model.Values.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class FileLoader implements Loader, Serializable {
    private View view;

    public FileLoader(View view) {
        this.view = view;
    }

    //просмотр списка сущностей
    public void getTrackList() throws IOException, ClassNotFoundException, ParseException {
        ArrayList<MusicTrack> arr = Streams.deserializeList(TRACK_FILE);
        resultTrackList(arr);
    }

    public void getGenresList() throws IOException, ClassNotFoundException, ParseException {
        ArrayList<MusicGenres> arr = Streams.deserializeList(GENRE_FILE);
        resultGenresList(arr);
    }

    //добавление сущностей в список
    public void addTracks(JSONObject js) throws IOException, ClassNotFoundException, ParseException {
        ArrayList<MusicTrack> list = Streams.deserializeList(TRACK_FILE);
        ArrayList arr = JsonHelper.getArrayFromJson(js);
        add(arr, list);
        Streams.serializeList(list, TRACK_FILE);
        resultTrackList(list);
    }

    public void addGenres(JSONObject js) throws IOException, ClassNotFoundException, ParseException {
        ArrayList<MusicGenres> list = Streams.deserializeList(GENRE_FILE);
        ArrayList arr = JsonHelper.getArrayFromJson(js);
        add(arr, list);
        Streams.serializeList(list, GENRE_FILE);
        resultGenresList(list);
    }

    public void searchEntityTemp(JSONObject js) throws IOException, ParseException, ClassNotFoundException {
        ArrayList<String> keys = JsonHelper.parseJsonToSearchArr(js);
        String name, author, album, genre = " ";
        if (js.containsValue(TRACK)) {
            if (!keys.get(0).contains("*") && !keys.get(1).contains("*")){
                name = keys.get(0);
                author = keys.get(1);
                searchTrackByNameAndPerformer(name, author);
            }
            else if (!keys.get(0).contains("*")) {
                name = keys.get(0);
                searchTrackName(name);
            }
            else if (!keys.get(1).contains("*")) {
                author = keys.get(1);
                searchTrackAuthor(author);
            }
            else if (!keys.get(2).contains("*")) {
                album = keys.get(2);
                searchTrackAlbum(album);
            }
            else if (!keys.get(3).contains("*")) {
                genre = keys.get(3);
                searchTrackByGenre(genre);
            }
        }else {
            genre = keys.get(0);
            searchGenre(genre);
        }
    }

    //метод для поиска трека по имени
    public void searchTrackName(String name) throws IOException, ClassNotFoundException, ParseException {
        ArrayList<MusicTrack> list = Streams.deserializeList(TRACK_FILE);
        ArrayList<MusicTrack> arr = new ArrayList<>();
        for (Entity i : list) {
            if (name.equals(((MusicTrack) i).getNameOfTrack()))
                arr.add((MusicTrack) i);
        }
        if (arr.size() == 0) return;
        resultTrackList(arr);
    }

    //метод для поиска трека по автору
    public void searchTrackAuthor(String author) throws IOException, ClassNotFoundException, ParseException {
        ArrayList<MusicTrack> list = Streams.deserializeList(TRACK_FILE);
        ArrayList<MusicTrack> arr = new ArrayList<>();
        for (Entity i : list) {
            if (author.equals(((MusicTrack) i).getNameOfPerformer()))
                arr.add((MusicTrack) i);
        }
        if (arr.size() == 0) return;
        resultTrackList(arr);
    }

    //метод для поиска трека по названию альбома
    public void searchTrackAlbum(String album) throws IOException, ClassNotFoundException, ParseException {
        ArrayList<MusicTrack> list = Streams.deserializeList(TRACK_FILE);
        ArrayList<MusicTrack> arr = new ArrayList<>();
        for (Entity i : list) {
            if (album.equals(((MusicTrack) i).getNameOfAlbum()))
                arr.add((MusicTrack) i);
        }
        if (arr.size() == 0) return;
        resultTrackList(arr);
    }

    //метод поиска трека по названию и исполнителю
    public void searchTrackByNameAndPerformer(String name, String author) throws IOException, ClassNotFoundException, ParseException {
        ArrayList<MusicTrack> arr = new ArrayList<>();
        ArrayList<MusicTrack> list = Streams.deserializeList(TRACK_FILE);
        for (Entity i : list) {
            if (name.equals(((MusicTrack) i).getNameOfTrack()) && author.equals(((MusicTrack) i).getNameOfPerformer()))
                arr.add((MusicTrack) i);
        }
        if (arr.size() == 0) return;
        resultTrackList(arr);
    }

    public void searchTrackByGenre(String name) throws IOException, ClassNotFoundException, ParseException {
        ArrayList<MusicTrack> list = Streams.deserializeList(TRACK_FILE);
        ArrayList<MusicTrack> arr = new ArrayList<>();
        for (Entity i : list) {
            if (name.equals(((MusicTrack) i).nameOfGenreById()))
                arr.add((MusicTrack) i);
        }
        if (arr.size() == 0) return;
        resultTrackList(arr);
    }

    //метод поиска жанра
    public void searchGenre(String genre) throws IOException, ParseException, ClassNotFoundException {
        ArrayList<MusicGenres> arr = new ArrayList<>();
        ArrayList<MusicGenres> list = Streams.deserializeList(GENRE_FILE);
        for (Entity i : list) {
            if (genre.equals(((MusicGenres) i).getNameOfGenre())) arr.add((MusicGenres) i);
        }
        if (arr.size() == 0) return;
        resultGenresList(arr);
    }

    //поиск для изменения
    public int searchTrackForSet(String author, String name, ArrayList<MusicTrack> list){
        int index = 0;
        for (Entity i : list) {
            if (name.equals(((MusicTrack) i).getNameOfTrack()) && author.equals(((MusicTrack) i).getNameOfPerformer())) {
                return index;
            }
            index++;
        }
        return -1;
    }
    //поиск для удаления
    public MusicTrack searchTrackForDelete(String author, String name, ArrayList<MusicTrack> list) {
        for (Entity i : list) {
            if (name.equals(((MusicTrack) i).getNameOfTrack()) && author.equals(((MusicTrack) i).getNameOfPerformer())) {
                return (MusicTrack) i;
            }
        }
        return null;
    }

    //поиск для изменения
    public int searchGenreForOperations(String name, ArrayList<MusicGenres> list){
        int index = 0;
        for (Entity i : list) {
            if (name.equals(i.getName())) {
                return index;
            }
            index++;
        }
        return -1;
    }

    //поиск для удаления
    public MusicGenres searchGenreForDelete(String name, ArrayList<MusicGenres> list) {
        for (Entity i : list) {
            if (name.equals(i.getName())) {
                MusicGenres genre = (MusicGenres) i;
                return genre;
            }
        }
        return null;
    }

    public void delGenre(JSONObject js) throws IOException, ClassNotFoundException, ParseException {
        ArrayList<MusicGenres> arrGenres = Streams.deserializeList(GENRE_FILE);
        ArrayList<String> str = JsonHelper.parseJsonToDeleteArr(js);
        String name = str.get(0);
        MusicGenres buff = searchGenreForDelete(name, arrGenres);
        arrGenres.remove(buff);
        Streams.serializeList(arrGenres, GENRE_FILE);
        resultGenresList(arrGenres);
    }

    public void delTrack(JSONObject js) throws IOException, ClassNotFoundException, ParseException {
        ArrayList<MusicTrack> arrTrack = Streams.deserializeList(TRACK_FILE);
        ArrayList<String> str = JsonHelper.parseJsonToDeleteArr(js);
        String track = str.get(0);
        String author = str.get(1);
        MusicTrack buff = searchTrackForDelete(author, track, arrTrack);
        arrTrack.remove(buff);
        Streams.serializeList(arrTrack, TRACK_FILE);
        resultTrackList(arrTrack);
    }

    public void setEntityTemp(JSONObject js) throws IOException, ClassNotFoundException, ParseException {
        ArrayList<String> arr = JsonHelper.parseJsonToSetArr(js);
        String nameSearch = arr.get(0);
        if (js.containsValue(TRACK)) {
            int i = 1;
            MusicTrack o = new MusicTrack();
            String authorSearch = arr.get(i++);
            String name = arr.get(i++);
            String author = arr.get(i++);
            String album = arr.get(i++);
            String genre = arr.get(i);
            if (o.createTrack(genre)) {
                o = new MusicTrack(name, author, album, genre);
                setTrack(authorSearch, nameSearch, o);
            }
        } else {
            String genre = arr.get(1);
            MusicGenres o = new MusicGenres(genre);
            setGenre(nameSearch, o);
        }
    }

    //сеттер трека в списке
    public void setTrack(String author, String name, MusicTrack o) throws IOException, ClassNotFoundException, ParseException {
        ArrayList<MusicTrack> list = Streams.deserializeList(TRACK_FILE);
        int index = searchTrackForSet(author, name, list);
        if(index == -1){
            //throw new Exceptions.ListIndexOutOfBoundException("Error, track was not found");
            view.errorList(JsonHelper.errorJson());
        }
        else {
            list.set(index, o);
            Streams.serializeList(list, TRACK_FILE);
            resultTrackList(list);
        }
    }

    //сеттер жанра в списке
    public void setGenre(String name, MusicGenres o) throws IOException, ClassNotFoundException, ParseException {
        ArrayList<MusicGenres> list = Streams.deserializeList(GENRE_FILE);
        int index = searchGenreForOperations(name, list);
        if(index == -1){
            //throw new Exceptions.ListIndexOutOfBoundException("Error, genre was not found");
            view.errorList(JsonHelper.errorJson());
        }
        else{
            list.set(index, o);
            Streams.serializeList(list, GENRE_FILE);
            resultGenresList(list);
        }
    }

    //геттер трека из списка
    public MusicTrack getTrack(int n) throws IOException, ClassNotFoundException {
        ArrayList<MusicTrack> list = Streams.deserializeList(TRACK_FILE);
        return list.get(n);
    }

    //геттер жанра из списка
    public MusicGenres getGenres(int n) throws IOException, ClassNotFoundException {
        ArrayList<MusicGenres> list = Streams.deserializeList(GENRE_FILE);
        return list.get(n);
    }

    private void add(ArrayList arr, ArrayList list) {
        for (int i = 0; i < arr.size(); i++) {
            list.add(arr.get(i));
        }
    }

    //результирующий метод
    public void resultTrackList(ArrayList<MusicTrack> arr) throws IOException, ParseException {
        view.stringList(JsonHelper.modelView(arr));
    }
    //результирующий метод
    public void resultGenresList(ArrayList<MusicGenres> arr) throws IOException, ParseException {
        view.stringList(JsonHelper.modelView(arr));
    }

    //реализация toString() для списка
    @Override
    public String toString() {
        ArrayList list = null;
        try {
            list = Streams.deserializeList(TRACK_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        StringBuffer myBuffer = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            myBuffer.append(i + list.get(i).toString());
        }
        return myBuffer.toString();
    }
}
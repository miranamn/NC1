package model;

import model.music.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public interface Loader {

    public void searchTrackName(String name) throws IOException, ClassNotFoundException, ParseException;

    public void searchTrackAuthor(String author) throws IOException, ClassNotFoundException, ParseException;

    public void searchTrackAlbum(String album) throws IOException, ClassNotFoundException, ParseException;

    public void searchTrackByNameAndPerformer(String name, String author) throws IOException, ClassNotFoundException, ParseException;

    public void searchTrackByGenre(String name) throws IOException, ClassNotFoundException, ParseException;

    public void searchGenre(String genre) throws IOException, ParseException, ClassNotFoundException;

    public void delTrack(String perf, String track) throws IOException, ClassNotFoundException, ParseException;

    public void delGenre(String name) throws IOException, ClassNotFoundException, ParseException;

    //метод добавления треков в список
    public void addTracks(ArrayList<MusicTrack> arr) throws IOException, ClassNotFoundException, ParseException;

    //метод добавления жанров в список
    public void addGenres(ArrayList<MusicGenres> arr) throws IOException, ClassNotFoundException, ParseException;

    //метод изменения трека
    public void setTrack(String author, String name, MusicTrack o) throws IOException, ClassNotFoundException, ParseException;

    //метод изменение жанра
    public void setGenre(String name, MusicGenres o) throws IOException, ClassNotFoundException, ParseException;

    //геттер трека из списка
    public MusicTrack getTrack(int n) throws IOException, ClassNotFoundException;

    //геттер жанра из списка
    public MusicGenres getGenres(int n) throws IOException, ClassNotFoundException;

    //геттер списка треков
    public void getTrackList() throws IOException, ClassNotFoundException, ParseException;

    //геттер списка жанров
    public void getGenresList() throws IOException, ClassNotFoundException, ParseException;

    //результативный метод списка треков
    public void resultTrackList(ArrayList<MusicTrack> arr) throws IOException, ParseException, ClassNotFoundException;

    //результативный метод списка жанров
    public void resultGenresList(ArrayList<MusicGenres> arr) throws IOException, ParseException, ClassNotFoundException;

    public String toString();

}
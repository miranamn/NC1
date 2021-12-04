package model;

import model.music.Entity;
import model.music.MusicGenres;
import model.music.MusicTrack;
import view.View;

import java.io.Serializable;
import java.util.ArrayList;

public class GenreListLoader implements Loader, Serializable {
    private ArrayList<MusicGenres> list;
    private View view;

    public GenreListLoader(View view) {
        this.view = view;
        list = new ArrayList<MusicGenres>();
    }

    public GenreListLoader(ArrayList<MusicGenres> list) {
        this.list = list;
    }

    public GenreListLoader(){
        list = new ArrayList<MusicGenres>();
    }

    public void addEntity(Entity o){
        list.add((MusicGenres) o);
    }

    public void delEntity(int n){
        list.remove(n);
    }

    public void setEntity(int n, Object o){
        list.set(n, (MusicGenres) o);
    }

    public Object getEntity(int n){
        return (Object) list.get(n);
    }

    public int getSize(){
        return list.size();
    }

    public ArrayList<MusicGenres> getList() {
        return list;
    }

    @Override
    public String toString() {
        StringBuffer myBuffer = new StringBuffer();
        for(MusicGenres genre : list){
            myBuffer.append(genre.toString()).append(" ");
        }
        return myBuffer.toString();
    }
}

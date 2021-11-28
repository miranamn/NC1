package model;

import model.music.MusicGenres;
import model.music.MusicTrack;

import java.io.Serializable;
import java.util.ArrayList;

public class GenreListLoader implements Loader, Serializable {
    private ArrayList<MusicGenres> list;

    public GenreListLoader(ArrayList<MusicGenres> list) {
        this.list = list;
    }

    public void addEntity(int n, Object o){
        list.add(n, (MusicGenres) o);
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

    @Override
    public String toString() {
        StringBuffer myBuffer = new StringBuffer();
        for(MusicGenres genre : list){
            myBuffer.append(genre.toString()).append(" ");
        }
        return myBuffer.toString();
    }
}

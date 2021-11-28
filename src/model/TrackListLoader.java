package model;

import model.music.MusicTrack;

import java.io.Serializable;
import java.util.ArrayList;

public class TrackListLoader implements Loader, Serializable {
    private ArrayList<MusicTrack> list;

    public TrackListLoader(ArrayList<MusicTrack> list) {
        this.list = list;
    }

    public void addEntity(int n, Object o){
        list.add(n, (MusicTrack) o);
    }

    public void delEntity(int n){
        list.remove(n);
    }

    public void setEntity(int n, Object o){
        list.set(n, (MusicTrack) o);
    }

    public Object getEntity(int n){
        return (Object) list.get(n);
    }

    public ArrayList<MusicTrack> getList() {
        return list;
    }

    public void setList(ArrayList<MusicTrack> list) {
        this.list = list;
    }

    //реализация toString() для списка
    @Override
    public String toString() {
        StringBuffer myBuffer = new StringBuffer();
        for(MusicTrack track : list){
            myBuffer.append(track.toString());
        }
        return myBuffer.toString();
    }
}

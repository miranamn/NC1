package model;

import model.music.Entity;
import model.music.MusicTrack;
import view.View;

import java.io.Serializable;
import java.util.ArrayList;

public class TrackListLoader implements Loader, Serializable {
    private ArrayList<MusicTrack> list;
    private View view;

    public TrackListLoader(View view) {
        this.view = view;
        list = new ArrayList<>();
    }

    public TrackListLoader(){
        list = new ArrayList<MusicTrack>();
    }

    public TrackListLoader(ArrayList<MusicTrack> list) {
        this.list = list;
    }

    public void addEntity(Entity o){
        list.add((MusicTrack) o);
    }

    public int[] searchEntity(String[] str) {
        String nameOfPerformer = str[0];
        String nameOfSong = str[1];
        int count = 0;
        int[] arr = new int[count];
        for(MusicTrack i: list){
            if(nameOfPerformer == i.getNameOfPerformer() && nameOfSong == i.getNameOfTrack()){
                arr[count] = i.getIdOfTrack();
                count++;
            }
        }
        if(count == 0) {
            System.out.println("No matches, sorry");
            return null;
        }
        else return arr;
    }

    public void setEntityList(ArrayList<Entity> arr) {
        for(int i = 0; i < arr.size(); i++){
            list.add(i, (MusicTrack) arr.get(i));
        }
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

    public int getSize(){
        return list.size();
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

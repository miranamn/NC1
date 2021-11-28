package model;

import model.music.MusicTrack;

import java.util.ArrayList;

public interface Loader {
    public void addEntity(int n, Object o);

    public void delEntity(int n);

    public void setEntity(int n, Object o);

    public Object getEntity(int n);

}
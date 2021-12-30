package model.music;

import model.InvalidGenreException;
import model.Streams;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import static model.Values.GENRE_FILE;

public class MusicGenres extends Entity implements Serializable {

    public MusicGenres(){}

    public MusicGenres(String name){
        super(name);
    }

    public String getNameOfGenre(){
        return super.getName();
    }

    @Override
    public UUID getId() {
        return super.getId();
    }

    public void setNameOfGenre(String name){
        super.setName(name);
    }

    @Override
    public String toString() {
        return " [name]: " + getName() + "\n";
    }
}

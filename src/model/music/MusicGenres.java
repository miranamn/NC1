package model.music;

import java.io.Serializable;

public class MusicGenres extends Entity implements Serializable {

    public MusicGenres(int id, String name){
        super(id, name);
    }

    public int getIdOfTrack(){
        return super.getId();
    }

    public String getNameOfTrack(){
        return super.getName();
    }

    public void setIdOfTrack(int id){
        super.setId(id);
    }

    public void setNameOfTrack(String name){
        super.setName(name);
    }

    @Override
    public String toString() {
        return " [id]: " + getId() + " [name]: " + getName() + "\n";
    }


}

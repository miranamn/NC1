package model.music;

import model.InvalidGenreException;
import model.Streams;
import util.JsonHelper;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;
import static model.Values.*;

public class MusicTrack extends Entity implements Serializable {
    private String nameOfPerformer;
    private String nameOfAlbum;
    private UUID idOfGenre;

    public MusicTrack(){}

    public MusicTrack(String name, String nameOfPerformer, String nameOfAlbum, String nameOfGenre) throws IOException, ClassNotFoundException {
        super(name);
        ArrayList<MusicGenres> genres = Streams.deserializeList(GENRE_FILE);
        for(int i = 0; i < genres.size(); i++){
            if(nameOfGenre.equals(genres.get(i).getNameOfGenre())){
                this.idOfGenre = genres.get(i).getId();
                this.nameOfPerformer = nameOfPerformer;
                this.nameOfAlbum = nameOfAlbum;
            }
        }
    }

    public boolean createTrack(String nameOfGenre){
        ArrayList<MusicGenres> genres = null;
        try {
            genres = Streams.deserializeList(GENRE_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < genres.size(); i++) {
            if(nameOfGenre.equals(genres.get(i).getNameOfGenre())) return true;
        }
        //JsonHelper.errorJson();
        return false;
    }

    public String nameOfGenreById() {
        String realName = " ";
        try {
            ArrayList<MusicGenres> genres = Streams.deserializeList(GENRE_FILE);
            for(int i = 0; i < genres.size(); i++) {
                if(idOfGenre.equals(genres.get(i).getId())){
                    realName = genres.get(i).getName();
                    break;
                }
            }
            return realName;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidGenreException wow){
            //формируем джейсон
        }
        return realName;
    }

    public UUID getIdOfGenre(){
        return idOfGenre;
    }

    public String getNameOfTrack(){
        return super.getName();
    }

    public void setNameOfTrack(String name){
        super.setName(name);
    }

    public String getNameOfPerformer() {
        return nameOfPerformer;
    }

    public void setNameOfPerformer(String nameOfPerformer) {
        this.nameOfPerformer = nameOfPerformer;
    }

    public String getNameOfAlbum() {
        return nameOfAlbum;
    }

    public void setNameOfAlbum(String nameOfAlbum) {
        this.nameOfAlbum = nameOfAlbum;
    }

    @Override
    public String toString() {
        return "[Name]: " + getName() + " [Performer Name]: " + nameOfPerformer
                + " [Album]: " + nameOfAlbum + " [Genre name]:" + nameOfGenreById() + "\n";
    }
}

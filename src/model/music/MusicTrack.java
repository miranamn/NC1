package model.music;

import model.Streams;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;
import static model.Values.*;

public class MusicTrack extends Entity implements Serializable {
    private String nameOfPerformer;
    private String nameOfAlbum;
    private UUID idOfGenre;
    private String nameOfGenre;

    public MusicTrack(){}

    public MusicTrack(String name, String nameOfPerformer, String nameOfAlbum, String nameOfGenre) throws IOException, ClassNotFoundException {
        super(name);
        ArrayList<MusicGenres> genres = Streams.deserializeList(GENRE_FILE);
        this.nameOfGenre = nameOfGenre;
        for(int i = 0; i < genres.size(); i++){
            if(nameOfGenre.equals(genres.get(i).getNameOfGenre())){
                this.idOfGenre = genres.get(i).getId();
            }
        }
        this.nameOfPerformer = nameOfPerformer;
        this.nameOfAlbum = nameOfAlbum;
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

    public String getNameOfGenre() {
        return nameOfGenre;
    }

    public void setNameOfGenre(String nameOfGenre) {
        this.nameOfGenre = nameOfGenre;
    }

    @Override
    public String toString() {
        return "[Name]: " + getName() + " [Performer Name]: " + nameOfPerformer
                + " [Album]: " + nameOfAlbum + " [Genre name]:" + nameOfGenre + "\n";
    }
}

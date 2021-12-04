package model.music;

import java.io.Serializable;

public class MusicTrack extends Entity implements Serializable {
    private String nameOfPerformer;
    private String nameOfAlbum;
    private MusicGenres genreOfTrack;

    public MusicTrack(){}

    public MusicTrack(int id, String name, String nameOfPerformer, String nameOfAlbum, MusicGenres genreOfTrack) {
        super(id, name);
        this.nameOfPerformer = nameOfPerformer;
        this.nameOfAlbum = nameOfAlbum;
        this.genreOfTrack = genreOfTrack;
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

    public MusicGenres getGenreOfTrack() {
        return genreOfTrack;
    }

    public void setGenreOfTrack(MusicGenres genreOfTrack) {
        this.genreOfTrack = genreOfTrack;
    }

    @Override
    public String toString() {
        return getIdOfTrack() + ") " +  "[Name]: " + getName() + " [Performer Name]: " + getNameOfPerformer()
                + " [Album]: " + getNameOfAlbum() + " [Genre ID]:" + getGenreOfTrack().getId() + " [Genre]: "
                + getGenreOfTrack().getName() + "\n";
    }
}

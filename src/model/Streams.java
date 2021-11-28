package model;

import model.music.MusicGenres;
import model.music.MusicTrack;

import java.io.*;
import java.util.ArrayList;

public abstract class Streams {
    public static void writeList (TrackListLoader list, Writer out)throws IOException {
        PrintWriter writer = new PrintWriter(out);
        int length = list.getList().size();
        for(int i = 0; i < length; i++){
            writer.print(i + 1 + ") ");
            writer.print(" '" + list.getList().get(i).getNameOfTrack() + "' ");
            writer.print(" '" + list.getList().get(i).getNameOfPerformer() + "' ");
            writer.print(" '" + list.getList().get(i).getNameOfAlbum() + "' ");
            writer.print(list.getList().get(i).getGenreOfTrack().getId() + " ");
            writer.print(" '" + list.getList().get(i).getGenreOfTrack().getName() + "'\n");
        }
        writer.close();
    }
    public static TrackListLoader readList (Reader in) throws IOException{
        StreamTokenizer of = new StreamTokenizer(in);
        ArrayList<MusicTrack> music = new ArrayList<>();
        while (of.nextToken() != StreamTokenizer.TT_EOF) {
            int id = (int) of.nval;
            of.nextToken();
            String name = of.sval;
            of.nextToken();
            String nameAuthor = of.sval;
            of.nextToken();
            String nameAlbum = of.sval;
            int gId = (int) of.nval;
            of.nextToken();
            String nameG = of.sval;
            of.nextToken();
            MusicGenres temp = new MusicGenres(gId, nameG);
            MusicTrack musicTrack = new MusicTrack(id, name, nameAuthor, nameAlbum, temp);
            music.add(musicTrack);
        }
        TrackListLoader list = new TrackListLoader(music);
        return list;
    }

    public static void serializeList (TrackListLoader list, OutputStream out) throws IOException {
        ObjectOutputStream of = new ObjectOutputStream(out);
        of.writeObject(list);
    }
    public static TrackListLoader deserializeList (InputStream in) throws IOException, ClassNotFoundException{
        ObjectInputStream is = new ObjectInputStream(in);
        TrackListLoader list = (TrackListLoader) is.readObject();
        return list;
    }
}

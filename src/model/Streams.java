package model;

import model.music.MusicGenres;
import model.music.MusicTrack;
import java.io.*;
import java.util.ArrayList;

public abstract class Streams{

    //запись в файл
    public static void writeList (Loader list, Writer out)throws IOException {
        PrintWriter writer = new PrintWriter(out);
        int length = list.getSize();
        if(list instanceof TrackListLoader) {
            TrackListLoader temp = (TrackListLoader) list;
            for (int i = 0; i < length; i++) {
                writer.print(temp.getList().get(i).getId() + " ");
                writer.print(temp.getList().get(i).getNameOfTrack() + " ");
                writer.print(temp.getList().get(i).getNameOfPerformer() + " ");
                writer.print(temp.getList().get(i).getNameOfAlbum() + " ");
                writer.print(temp.getList().get(i).getGenreOfTrack().getId() + " ");
                writer.print(temp.getList().get(i).getGenreOfTrack().getName() + "\n");
            }
        } else {
            GenreListLoader temp = (GenreListLoader) list;
            for(int i = 0; i < length; i++){
                writer.print(temp.getList().get(i).getId() + " ");
                writer.print(temp.getList().get(i).getName() + "\n");
            }
        }
        writer.close();
    }

    public static TrackListLoader readList (Reader in) throws IOException{
        BufferedReader reader = new BufferedReader(in);
        ArrayList<MusicTrack> music = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] str = line.split(" ");
            int iterator = 0;
            int id = Integer.parseInt(str[iterator]);
            iterator++;
            String name = str[iterator];
            iterator++;
            String author = str[iterator];
            iterator++;
            String album = str[iterator];
            iterator++;
            int gId = Integer.parseInt(str[iterator]);
            iterator++;
            String genre = str[iterator];
            iterator++;
            MusicGenres temp = new MusicGenres(gId, genre);
            MusicTrack musicTrack = new MusicTrack(id, name, author, album, temp);
            music.add(musicTrack);
        }
        TrackListLoader list = new TrackListLoader(music);
        return list;
    }

    public static void serializeList (Loader list, OutputStream out) throws IOException {
        ObjectOutputStream of = new ObjectOutputStream(out);
        of.writeObject(list);
        of.close();
    }
    public static Loader deserializeList (InputStream in) throws IOException, ClassNotFoundException{
        ObjectInputStream is = new ObjectInputStream(in);
        Loader list = (TrackListLoader) is.readObject();
        return list;
    }
}

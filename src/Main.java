import model.Loader;
import model.Streams;
import model.TrackListLoader;
import model.music.MusicGenres;
import model.music.MusicTrack;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        //проверка. Создаю музыкальные жанры
        MusicGenres hipHop = new MusicGenres(1, "HipHop");
        MusicGenres classic = new MusicGenres(2, "Classic Music");
        //создаю треки
        MusicTrack aboba = new MusicTrack(1, "Trap House", "Kizaru", "Aboba", hipHop);
        MusicTrack LovTrack = new MusicTrack(2, "Ride", "21Pilots", "DirtySoil", classic);
        MusicTrack winter = new MusicTrack(3, "winter", "Vivaldi", "Seasons", hipHop);
        //добавляю треки
        ArrayList<MusicTrack> list = new ArrayList<>();
        list.add(aboba);
        list.add(LovTrack);
        list.add(winter);
        TrackListLoader list1 = new TrackListLoader(list);
        System.out.println(list1.toString());

        System.out.println();
        /*  FileWriter writer = null;
        try {
            writer = new FileWriter("tracks.txt");
            Streams.writeList(list1, writer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        FileReader reader = null;
        try {
            reader = new FileReader("tracks.txt");
            TrackListLoader tempList = Streams.readList(reader);
            System.out.println(tempList.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
      /*  try {
            FileOutputStream os = new FileOutputStream("ser.txt");
            Streams.serializeList(list1, os);
            FileInputStream fos = new FileInputStream("ser.txt");
            TrackListLoader list2 = Streams.deserializeList(fos);
            System.out.println(list2.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }*/

    }
}

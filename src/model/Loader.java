package model;
import model.music.*;

import java.util.ArrayList;

public interface Loader {

    public void addEntity(Entity o);

    public void delEntity(int n);

    public int[] searchEntity(String[] str);

    public void setEntityList(ArrayList<Entity> arr);

    public void setEntity(int n, Object o);

    public Object getEntity(int n);

    public int getSize();

    public String toString();

}
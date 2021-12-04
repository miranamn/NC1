package model;
import model.music.*;

public interface Loader {

    public void addEntity(Entity o);


    public void delEntity(int n);

    public void setEntity(int n, Object o);

    public Object getEntity(int n);

    public int getSize();

    public String toString();

}
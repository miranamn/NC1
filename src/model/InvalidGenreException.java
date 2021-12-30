package model;

public class InvalidGenreException extends IllegalArgumentException{
    public InvalidGenreException(String string) {
        System.out.println(string);
    }
}

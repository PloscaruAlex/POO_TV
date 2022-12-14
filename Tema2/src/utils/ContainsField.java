package utils;

import io.ContainsInput;

import java.util.ArrayList;

public class ContainsField {
    private ArrayList<String> actors;
    private ArrayList<String> genre;

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(final ArrayList<String> genre) {
        this.genre = genre;
    }

    public void setContainsFromInput(ContainsInput contains) {
        if (contains == null) {
            return;
        }
        this.actors = contains.getActors();
        this.genre = contains.getGenre();
    }
}

package utils;

import io.ContainsInput;
import java.util.ArrayList;

public final class ContainsField {
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

    /**
     * Sets the values of the contains field from the input files.
     * @param contains the input for the contains field
     */
    public void setContainsFromInput(final ContainsInput contains) {
        if (contains == null) {
            return;
        }
        this.actors = contains.getActors();
        this.genre = contains.getGenre();
    }
}

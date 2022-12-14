package utils;

import io.SortInput;

public class SortField {
    private String rating;
    private String duration;

    public String getRating() {
        return rating;
    }

    public void setRating(final String rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(final String duration) {
        this.duration = duration;
    }

    public void setSortFromInput(SortInput sort) {
        if (sort == null) {
            return;
        }
        this.rating = sort.getRating();
        this.duration = sort.getDuration();
    }
}

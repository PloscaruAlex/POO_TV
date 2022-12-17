package utils;

import io.SortInput;

public final class SortField {
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

    /**
     * Sets the values of the sort field from the input files.
     * @param sort the input for the sort field
     */
    public void setSortFromInput(final SortInput sort) {
        if (sort == null) {
            return;
        }
        this.rating = sort.getRating();
        this.duration = sort.getDuration();
    }
}

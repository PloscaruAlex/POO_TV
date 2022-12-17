package functionality;

import io.MoviesInput;
import java.util.ArrayList;

/**
 * Movie class to help store movies.
 */
public class Movie {
    private String name;
    private int year;
    private int duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;
    private int numLikes;
    private double rating;
    private int numRatings;
    private ArrayList<Double> ratingsList = new ArrayList<Double>();

    /**
     * Sets the values of the movie's parameters from the input files.
     *
     * @param movie the movie
     */
    public void setMovieFromInput(final MoviesInput movie) {
        this.name = movie.getName();
        this.year = movie.getYear();
        this.duration = movie.getDuration();
        this.genres = new ArrayList<String>(movie.getGenres());
        this.actors = new ArrayList<String>(movie.getActors());
        this.countriesBanned = new ArrayList<String>(movie.getCountriesBanned());
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets year.
     *
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets year.
     *
     * @param year the year
     */
    public void setYear(final int year) {
        this.year = year;
    }

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(final int duration) {
        this.duration = duration;
    }

    /**
     * Gets genres.
     *
     * @return the genres
     */
    public ArrayList<String> getGenres() {
        return genres;
    }

    /**
     * Sets genres.
     *
     * @param genres the genres
     */
    public void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }

    /**
     * Gets actors.
     *
     * @return the actors
     */
    public ArrayList<String> getActors() {
        return actors;
    }

    /**
     * Sets actors.
     *
     * @param actors the actors
     */
    public void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    /**
     * Gets countries banned.
     *
     * @return the countries banned
     */
    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    /**
     * Sets countries banned.
     *
     * @param countriesBanned the countries banned
     */
    public void setCountriesBanned(final ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    /**
     * Gets num likes.
     *
     * @return the num likes
     */
    public int getNumLikes() {
        return numLikes;
    }

    /**
     * Sets num likes.
     *
     * @param numLikes the num likes
     */
    public void setNumLikes(final int numLikes) {
        this.numLikes = numLikes;
    }

    /**
     * Gets rating.
     *
     * @return the rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(final double rating) {
        this.rating = rating;
    }

    /**
     * Gets num ratings.
     *
     * @return the num ratings
     */
    public int getNumRatings() {
        return numRatings;
    }

    /**
     * Sets num ratings.
     *
     * @param numRatings the num ratings
     */
    public void setNumRatings(final int numRatings) {
        this.numRatings = numRatings;
    }

    /**
     * Adds a new rating to the ratings list and calculates the new rating of the movie.
     *
     * @param r the new user rating
     */
    public void addNewRating(final double r) {
        ratingsList.add(r);
        numRatings++;
        double sum = 0;
        for (Double d : ratingsList) {
            sum += d;
        }
        rating = sum / numRatings;
    }
}

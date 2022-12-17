package functionality;

import io.UsersInput;

import java.util.ArrayList;

/**
 * User class to help manage all the users in the system and the current user logged in.
 */
public class User {
    private Credentials credentials = new Credentials();
    private int tokensCount = 0;
    private int numFreePremiumMovies = Constants.NUMBER_OF_FREE_MOVIES_PREMIUM_USER;
    private ArrayList<Movie> purchasedMovies = new ArrayList<Movie>();
    private ArrayList<Movie> watchedMovies = new ArrayList<Movie>();
    private ArrayList<Movie> likedMovies = new ArrayList<Movie>();
    private ArrayList<Movie> ratedMovies = new ArrayList<Movie>();

    /**
     * Initializes the values from the input files.
     *
     * @param user the user
     */
    public void setUserFromInput(final UsersInput user) {
        this.credentials.setCredentialsFromInput(user.getCredentials());
    }

    /**
     * Gets credentials.
     *
     * @return the credentials
     */
    public Credentials getCredentials() {
        return credentials;
    }

    /**
     * Sets credentials.
     *
     * @param credentials the credentials
     */
    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    /**
     * Gets tokens count.
     *
     * @return the tokens count
     */
    public int getTokensCount() {
        return tokensCount;
    }

    /**
     * Sets tokens count.
     *
     * @param tokensCount the tokens count
     */
    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    /**
     * Gets num free premium movies.
     *
     * @return the num free premium movies
     */
    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    /**
     * Sets num free premium movies.
     *
     * @param numFreePremiumMovies the num free premium movies
     */
    public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    /**
     * Gets purchased movies.
     *
     * @return the purchased movies
     */
    public ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    /**
     * Sets purchased movies.
     *
     * @param purchasedMovies the purchased movies
     */
    public void setPurchasedMovies(final ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    /**
     * Gets watched movies.
     *
     * @return the watched movies
     */
    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    /**
     * Sets watched movies.
     *
     * @param watchedMovies the watched movies
     */
    public void setWatchedMovies(final ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    /**
     * Gets liked movies.
     *
     * @return the liked movies
     */
    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    /**
     * Sets liked movies.
     *
     * @param likedMovies the liked movies
     */
    public void setLikedMovies(final ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    /**
     * Gets rated movies.
     *
     * @return the rated movies
     */
    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    /**
     * Sets rated movies.
     *
     * @param ratedMovies the rated movies
     */
    public void setRatedMovies(final ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }
}

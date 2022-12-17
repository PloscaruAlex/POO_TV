package actions;

import functionality.Credentials;
import functionality.CurrentSession;
import io.ActionsInput;
import utils.FiltersField;

/**
 * This is the abstract parent class that is used to generate all the other action classes.
 */
public abstract class Action {
    private String type;
    private String page;
    private String feature;
    private Credentials credentials = new Credentials();
    private String movie;
    private int rate;
    private String startsWith;
    private String count;
    private FiltersField filters = new FiltersField();
    private CurrentSession session;

    /**
     * Initializes the action with the fields read from the input files.
     *
     * @param action the input action from file
     */
    public void setActionFromInput(final ActionsInput action) {
        this.type = action.getType();
        this.page = action.getPage();
        this.feature = action.getFeature();
        this.credentials.setCredentialsFromInput(action.getCredentials());
        this.movie = action.getMovie();
        this.rate = action.getRate();
        this.startsWith = action.getStartsWith();
        this.count = action.getCount();
        this.filters.setFiltersFromInput(action.getFilters());
    }

    /**
     * The function that is specific to every action.
     */
    public abstract void doAction();

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Gets page.
     *
     * @return the page
     */
    public String getPage() {
        return page;
    }

    /**
     * Sets page.
     *
     * @param page the page
     */
    public void setPage(final String page) {
        this.page = page;
    }

    /**
     * Gets feature.
     *
     * @return the feature
     */
    public String getFeature() {
        return feature;
    }

    /**
     * Sets feature.
     *
     * @param feature the feature
     */
    public void setFeature(final String feature) {
        this.feature = feature;
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
     * Gets movie.
     *
     * @return the movie
     */
    public String getMovie() {
        return movie;
    }

    /**
     * Sets movie.
     *
     * @param movie the movie
     */
    public void setMovie(final String movie) {
        this.movie = movie;
    }

    /**
     * Gets rate.
     *
     * @return the rate
     */
    public int getRate() {
        return rate;
    }

    /**
     * Sets rate.
     *
     * @param rate the rate
     */
    public void setRate(final int rate) {
        this.rate = rate;
    }

    /**
     * Gets starts with.
     *
     * @return the starts with
     */
    public String getStartsWith() {
        return startsWith;
    }

    /**
     * Sets starts with.
     *
     * @param startsWith the starts with
     */
    public void setStartsWith(final String startsWith) {
        this.startsWith = startsWith;
    }

    /**
     * Gets count.
     *
     * @return the count
     */
    public String getCount() {
        return count;
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public void setCount(final String count) {
        this.count = count;
    }

    /**
     * Gets filters.
     *
     * @return the filters
     */
    public FiltersField getFilters() {
        return filters;
    }

    /**
     * Sets filters.
     *
     * @param filters the filters
     */
    public void setFilters(final FiltersField filters) {
        this.filters = filters;
    }

    /**
     * Gets session.
     *
     * @return the session
     */
    public CurrentSession getSession() {
        return session;
    }

    /**
     * Sets session.
     *
     * @param session the session
     */
    public void setSession(final CurrentSession session) {
        this.session = session;
    }
}

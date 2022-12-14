package actions;

import functionality.Credentials;
import functionality.CurrentSession;
import io.ActionsInput;
import utils.FiltersField;

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

    public void setActionFromInput(ActionsInput action) {
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

    public abstract void doAction();

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(final String page) {
        this.page = page;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(final String feature) {
        this.feature = feature;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getStartsWith() {
        return startsWith;
    }

    public void setStartsWith(String startsWith) {
        this.startsWith = startsWith;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public FiltersField getFilters() {
        return filters;
    }

    public void setFilters(FiltersField filters) {
        this.filters = filters;
    }

    public CurrentSession getSession() {
        return session;
    }

    public void setSession(CurrentSession session) {
        this.session = session;
    }
}

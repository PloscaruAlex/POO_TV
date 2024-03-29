package functionality;

import actions.Action;
import actions.ActionFactory;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.ActionsInput;
import io.Input;
import io.MoviesInput;
import io.UsersInput;
import pages.HomepageUnauthenticated;
import pages.Page;

import java.util.ArrayList;

/**
 * Current session class, helps to manage the current connection to the platform.
 */
public final class CurrentSession {
    private ArrayList<User> databaseUsers;
    private ArrayList<Movie> databaseMovies;
    private ArrayList<Action> actions;
    private User currentUser;
    private Page currentPage;
    private ArrayNode output;
    private ArrayList<Movie> currentMovieList;
    private static final CurrentSession instance = new CurrentSession();

    private CurrentSession() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static CurrentSession getInstance() {
        return instance;
    }

    /**
     * Gets database users.
     *
     * @return the database users
     */
    public ArrayList<User> getDatabaseUsers() {
        return databaseUsers;
    }

    /**
     * Sets database users.
     *
     * @param databaseUsers the database users
     */
    public void setDatabaseUsers(final ArrayList<User> databaseUsers) {
        this.databaseUsers = databaseUsers;
    }

    /**
     * Gets database movies.
     *
     * @return the database movies
     */
    public ArrayList<Movie> getDatabaseMovies() {
        return databaseMovies;
    }

    /**
     * Sets database movies.
     *
     * @param databaseMovies the database movies
     */
    public void setDatabaseMovies(final ArrayList<Movie> databaseMovies) {
        this.databaseMovies = databaseMovies;
    }

    /**
     * Gets current user.
     *
     * @return the current user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets current user.
     *
     * @param currentUser the current user
     */
    public void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Gets actions.
     *
     * @return the actions
     */
    public ArrayList<Action> getActions() {
        return actions;
    }

    /**
     * Sets actions.
     *
     * @param actions the actions
     */
    public void setActions(final ArrayList<Action> actions) {
        this.actions = actions;
    }

    /**
     * Gets current page.
     *
     * @return the current page
     */
    public Page getCurrentPage() {
        return currentPage;
    }

    /**
     * Sets current page.
     *
     * @param currentPage the current page
     */
    public void setCurrentPage(final Page currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * Gets output.
     *
     * @return the output
     */
    public ArrayNode getOutput() {
        return output;
    }

    /**
     * Sets output.
     *
     * @param output the output
     */
    public void setOutput(final ArrayNode output) {
        this.output = output;
    }

    /**
     * Gets current movie list.
     *
     * @return the current movie list
     */
    public ArrayList<Movie> getCurrentMovieList() {
        return currentMovieList;
    }

    /**
     * Sets current movie list.
     *
     * @param currentMovieList the current movie list
     */
    public void setCurrentMovieList(final ArrayList<Movie> currentMovieList) {
        this.currentMovieList = currentMovieList;
    }

    /**
     * Initializes the current session with the values from the input files.
     *
     * @param inputData the input data
     */
    public void startWith(final Input inputData) {
        databaseUsers = new ArrayList<User>();
        databaseMovies = new ArrayList<Movie>();
        actions = new ArrayList<Action>();
        for (UsersInput userInput : inputData.getUsers()) {
            User user = new User();
            user.setUserFromInput(userInput);
            this.databaseUsers.add(user);
        }
        for (MoviesInput movieInput : inputData.getMovies()) {
            Movie movie = new Movie();
            movie.setMovieFromInput(movieInput);
            this.databaseMovies.add(movie);
        }
        for (ActionsInput actionsInput : inputData.getActions()) {
            Action action = ActionFactory.createAction(actionsInput, getInstance());
            action.setActionFromInput(actionsInput);
            this.actions.add(action);
        }
        this.currentUser = null;
        this.currentPage = HomepageUnauthenticated.getInstance();
        this.currentMovieList = this.databaseMovies;
    }

    /**
     * Cleares all the data from the current session.
     */
    public void stopSession() {
        this.currentUser = null;
        this.currentPage = HomepageUnauthenticated.getInstance();
        this.databaseUsers = null;
        this.databaseMovies = null;
        this.actions = null;
    }

    /**
     * The index in the database of the user with the name.
     *
     * @param name the name
     * @return the index
     */
    public int userExists(final String name) {
        int index = -1;
        for (int i = 0; i < getDatabaseUsers().size(); i++) {
            if (getDatabaseUsers().get(i).getCredentials().getName().equals(name)) {
                index = i;
            }
        }
        return index;
    }
}

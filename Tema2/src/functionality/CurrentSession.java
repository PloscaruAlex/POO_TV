package functionality;

import actions.Action;
import actions.ActionFactory;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.ActionsInput;
import io.Input;
import io.MoviesInput;
import io.UsersInput;
import pages.HomepageUnauthenticated;
import pages.Page;

import java.util.ArrayList;

public class CurrentSession {
    private ArrayList<User> databaseUsers;
    private ArrayList<Movie> databaseMovies;
    private ArrayList<Action> actions;
    private User currentUser;
    private Page currentPage;
    private ArrayNode output;
    private ArrayList<Movie> currentMovieList;
    private final static CurrentSession instance = new CurrentSession();

    private CurrentSession() {}

    public static CurrentSession getInstance() {
        return instance;
    }

    public ArrayList<User> getDatabaseUsers() {
        return databaseUsers;
    }

    public void setDatabaseUsers(ArrayList<User> databaseUsers) {
        this.databaseUsers = databaseUsers;
    }

    public ArrayList<Movie> getDatabaseMovies() {
        return databaseMovies;
    }

    public void setDatabaseMovies(ArrayList<Movie> databaseMovies) {
        this.databaseMovies = databaseMovies;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Page currentPage) {
        this.currentPage = currentPage;
    }

    public ArrayNode getOutput() {
        return output;
    }

    public void setOutput(ArrayNode output) {
        this.output = output;
    }

    public ArrayList<Movie> getCurrentMovieList() {
        return currentMovieList;
    }

    public void setCurrentMovieList(ArrayList<Movie> currentMovieList) {
        this.currentMovieList = currentMovieList;
    }

    public void startWith(Input inputData) {
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
        for (ActionsInput actionsInput : inputData.getActions()){
            Action action = ActionFactory.createAction(actionsInput, getInstance());
            action.setActionFromInput(actionsInput);
            this.actions.add(action);
        }
        this.currentUser = null;
        this.currentPage = HomepageUnauthenticated.getInstance();
        this.currentMovieList = this.databaseMovies;
    }

    public void stopSession() {
        this.currentUser = null;
        this.currentPage = HomepageUnauthenticated.getInstance();
        this.databaseUsers = null;
        this.databaseMovies = null;
        this.actions = null;
    }

    public int userExists(String name) {
        int index = -1;
        for (int i = 0; i < getDatabaseUsers().size(); i++) {
            if (getDatabaseUsers().get(i).getCredentials().getName().equals(name)) {
                index = i;
            }
        }
        return index;
    }
}

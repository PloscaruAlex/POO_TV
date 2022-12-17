package actions;

import functionality.CurrentSession;
import functionality.Movie;
import functionality.OutputHelper;
import functionality.User;
import pages.PageFactory;

import java.util.ArrayList;

/**
 * Like action class.
 */
public class Like extends Action {
    /**
     * Instantiates a new Like action.
     *
     * @param session the session
     */
    public Like(final CurrentSession session) {
        this.setSession(session);
    }

    /**
     * This is the function that performs the like action.
     */
    public void doAction() {
        ArrayList<String> allowedActions =
                this.getSession().getCurrentPage().getActionsThatCanBePerformed();
        if (!allowedActions.contains(this.getFeature())) {
            if (this.getSession().getCurrentUser() != null) {
                this.getSession().getOutput().add(OutputHelper.error(this.getSession()));
                return;
            }
            this.getSession().getOutput().add(OutputHelper.error(this.getSession()));
            this.getSession().setCurrentPage(PageFactory.createPage("homepageUnauthenticated"));
            this.getSession().setCurrentUser(null);
            return;
        }

        User user = this.getSession().getCurrentUser();
        Movie movie = this.getSession().getCurrentMovieList().get(0);
        if (movie == null) {
            this.getSession().getOutput().add(OutputHelper.error(this.getSession()));
            return;
        }

        boolean watched = false;
        for (Movie m : this.getSession().getCurrentUser().getWatchedMovies()) {
            if (m.getName().equals(movie.getName())) {
                watched = true;
                break;
            }
        }

        if (!watched) {
            this.getSession().getOutput().add(OutputHelper.error(this.getSession()));
            return;
        }

        user.getLikedMovies().add(movie);
        movie.setNumLikes(movie.getNumLikes() + 1);
        this.getSession().getOutput().add(OutputHelper.details(movie, user));
    }
}

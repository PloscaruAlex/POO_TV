package actions;

import functionality.CurrentSession;
import functionality.OutputHelper;
import functionality.Constants;
import functionality.User;
import functionality.Movie;
import pages.PageFactory;

import java.util.ArrayList;

/**
 * Rate action class.
 */
public class Rate extends Action {
    /**
     * Instantiates a new Rate action.
     *
     * @param session the session
     */
    public Rate(final CurrentSession session) {
        this.setSession(session);
    }

    /**
     * This is the function that performs the rate action.
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

        if (this.getRate() < 0 || this.getRate() > Constants.MAX_RATING) {
            this.getSession().getOutput().add(OutputHelper.error(this.getSession()));
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

        movie.addNewRating(this.getRate());
        user.getRatedMovies().add(movie);
        this.getSession().getOutput().add(OutputHelper.details(movie, user));
    }
}

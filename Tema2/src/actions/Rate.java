package actions;

import functionality.CurrentSession;
import functionality.Movie;
import functionality.OutputHelper;
import functionality.User;
import pages.PageFactory;

import java.util.ArrayList;

public class Rate extends Action {
    public Rate(CurrentSession session) {
        this.setSession(session);
    }

    public void doAction() {
        ArrayList<String> allowedActions = this.getSession().getCurrentPage().getActionsThatCanBePerformed();
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
            if (m != null && m.getName().equals(movie.getName())) {
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

package actions;

import functionality.CurrentSession;
import functionality.Movie;
import functionality.OutputHelper;
import functionality.User;
import pages.PageFactory;

import java.util.ArrayList;

public class Watch extends Action {
    public Watch(CurrentSession session) {
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

        boolean purchased = false;
        for (Movie m : this.getSession().getCurrentUser().getPurchasedMovies()) {
            if (m.getName().equals(movie.getName())) {
                purchased = true;
                break;
            }
        }
        if (!purchased) {
            this.getSession().getOutput().add(OutputHelper.error(this.getSession()));
            return;
        }

        user.getWatchedMovies().add(movie);
        this.getSession().getOutput().add(OutputHelper.details(movie, user));
    }
}

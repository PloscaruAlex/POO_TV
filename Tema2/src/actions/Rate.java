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

        for (Movie m : this.getSession().getCurrentUser().getWatchedMovies()) {
            if (!m.getName().contains(this.getMovie())) {
                this.getSession().getOutput().add(OutputHelper.error(this.getSession()));
                return;
            }
        }

        User user = this.getSession().getCurrentUser();
        Movie movie = null;
        for (Movie m : this.getSession().getDatabaseMovies()) {
            if (m.getName().equals(this.getMovie())) {
                movie = m;
                break;
            }
        }

        movie.addNewRating(this.getRate());
        user.getRatedMovies().add(movie);
        this.getSession().getOutput().add(OutputHelper.details(movie, user));
    }
}

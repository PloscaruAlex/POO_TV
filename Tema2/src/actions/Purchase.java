package actions;

import functionality.CurrentSession;
import functionality.Movie;
import functionality.OutputHelper;
import functionality.User;
import pages.PageFactory;

import java.util.ArrayList;

public class Purchase extends Action {
    public Purchase(CurrentSession session) {
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

        for (Movie m : this.getSession().getCurrentUser().getPurchasedMovies()) {
            if (m.getName().contains(this.getMovie())) {
                this.getSession().getOutput().add(OutputHelper.error(this.getSession()));
                return;
            }
        }

        User user = this.getSession().getCurrentUser();
        if (user.getCredentials().getAccountType().equals("standard")) {
            int tokens = user.getTokensCount();
            if (tokens < 2) {
                this.getSession().getOutput().add(OutputHelper.error(this.getSession()));
                return;
            }

            Movie movie = null;
            for (Movie m : this.getSession().getDatabaseMovies()) {
                if (m.getName().equals(this.getMovie())) {
                    movie = m;
                    break;
                }
            }
            if (movie == null) {
                this.getSession().getOutput().add(OutputHelper.error(this.getSession()));
                return;
            }

            user.getPurchasedMovies().add(movie);
            tokens -= 2;
            user.setTokensCount(tokens);
        } else {
            Movie movie = null;
            for (Movie m : this.getSession().getDatabaseMovies()) {
                if (m.getName().equals(this.getMovie())) {
                    movie = m;
                    break;
                }
            }
            if (movie == null) {
                this.getSession().getOutput().add(OutputHelper.error(this.getSession()));
                return;
            }

            if (user.getNumFreePremiumMovies() == 0) {
                int tokens = user.getTokensCount();
                if (tokens < 2) {
                    this.getSession().getOutput().add(OutputHelper.error(this.getSession()));
                    return;
                }

                tokens -= 2;
                user.setTokensCount(tokens);
            } else {
                user.setNumFreePremiumMovies(user.getNumFreePremiumMovies() - 1);
            }

            user.getPurchasedMovies().add(movie);
        }
    }
}

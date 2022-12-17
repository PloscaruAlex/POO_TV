package actions;

import functionality.CurrentSession;
import functionality.Movie;
import functionality.OutputHelper;
import functionality.User;
import pages.PageFactory;

import java.util.ArrayList;

/**
 * Purchase action class.
 */
public class Purchase extends Action {
    /**
     * Instantiates a new Purchase action.
     *
     * @param session the session
     */
    public Purchase(final CurrentSession session) {
        this.setSession(session);
    }

    /**
     * This is the function that performs the purchase action.
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

        for (Movie m : this.getSession().getCurrentUser().getPurchasedMovies()) {
            if (m.getName().equals(this.getSession().getCurrentMovieList().get(0).getName())) {
                this.getSession().getOutput().add(OutputHelper.error(this.getSession()));
                return;
            }
        }

        User user = this.getSession().getCurrentUser();

        Movie movie = this.getSession().getCurrentMovieList().get(0);

        if (movie == null) {
            this.getSession().getOutput().add(OutputHelper.error(this.getSession()));
            return;
        }

        if (user.getCredentials().getAccountType().equals("standard")) {
            int tokens = user.getTokensCount();
            if (tokens < 2) {
                this.getSession().getOutput().add(OutputHelper.error(this.getSession()));
                return;
            }

            tokens -= 2;
            user.setTokensCount(tokens);
        } else {
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
        }

        user.getPurchasedMovies().add(movie);
        this.getSession().getOutput().add(OutputHelper.details(movie, user));
    }
}

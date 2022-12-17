package actions;

import functionality.CurrentSession;
import functionality.Movie;
import functionality.OutputHelper;
import pages.PageFactory;

import java.util.ArrayList;

/**
 * Search action class.
 */
public class Search extends Action {
    /**
     * Instantiates a new Search action.
     *
     * @param session the session
     */
    public Search(final CurrentSession session) {
        this.setSession(session);
    }

    /**
     * This is the function that performs the search action.
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

        ArrayList<Movie> searchedMovies = new ArrayList<Movie>();
        for (Movie m : this.getSession().getCurrentMovieList()) {
            if (m.getName().startsWith(this.getStartsWith())) {
                searchedMovies.add(m);
            }
        }
        String country = this.getSession().getCurrentUser().getCredentials().getCountry();
        searchedMovies.removeIf((m) -> m.getCountriesBanned().contains(country));

        if (searchedMovies.size() != 0) {
            this.getSession().setCurrentMovieList(searchedMovies);
        }
        this.getSession().getOutput().add(
                OutputHelper.search(searchedMovies, this.getSession().getCurrentUser())
        );
    }
}

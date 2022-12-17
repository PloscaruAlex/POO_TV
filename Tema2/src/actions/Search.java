package actions;

import functionality.CurrentSession;
import functionality.Movie;
import functionality.OutputHelper;
import pages.PageFactory;

import java.util.ArrayList;

public class Search extends Action {
    public Search(CurrentSession session) {
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

        ArrayList<Movie> searchedMovies = new ArrayList<Movie>();
        for (Movie m : this.getSession().getCurrentMovieList()) {
            if (m.getName().startsWith(this.getStartsWith())) {
                searchedMovies.add(m);
            }
        }
        String country = this.getSession().getCurrentUser().getCredentials().getCountry();
        searchedMovies.removeIf((m) -> m.getCountriesBanned().contains(country));

        this.getSession().getOutput().add(OutputHelper.search(searchedMovies, this.getSession().getCurrentUser()));
    }
}

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
        for (Movie m : this.getSession().getDatabaseMovies()) {
            int index = m.getName().indexOf(this.getStartsWith());
            if (index != -1) {
                searchedMovies.add(m);
            }
        }
        this.getSession().getOutput().add(OutputHelper.search(searchedMovies, this.getSession().getCurrentUser()));
    }
}

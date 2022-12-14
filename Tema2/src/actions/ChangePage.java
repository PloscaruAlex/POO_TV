package actions;

import functionality.CurrentSession;
import functionality.Movie;
import functionality.OutputHelper;
import pages.PageFactory;

import java.util.ArrayList;

public class ChangePage extends Action {
    public ChangePage(CurrentSession session) {
        this.setSession(session);
    }
    public void doAction() {
        ArrayList<String> allowedPages = this.getSession().getCurrentPage().getPagesThatCanBeAccessed();
        if (!allowedPages.contains(this.getPage())) {
            if (this.getSession().getCurrentUser() != null) {
                this.getSession().getOutput().add(OutputHelper.error(this.getSession()));
                return;
            }
            this.getSession().getOutput().add(OutputHelper.error(this.getSession()));
            this.getSession().setCurrentPage(PageFactory.createPage("homepageUnauthenticated"));
            this.getSession().setCurrentUser(null);
            return;
        }

        this.getSession().setCurrentPage(PageFactory.createPage(this.getPage()));

        if (this.getPage().equals("movies")) {
            this.getSession().getOutput().add(OutputHelper.moviesInfo(this.getSession()));
        }

        if (this.getPage().equals("see details")) {
            Movie movie = null;
            for (Movie m : this.getSession().getDatabaseMovies()) {
                if (m.getName().equals(this.getMovie())) {
                    movie = m;
                    break;
                }
            }
            if (movie != null && !movie.getCountriesBanned().contains(this.getSession().getCurrentUser().getCredentials().getCountry())) {
                this.getSession().getOutput().add(OutputHelper.movieOutputHelper(movie));
            } else {
                this.getSession().getOutput().add(OutputHelper.error(this.getSession()));
            }
        }

        if (this.getPage().equals("logout")) {
            this.getSession().setCurrentPage(PageFactory.createPage("homepageUnauthenticated"));
            this.getSession().setCurrentUser(null);
        }
    }
}

package actions;

import functionality.CurrentSession;
import functionality.Movie;
import functionality.OutputHelper;
import pages.PageFactory;

import java.util.ArrayList;

/**
 * Change page action class.
 */
public class ChangePage extends Action {
    /**
     * Instantiates a new Change page action.
     *
     * @param session the session
     */
    public ChangePage(final CurrentSession session) {
        this.setSession(session);
    }

    /**
     * This is the function that performs the change page action.
     */
    public void doAction() {
        ArrayList<String> allowedPages =
                this.getSession().getCurrentPage().getPagesThatCanBeAccessed();
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
            this.getSession().setCurrentMovieList(
                    new ArrayList<Movie>(this.getSession().getDatabaseMovies())
            );
            this.getSession().getCurrentMovieList().removeIf(
                    (m) -> m.getCountriesBanned().contains(
                            this.getSession().getCurrentUser().getCredentials().getCountry()
                    )
            );
            this.getSession().getOutput().add(OutputHelper.moviesInfo(this.getSession()));
        }

        if (this.getPage().equals("homepageAuthenticated")) {
            this.getSession().setCurrentMovieList(
                    new ArrayList<Movie>(this.getSession().getDatabaseMovies())
            );
            this.getSession().getCurrentMovieList().removeIf(
                    (m) -> m.getCountriesBanned().contains(
                            this.getSession().getCurrentUser().getCredentials().getCountry()
                    )
            );
        }

        if (this.getPage().equals("see details")) {
            Movie movie = null;
            for (Movie m : this.getSession().getCurrentMovieList()) {
                if (m.getName().equals(this.getMovie())) {
                    movie = m;
                    break;
                }
            }
            if (movie != null) {
                this.getSession().getOutput().add(
                        OutputHelper.details(movie, this.getSession().getCurrentUser())
                );
                ArrayList<Movie> movies = new ArrayList<Movie>();
                movies.add(movie);
                this.getSession().setCurrentMovieList(movies);
            } else {
                this.getSession().getOutput().add(OutputHelper.error(this.getSession()));
                this.getSession().setCurrentPage(PageFactory.createPage("movies"));
            }
        }

        if (this.getPage().equals("logout")) {
            this.getSession().setCurrentPage(PageFactory.createPage("homepageUnauthenticated"));
            this.getSession().setCurrentUser(null);
            this.getSession().setCurrentMovieList(null);
        }
    }
}

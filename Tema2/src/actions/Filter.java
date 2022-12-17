package actions;

import functionality.CurrentSession;
import functionality.Movie;
import functionality.OutputHelper;
import pages.PageFactory;

import java.util.ArrayList;
import java.util.Comparator;

public class Filter extends Action {
    public Filter(CurrentSession session) {
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

        ArrayList<Movie> movies = new ArrayList<Movie>(this.getSession().getCurrentMovieList());
        String country = this.getSession().getCurrentUser().getCredentials().getCountry();
        movies.removeIf((m) -> m.getCountriesBanned().contains(country));

        if (this.getFilters().getSort().getRating() != null && this.getFilters().getSort().getDuration() != null) {
            if (this.getFilters().getSort().getRating().equals("decreasing")) {
                if (this.getFilters().getSort().getDuration().equals("decreasing")) {
                    movies.sort(new Comparator<Movie>() {
                        @Override
                        public int compare(Movie m1, Movie m2) {
                            int d1 = m1.getDuration();
                            int d2 = m2.getDuration();
                            int durationComparator = Integer.compare(d2, d1);
                            if (durationComparator != 0) {
                                return durationComparator;
                            }
                            double r1 = m1.getRating();
                            double r2 = m2.getRating();
                            return Double.compare(r2, r1);
                        }
                    });
                } else {
                    movies.sort(new Comparator<Movie>() {
                        @Override
                        public int compare(Movie m1, Movie m2) {
                            int d1 = m1.getDuration();
                            int d2 = m2.getDuration();
                            int durationComparator = Integer.compare(d1, d2);
                            if (durationComparator != 0) {
                                return durationComparator;
                            }
                            double r1 = m1.getRating();
                            double r2 = m2.getRating();
                            return Double.compare(r2, r1);
                        }
                    });
                }
            } else {
                if (this.getFilters().getSort().getDuration().equals("decreasing")) {
                    movies.sort(new Comparator<Movie>() {
                        @Override
                        public int compare(Movie m1, Movie m2) {
                            int d1 = m1.getDuration();
                            int d2 = m2.getDuration();
                            int durationComparator = Integer.compare(d2, d1);
                            if (durationComparator != 0) {
                                return durationComparator;
                            }
                            double r1 = m1.getRating();
                            double r2 = m2.getRating();
                            return Double.compare(r1, r2);
                        }
                    });
                } else {
                    movies.sort(new Comparator<Movie>() {
                        @Override
                        public int compare(Movie m1, Movie m2) {
                            int d1 = m1.getDuration();
                            int d2 = m2.getDuration();
                            int durationComparator = Integer.compare(d1, d2);
                            if (durationComparator != 0) {
                                return durationComparator;
                            }
                            double r1 = m1.getRating();
                            double r2 = m2.getRating();
                            return Double.compare(r1, r2);
                        }
                    });
                }
            }
        } else if (this.getFilters().getSort().getRating() != null) {
            if (this.getFilters().getSort().getRating().equals("decreasing")) {
                movies.sort((m1, m2) -> Double.compare(m2.getRating(), m1.getRating()));
            } else {
                movies.sort((m1, m2) -> Double.compare(m1.getRating(), m2.getRating()));
            }
        } else if (this.getFilters().getSort().getDuration() != null) {
            if (this.getFilters().getSort().getDuration().equals("decreasing")) {
                movies.sort((m1, m2) -> Integer.compare(m2.getDuration(), m1.getDuration()));
            } else {
                movies.sort((m1, m2) -> Integer.compare(m1.getDuration(), m2.getDuration()));
            }
        }

        if (this.getFilters().getContains() != null) {
            ArrayList<String> actors = this.getFilters().getContains().getActors();
            ArrayList<String> genre = this.getFilters().getContains().getGenre();

            if (actors != null) {
                for (String actor : actors) {
                    movies.removeIf((m) -> !m.getActors().contains(actor));
                }
            }

            if (genre != null) {
                for (String g : genre) {
                    movies.removeIf((m) -> !m.getGenres().contains(g));
                }
            }
        }
        this.getSession().setCurrentMovieList(movies);
        this.getSession().getOutput().add(OutputHelper.filter(this.getSession().getCurrentMovieList(), this.getSession().getCurrentUser()));
    }
}

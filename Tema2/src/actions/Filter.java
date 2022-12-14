package actions;

import functionality.CurrentSession;
import functionality.Movie;
import functionality.OutputHelper;
import pages.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
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

        ArrayList<Movie> movies = new ArrayList<Movie>(this.getSession().getDatabaseMovies());
        if (this.getFilters().getSort().getRating() != null && this.getFilters().getSort().getDuration() != null) {
            if (this.getFilters().getSort().getRating().equals("decreasing")) {
                if (this.getFilters().getSort().getDuration().equals("decreasing")) {
                    movies.sort(new Comparator<Movie>() {
                        @Override
                        public int compare(Movie m1, Movie m2) {
                            double r1 = m1.getRating();
                            double r2 = m2.getRating();
                            int ratingComparator = Double.compare(r2, r1);
                            if (ratingComparator != 0) {
                                return ratingComparator;
                            }
                            int d1 = m1.getDuration();
                            int d2 = m2.getDuration();
                            return Integer.compare(d2, d1);
                        }
                    });
                } else {
                    movies.sort(new Comparator<Movie>() {
                        @Override
                        public int compare(Movie m1, Movie m2) {
                            double r1 = m1.getRating();
                            double r2 = m2.getRating();
                            int ratingComparator = Double.compare(r2, r1);
                            if (ratingComparator != 0) {
                                return ratingComparator;
                            }
                            int d1 = m1.getDuration();
                            int d2 = m2.getDuration();
                            return Integer.compare(d1, d2);
                        }
                    });
                }
            } else {
                if (this.getFilters().getSort().getDuration().equals("decreasing")) {
                    movies.sort(new Comparator<Movie>() {
                        @Override
                        public int compare(Movie m1, Movie m2) {
                            double r1 = m1.getRating();
                            double r2 = m2.getRating();
                            int ratingComparator = Double.compare(r1, r2);
                            if (ratingComparator != 0) {
                                return ratingComparator;
                            }
                            int d1 = m1.getDuration();
                            int d2 = m2.getDuration();
                            return Integer.compare(d2, d1);
                        }
                    });
                } else {
                    movies.sort(new Comparator<Movie>() {
                        @Override
                        public int compare(Movie m1, Movie m2) {
                            double r1 = m1.getRating();
                            double r2 = m2.getRating();
                            int ratingComparator = Double.compare(r1, r2);
                            if (ratingComparator != 0) {
                                return ratingComparator;
                            }
                            int d1 = m1.getDuration();
                            int d2 = m2.getDuration();
                            return Integer.compare(d1, d2);
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

            movies.removeIf((m) -> !containsThis(m, actors, genre));
        }

        this.getSession().getOutput().add(OutputHelper.filter(movies, this.getSession().getCurrentUser()));
    }

    private boolean containsThis(Movie m, ArrayList<String> a, ArrayList<String> g) {
        if (a != null) {
            for (String actor : m.getActors()) {
                if (!a.contains(actor)) {
                    return false;
                }
            }
        }
        if (g != null) {
            for (String genre : m.getGenres()) {
                if (!g.contains(genre)) {
                    return false;
                }
            }
        }
        return true;
    }
}

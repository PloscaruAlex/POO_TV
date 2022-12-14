package pages;

import java.util.ArrayList;

public class MoviesPage extends Page {
    private ArrayList<String> pagesThatCanBeAccessed = new ArrayList<String>() {
        {
            add("homepageAuthenticated");
            add("see details");
            add("logout");
        }
    };
    private ArrayList<String> actionsThatCanBePerformed = new ArrayList<String>() {
        {
            add("search");
            add("filter");
        }
    };

    private static MoviesPage instance = new MoviesPage();

    private MoviesPage() {}

    public static MoviesPage getInstance() {
        return instance;
    }

    public ArrayList<String> getPagesThatCanBeAccessed() {
        return pagesThatCanBeAccessed;
    }

    public void setPagesThatCanBeAccessed(ArrayList<String> pagesThatCanBeAccessed) {
        this.pagesThatCanBeAccessed = pagesThatCanBeAccessed;
    }

    public ArrayList<String> getActionsThatCanBePerformed() {
        return actionsThatCanBePerformed;
    }

    public void setActionsThatCanBePerformed(ArrayList<String> actionsThatCanBePerformed) {
        this.actionsThatCanBePerformed = actionsThatCanBePerformed;
    }
}

package pages;

import java.util.ArrayList;

public class HomepageAuthenticated extends Page{
    private ArrayList<String> pagesThatCanBeAccessed = new ArrayList<String>() {
        {
            add("movies");
            add("upgrades");
            add("logout");
        }
    };
    private ArrayList<String> actionsThatCanBePerformed = new ArrayList<String>() {
        {
            add("none");
        }
    };

    private static HomepageAuthenticated instance = new HomepageAuthenticated();

    private HomepageAuthenticated() {}

    public static HomepageAuthenticated getInstance() {
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

package pages;

import java.util.ArrayList;

public class HomepageUnauthenticated extends Page {
    private ArrayList<String> pagesThatCanBeAccessed = new ArrayList<String>() {
        {
            add("login");
            add("register");
        }
    };
    private ArrayList<String> actionsThatCanBePerformed = new ArrayList<String>() {
        {
            add("none");
        }
    };

    private static HomepageUnauthenticated instance = new HomepageUnauthenticated();

    private HomepageUnauthenticated() {}

    public static HomepageUnauthenticated getInstance() {
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

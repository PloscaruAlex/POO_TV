package pages;

import java.util.ArrayList;

public class UpgradesPage extends Page {
    private ArrayList<String> pagesThatCanBeAccessed = new ArrayList<String>() {
        {
            add("homepageAuthenticated");
            add("movies");
            add("logout");
        }
    };
    private ArrayList<String> actionsThatCanBePerformed = new ArrayList<String>() {
        {
            add("buy tokens");
            add("buy premium account");
        }
    };

    private static UpgradesPage instance = new UpgradesPage();

    private UpgradesPage() {}

    public static UpgradesPage getInstance() {
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

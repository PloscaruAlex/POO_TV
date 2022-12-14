package pages;

import java.util.ArrayList;

public abstract class Page {
    ArrayList<String> pagesThatCanBeAccessed = new ArrayList<String>();
    ArrayList<String> actionsThatCanBePerformed = new ArrayList<String>();

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

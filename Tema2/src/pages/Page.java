package pages;

import java.util.ArrayList;

/**
 * This is the abstract class that is the parent of all the pages.
 */
public abstract class Page {
    /**
     * The Pages that can be accessed.
     */
    private ArrayList<String> pagesThatCanBeAccessed = new ArrayList<String>();
    /**
     * The Actions that can be performed.
     */
    private ArrayList<String> actionsThatCanBePerformed = new ArrayList<String>();

    /**
     * Gets pages that can be accessed.
     *
     * @return the pages that can be accessed
     */
    public ArrayList<String> getPagesThatCanBeAccessed() {
        return pagesThatCanBeAccessed;
    }

    /**
     * Sets pages that can be accessed.
     *
     * @param pagesThatCanBeAccessed the pages that can be accessed
     */
    public void setPagesThatCanBeAccessed(final ArrayList<String> pagesThatCanBeAccessed) {
        this.pagesThatCanBeAccessed = pagesThatCanBeAccessed;
    }

    /**
     * Gets actions that can be performed.
     *
     * @return the actions that can be performed
     */
    public ArrayList<String> getActionsThatCanBePerformed() {
        return actionsThatCanBePerformed;
    }

    /**
     * Sets actions that can be performed.
     *
     * @param actionsThatCanBePerformed the actions that can be performed
     */
    public void setActionsThatCanBePerformed(final ArrayList<String> actionsThatCanBePerformed) {
        this.actionsThatCanBePerformed = actionsThatCanBePerformed;
    }
}

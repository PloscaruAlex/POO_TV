package pages;

import java.util.ArrayList;

public class RegisterPage extends Page {
    private ArrayList<String> pagesThatCanBeAccessed = new ArrayList<String>() {
        {
            add("none");
        }
    };
    private ArrayList<String> actionsThatCanBePerformed = new ArrayList<String>() {
        {
            add("register");
        }
    };

    private static RegisterPage instance = new RegisterPage();

    private RegisterPage() {}

    public static RegisterPage getInstance() {
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

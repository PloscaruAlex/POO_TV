package pages;

import java.util.ArrayList;

public class LogoutPage extends Page {
    private ArrayList<String> pagesThatCanBeAccessed = new ArrayList<String>() {
        {
            add("none");
        }
    };
    private ArrayList<String> actionsThatCanBePerformed = new ArrayList<String>() {
        {
            add("none");
        }
    };

    private static LogoutPage instance = new LogoutPage();

    private LogoutPage() {}

    public static LogoutPage getInstance() {
        return instance;
    }
}

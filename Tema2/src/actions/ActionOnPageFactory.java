package actions;

import functionality.CurrentSession;
import io.ActionsInput;

/**
 * This is the factory that generates every action on page.
 */
public final class ActionOnPageFactory {
    /**
     * The main function in the factory that creates actions.
     *
     * @param action  the action
     * @param session the session
     * @return the action
     */
    public static Action createAction(final ActionsInput action, final CurrentSession session) {
        switch (action.getFeature()) {
            case "login":
                Login login = new Login(session);
                login.setActionFromInput(action);
                return login;
            case "register":
                Register register = new Register(session);
                register.setActionFromInput(action);
                return register;
            case "search":
                Search search = new Search(session);
                search.setActionFromInput(action);
                return search;
            case "filter":
                Filter filter = new Filter(session);
                filter.setActionFromInput(action);
                return filter;
            case "purchase":
                Purchase purchase = new Purchase(session);
                purchase.setActionFromInput(action);
                return purchase;
            case "watch":
                Watch watch = new Watch(session);
                watch.setActionFromInput(action);
                return watch;
            case "like":
                Like like = new Like(session);
                like.setActionFromInput(action);
                return like;
            case "rate":
                Rate rate = new Rate(session);
                rate.setActionFromInput(action);
                return rate;
            case "buy premium account":
                BuyPremiumAccount buyPremiumAccount = new BuyPremiumAccount(session);
                buyPremiumAccount.setActionFromInput(action);
                return buyPremiumAccount;
            case "buy tokens":
                BuyTokens buyTokens = new BuyTokens(session);
                buyTokens.setActionFromInput(action);
                return buyTokens;
            default:
                break;
        }
        throw new IllegalArgumentException("Not an action.");
    }

    private ActionOnPageFactory() {
    }
}

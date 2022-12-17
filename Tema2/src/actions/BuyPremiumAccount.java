package actions;

import functionality.Constants;
import functionality.CurrentSession;
import functionality.OutputHelper;
import pages.PageFactory;

import java.util.ArrayList;

/**
 * Buy premium account action class.
 */
public class BuyPremiumAccount extends Action {
    /**
     * Instantiates a new Buy premium account action.
     *
     * @param session the session
     */
    public BuyPremiumAccount(final CurrentSession session) {
        this.setSession(session);
    }

    /**
     * This is the function that performs the buy premium account action.
     */
    public void doAction() {
        ArrayList<String> allowedActions =
                this.getSession().getCurrentPage().getActionsThatCanBePerformed();
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

        int tokens = this.getSession().getCurrentUser().getTokensCount();
        if (tokens < Constants.PRICE_OF_PREMIUM_ACCOUNT) {
            this.getSession().getOutput().add(OutputHelper.error(this.getSession()));
            return;
        }
        tokens -= Constants.PRICE_OF_PREMIUM_ACCOUNT;
        this.getSession().getCurrentUser().setTokensCount(tokens);
        this.getSession().getCurrentUser().getCredentials().setAccountType("premium");
    }
}

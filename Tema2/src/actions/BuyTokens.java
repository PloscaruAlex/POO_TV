package actions;

import functionality.CurrentSession;
import functionality.OutputHelper;
import pages.PageFactory;

import java.util.ArrayList;

/**
 * Buy tokens action class.
 */
public class BuyTokens extends Action {
    /**
     * Instantiates a new Buy tokens action.
     *
     * @param session the session
     */
    public BuyTokens(final CurrentSession session) {
        this.setSession(session);
    }

    /**
     * This is the function that performs the buy tokens action.
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

        int balance = Integer.parseInt(
                this.getSession().getCurrentUser().getCredentials().getBalance()
        );
        int count = Integer.parseInt(this.getCount());
        int tokens = this.getSession().getCurrentUser().getTokensCount();
        if (balance < count) {
            this.getSession().getOutput().add(OutputHelper.error(this.getSession()));
            return;
        }
        balance -= count;
        tokens += count;
        this.getSession().getCurrentUser().getCredentials().setBalance(Integer.toString(balance));
        this.getSession().getCurrentUser().setTokensCount(tokens);
    }
}

package actions;

import functionality.CurrentSession;
import functionality.OutputHelper;
import pages.PageFactory;

import java.util.ArrayList;

public class BuyTokens extends Action {
    public BuyTokens(CurrentSession session) {
        this.setSession(session);
    }

    public void doAction() {
        ArrayList<String> allowedActions = this.getSession().getCurrentPage().getActionsThatCanBePerformed();
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

        int balance = Integer.parseInt(this.getSession().getCurrentUser().getCredentials().getBalance());
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

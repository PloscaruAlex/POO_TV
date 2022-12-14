package actions;

import functionality.CurrentSession;
import functionality.OutputHelper;
import pages.PageFactory;

import java.util.ArrayList;

public class BuyPremiumAccount extends Action {
    public BuyPremiumAccount(CurrentSession session) {
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

        int tokens = this.getSession().getCurrentUser().getTokensCount();
        if (tokens < 10) {
            this.getSession().getOutput().add(OutputHelper.error(this.getSession()));
            return;
        }
        tokens -= 10;
        this.getSession().getCurrentUser().setTokensCount(tokens);
        this.getSession().getCurrentUser().getCredentials().setAccountType("premium");
    }
}

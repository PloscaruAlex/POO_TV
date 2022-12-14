package actions;

import functionality.CurrentSession;
import functionality.OutputHelper;
import functionality.User;
import pages.PageFactory;

import java.util.ArrayList;

public class Register extends  Action {
    public Register(CurrentSession session) {
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

        int index = this.getSession().userExists(this.getCredentials().getName());
        if (index != -1) {
            this.getSession().getOutput().add(OutputHelper.error(this.getSession()));
            this.getSession().setCurrentPage(PageFactory.createPage("homepageUnauthenticated"));
            return;
        }

        User user = new User();
        user.setCredentials(this.getCredentials());
        this.getSession().getDatabaseUsers().add(user);
        int indexCurrent = this.getSession().userExists(this.getCredentials().getName());
        this.getSession().setCurrentUser(this.getSession().getDatabaseUsers().get(indexCurrent));
        this.getSession().setCurrentPage(PageFactory.createPage("homepageAuthenticated"));
        this.getSession().getOutput().add(OutputHelper.loginInfo(this.getSession().getCurrentUser()));
    }
}

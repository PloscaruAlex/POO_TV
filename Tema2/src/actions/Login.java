package actions;

import functionality.CurrentSession;
import functionality.OutputHelper;
import pages.PageFactory;

import java.util.ArrayList;

/**
 * Login action class.
 */
public class Login extends Action {
    /**
     * Instantiates a new Login action.
     *
     * @param session the session
     */
    public Login(final CurrentSession session) {
        this.setSession(session);
    }

    /**
     * This is the function that performs the login action.
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

        int index = this.getSession().userExists(this.getCredentials().getName());
        if (index == -1) {
            this.getSession().getOutput().add(OutputHelper.error(this.getSession()));
            this.getSession().setCurrentPage(PageFactory.createPage("homepageUnauthenticated"));
            return;
        }

        if (!this.getSession().getDatabaseUsers().get(index)
                .getCredentials().getPassword().equals(this.getCredentials().getPassword())
        ) {
            this.getSession().getOutput().add(OutputHelper.error(this.getSession()));
            this.getSession().setCurrentPage(PageFactory.createPage("homepageUnauthenticated"));
            return;
        }

        this.getSession().setCurrentUser(this.getSession().getDatabaseUsers().get(index));
        this.getSession().setCurrentPage(PageFactory.createPage("homepageAuthenticated"));
        this.getSession().getOutput().add(
                OutputHelper.loginInfo(this.getSession().getCurrentUser())
        );
    }
}

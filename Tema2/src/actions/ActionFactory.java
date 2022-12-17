package actions;

import functionality.CurrentSession;
import io.ActionsInput;

/**
 * This is the main factory that generates every action.
 */
public final class ActionFactory {
    /**
     * The main function in the factory that creates actions.
     *
     * @param action  the action
     * @param session the session
     * @return the action
     */
    public static Action createAction(final ActionsInput action, final CurrentSession session) {
        switch (action.getType()) {
            case "on page":
                return ActionOnPageFactory.createAction(action, session);
            case "change page":
                ChangePage changePage = new ChangePage(session);
                changePage.setActionFromInput(action);
                return changePage;
            default:
                break;
        }
        throw new IllegalArgumentException("Not an action.");
    }

    private ActionFactory() {
    }
}

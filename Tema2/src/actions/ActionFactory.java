package actions;

import functionality.CurrentSession;
import io.ActionsInput;

public class ActionFactory {
    public static Action createAction(ActionsInput action, CurrentSession session) {
        switch (action.getType()) {
            case "on page":
                return ActionOnPageFactory.createAction(action, session);
            case "change page":
                ChangePage changePage = new ChangePage(session);
                changePage.setActionFromInput(action);
                return changePage;
        }
        throw new IllegalArgumentException("Not an action.");
    }
}

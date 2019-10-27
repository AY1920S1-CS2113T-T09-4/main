package entertainment.pro.logic.parsers.commands;

import entertainment.pro.storage.user.WatchlistHandler;
import entertainment.pro.ui.Controller;
import entertainment.pro.ui.MovieHandler;
import entertainment.pro.commons.enums.COMMANDKEYS;
import entertainment.pro.logic.parsers.CommandStructure;
import entertainment.pro.logic.parsers.CommandSuper;

import java.io.IOException;

public class RemoveCommand extends CommandSuper {
    /**
     * Constructor for each Command Super class.
     *
     * @param uicontroller
     */
    public RemoveCommand(Controller uicontroller) {
        super(COMMANDKEYS.remove, CommandStructure.cmdStructure.get(COMMANDKEYS.remove), uicontroller);
    }

    /**
     * executes the entertainment.pro.logic.parsers.commands based on the subroot command that is passed to it
     * @throws IOException
     */
    @Override
    public void executeCommands() throws IOException {
        switch(this.getSubRootCommand()) {
            case watchlist:
                String mov = getPayload();
                System.out.println(mov);
                if (WatchlistHandler.removeFromWatchlist(mov, (MovieHandler)(this.getUIController()))) {
                        ((MovieHandler) getUIController()).setFeedbackText("Successfully removed the movie from WatchList: " + mov);
                } else {
                        ((MovieHandler) getUIController()).setFeedbackText("Such a movie does not exist in your WatchList. Check your spelling?");
                }
                break;
            case blacklist:

                break;
            default:
                break;
        }
    }



    public static boolean isInteger(String s, int radix) {
        if (s.isEmpty()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) {
                    return false;
                } else {
                    continue;
                }
            }
            if (Character.digit(s.charAt(i) , radix) < 0) {
                return false;
            }
        }
        return true;
    }

}
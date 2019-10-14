package Commands;

import Contexts.SearchResultContext;
import EPstorage.Blacklist;
import MovieUI.Controller;
import MovieUI.MovieHandler;
import task.Deadline;
import task.Period;
import ListCommands.WatchlistHandler;

public class AddCommand extends CommandSuper {

    public AddCommand(Controller uicontroller) {
        super(COMMAND_KEYS.add, CommandStructure.cmdStructure.get(COMMAND_KEYS.add), uicontroller);
    }


    @Override
    public void executeCommands() {
        switch (this.getSubRootCommand()) {
        case watchlist:
            addToWatchList();
            break;
        case blacklist:
            addToBlackList();
            break;
        default:
            break;
        }
    }

    /**
     * Add items to the watchlist
     *
     */
    public void addToWatchList() {
        String movie = ((MovieHandler)this.getUIController()).getAPIRequester().beginAddRequest(getPayload());
        String type = this.getFlagMap().get("-d").get(0);
        switch (type) {
        case " d ":
            String endDate = this.getFlagMap().get("-e").get(0);
            Deadline deadline = new Deadline(movie, "D", endDate);
            WatchlistHandler.add(deadline);
            break;
        case " p ":
            String sDate = this.getFlagMap().get("-s").get(0);
            String eDate = this.getFlagMap().get("-e").get(0);
            Period period = new Period(movie, "P", sDate, eDate);
            WatchlistHandler.add(period);
            break;
        default:
            break;
        }
        WatchlistHandler.print_list((MovieHandler)(this.getUIController()));
    }

    /**
     * Check if payload is an integer
     *
     * @param radix the chosen radix
     * @param s string payload
     */
    public static boolean isInteger(String s, int radix) {
        if (s.isEmpty()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) {
                    return false;
                } else {
                    continue;
                }
            }
            if (Character.digit(s.charAt(i),radix) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Add items to the blacklist
     *
     */
    public void addToBlackList() {
        System.out.print("HERE!!");
        String movie = getPayload().trim();
        if(isInteger(movie,10)) {
            Blacklist.addToBlacklist(SearchResultContext.getIndex(Integer.parseInt(movie)));
        } else {
            Blacklist.addToBlacklist(movie);
        }

        Blacklist.printList();

    }


}

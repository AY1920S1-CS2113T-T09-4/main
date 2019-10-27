package entertainment.pro.logic.parsers.commands;


import entertainment.pro.ui.Controller;
import entertainment.pro.ui.MovieHandler;
import entertainment.pro.commons.enums.COMMANDKEYS;
import entertainment.pro.logic.parsers.CommandStructure;
import entertainment.pro.logic.parsers.CommandSuper;
import entertainment.pro.model.MovieInfoObject;


import java.util.ArrayList;

import java.io.IOException;

public class GetCommand extends CommandSuper {
    private int constant = 5;

    public GetCommand(Controller uicontroller) {
        super(COMMANDKEYS.get, CommandStructure.cmdStructure.get(COMMANDKEYS.get), uicontroller);
    }

    @Override
    public void executeCommands() {
        try {
            switch(this.getSubRootCommand()) {
                case recommendation:
                    ExecuteRecommendationCommand();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            ((MovieHandler) this.getUIController()).setFeedbackText("file unable to be found");
        }
    }

    /**
     * prints out a list of recommendations based on the users set preferences
     * @throws IOException: file was not able to be found
     */
    public void ExecuteRecommendationCommand() {
        String feedback = "Your recommended movies are: \n";
        MovieHandler movieHandler = ((MovieHandler) this.getUIController());
        ArrayList<Integer> p_indices = movieHandler.getUserProfile().getGenreIdPreference();
        ArrayList<MovieInfoObject>  movies = movieHandler.getAPIRequester().beginSearchGenre(Integer.toString(p_indices.get(0)), movieHandler.getUserProfile().isAdult());
        for (int i = 0; i < constant; i++) {
            feedback += i + 1 + ". " + movies.get(i).getTitle() + "\n";
        }
        movieHandler.setFeedbackText(feedback);
    }
}
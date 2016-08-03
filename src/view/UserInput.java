package view;

import model.Character;
import model.Player;

import java.util.List;

/**
 * Created by megan on 17/07/16.
 */
public interface UserInput {
    /**
     * Get the number of players
     * @return the number of players
     */
    int getNumberOfPlayers();

    /**
     * Get the player names
     * @return the list of names of the players
     */
    String getPlayers();

    /**
     * Allow for players to choose a character
     * @return character choice
     */
    String choosingCharacters();

    /**
     * Allow for players to make a suggestion
     * @return Suggestion
     */
    String [] suggest();

    /**
     * Allow for players to make an accusation
     * @return Accusation
     */
    String [] accuse();

    /**
     * Allow for players to input a move using WASD key strokes
     * @return
     */
    String moving(Player player);

}

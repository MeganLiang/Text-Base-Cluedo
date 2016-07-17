package view;

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
    List<String> getPlayerNames();

    Player chooseCharacter(String playerName, List<Player.Character> allCharacters, List<Player.Character> avaliableCharacters);



}

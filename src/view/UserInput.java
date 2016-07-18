package view;

import model.Character;

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

    //Character chooseCharacter(String playerName, List<Character.Character> allCharacters, List<Character.Character> avaliableCharacters);



}

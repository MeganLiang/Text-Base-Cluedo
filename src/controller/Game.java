package controller;

import model.*;
import view.TextBaseCluedo;
import java.util.*;
import java.util.List;

import static controller.Setup.*;

public class Game {

    private static Setup setup = new Setup();
    private static TextBaseCluedo textBaseCluedo = new TextBaseCluedo();
    private static List<Player> playersList = new ArrayList<>();
    private static Board board = new Board();

    /**
     * getter for textBase
     * @return textBase
     */
    public static TextBaseCluedo getTextBaseCluedo() {
        return textBaseCluedo;
    }
    /**
     * add to List of players
     * @param player players of game
     */
    public static void addToPlayersList(Player player) {
        playersList.add(player);
    }

    /**
     * get List of players in game
     * @return List<Player></>
     */
    public static List<Player> getPlayerList() {
        return playersList;
    }

    /**
     * returns the board for the game
     * @return Board
     */
    public static Board getBoard(){
        return board;
    }

    public static void main(String[] args) {
        initGame();
        chooseCharacters(getNumPlayers());
        dealCards();
        setup.placePlayersAtStart();
        System.out.println("Game is ready to play!!");
        for(Player player: getPlayerList()) {
            Guessing.chooseAccusation(player);
            if(!player.hasMadeAccusation()) {
                Moving.movePlayer(player);
                if(player.isInRoom(player,board)) {
                    Guessing.suggestion();
                }
            }
        }

    }

}
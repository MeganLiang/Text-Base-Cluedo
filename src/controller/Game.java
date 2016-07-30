package controller;

import model.*;
import model.Character;
import model.Squares.NullSquare;
import view.TextBaseCluedo;

import java.awt.*;
import java.util.*;
import java.util.List;

import static controller.Setup.*;

public class Game {

    private static Setup setup = new Setup();
    public static TextBaseCluedo textBaseCluedo = new TextBaseCluedo();

    private static List<Player> playersList = new ArrayList<>();
    public static Board board = new Board();



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

    public static void main(String[] args) {
        initGame();
        chooseCharacters(setup.getNumPlayers());
        setup.dealCards();
        setup.placePlayersAtStart();
        System.out.println("Game is ready to play!!");

        for(Player player: getPlayerList()) {
            Guessing.chooseAccusation(player);
            if(!player.hasMadeAccusation()) { //player has not made an accusation
                Moving.movePlayer(player);
            }
        }

    }


}
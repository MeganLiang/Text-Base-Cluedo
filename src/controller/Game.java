package controller;

import model.*;
import view.PaintBoard;
import view.TextBaseCluedo;
import java.util.*;
import java.util.List;


/**
 * Main Class to Run
 */
public class Game {
    private Setup setup = new Setup();
    private Moving moving = new Moving();
    private TextBaseCluedo textBaseCluedo = new TextBaseCluedo();
    private PaintBoard paintBoard = new PaintBoard();
    private Board board = new Board();
    private Guessing guessing = new Guessing();
    private List<Player> playersList = new ArrayList<>(); //list of players

    /**
     * getter for textBase
     * @return textBase
     */
    public TextBaseCluedo getTextBaseCluedo() {
        return textBaseCluedo;
    }
    /**
     * add to List of players
     * @param player players of game
     */
    public void addToPlayersList(Player player) {
        playersList.add(player);
    }

    /**
     * get List of players in game
     * @return List<Player></>
     */
    public List<Player> getPlayerList() {
        return playersList;
    }

    public Setup getSetup() {
        return setup;
    }

    /**
     * returns the board for the game
     * @return Board
     */
    public Board getBoard(){
        return board;
    }

    /**
     * Getter for Moving controller
     * @return
     */
    public Moving getMoving() {
        return moving;
    }

    /**
     * Getter for paintBoard class
     * @return
     */
    public PaintBoard getPaintBoard() {
        return paintBoard;
    }
    /**
     * Getter for Moving controller
     * @return
     */
    public Guessing getGuessing() {
        return guessing;
    }

    public static void main(String[] args) {
        Game cluedo = new Game();
        System.out.println("Movement Rules: When inputting movement directions, these do not have to be the exact path ");
        System.out.println("to the Square the player is moving to, just the Square itself (it's x and y location) as the path finding ");
        System.out.println("algorithm works it out. E.g. When you want to enter a room, and you have enough on your dice roll, you can take shortcuts ");
        System.out.println("instead of entering a much longer command of movements");
        System.out.println("When in a Room, the player has unlimited moves, therefore, the associated");
        System.out.println("dice roll count doesn't start until you leave the room. ");
        cluedo.setup.initGame(cluedo);
        System.out.println("Game is ready to play!!");
        while (!cluedo.guessing.isGameWon()) {
            for (Player player : cluedo.playersList) {
                System.out.println("Player's hand: ============");
                player.printHand();
                if (!player.hasMadeAccusation()) {
                    cluedo.getPaintBoard().updateArray(player,cluedo);
                    cluedo.getPaintBoard().paintBoard();
                    System.out.println();

                        cluedo.moving.movePlayer(player, cluedo);

                    if (player.isInRoom(player, cluedo.board,cluedo) && player.isJustEnteredRoom()) { //is in room and needs to suggest
                        System.out.println("Player's hand: ============");
                        player.printHand();
                        cluedo.guessing.suggestion(cluedo, player);
                        player.setJustEnteredRoom(false);
                    }
                    cluedo.guessing.chooseAccusation(player,cluedo);
                    if (cluedo.guessing.isGameWon()) {
                        System.exit(0); //game won
                    }
                }
            }
        }
    }
}

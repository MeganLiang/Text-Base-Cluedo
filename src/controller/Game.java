package controller;

import model.*;
import view.PaintBoard;
import view.TextBaseCluedo;

import java.awt.*;
import java.util.*;
import java.util.List;

import static controller.Setup.*;

public class Game {
    private Setup setup = new Setup();
    private Moving moving = new Moving();
    private TextBaseCluedo textBaseCluedo = new TextBaseCluedo();
    private PaintBoard paintBoard = new PaintBoard();
    private List<Player> playersList = new ArrayList<>();
    private Board board = new Board();
    private Guessing guessing = new Guessing();

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

    public Moving getMoving() {
        return moving;
    }

    public PaintBoard getPaintBoard() {
        return paintBoard;
    }

    public static void main(String[] args) {
        Game cluedo = new Game();
        cluedo.setup.initGame(cluedo);
        System.out.println("Game is ready to play!!");
        while (!cluedo.guessing.isGameWon()) {
            for (Player player : cluedo.playersList) {
                System.out.println("Player's hand: ============");
                player.printHand();
                if (!player.hasMadeAccusation()) {
                    cluedo.getPaintBoard().repaint(player,cluedo, null);
                    System.out.println();
                    if (player.isInRoom(player, cluedo.board, cluedo) && !cluedo.guessing.isJustEnteredRoom()) { // is still in room and needs to pick an exit
                        player.findRoom(player, cluedo.board,cluedo).printExits(player, cluedo.board, cluedo);
                        cluedo.moving.movePlayer(player,cluedo);
                    }else {
                        cluedo.moving.movePlayer(player, cluedo);
                    }

                    if (player.isInRoom(player, cluedo.board,cluedo) && cluedo.guessing.isJustEnteredRoom()) { //is in room and needs to suggest
                        System.out.println("Player's hand: ============");
                        player.printHand();
                        cluedo.guessing.suggestion(cluedo);
                    }
                    cluedo.guessing.chooseAccusation(player,cluedo);
                    if (cluedo.guessing.isGameWon()) {
                        return; //game won
                    }
                }
            }
        }
    }
}

package controller;

import model.*;
import view.TextBaseCluedo;

import java.awt.*;
import java.util.*;
import java.util.List;

import static controller.Setup.*;

public class Game {
    private  Setup setup = new Setup();
    private  Moving moving = new Moving();
    private  TextBaseCluedo textBaseCluedo = new TextBaseCluedo();
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

    public static void main(String[] args) {
        Game cluedo = new Game();
        cluedo.setup.initGame(cluedo);
        cluedo.setup.chooseCharacters(cluedo.setup.getNumPlayers(),cluedo);
        cluedo.setup.dealCards(cluedo);

        System.out.println("Game is ready to play!!");
        while (!cluedo.guessing.isGameWon()) {
            for (Player player : cluedo.playersList) {

                // move
                // if in room, make suggestion
                // if want to make accusation, do so

                if (!player.hasMadeAccusation()) {
                    cluedo.guessing.chooseAccusation(player, cluedo);
                    if (cluedo.guessing.isGameWon()) {
                        return; //game won
                    }
                    if (!player.hasMadeAccusation()) {
//                        if (player.isInRoom(player, cluedo.board, cluedo) && cluedo.guessing.isJustEnteredRoom()) { //is in room and needs to suggest
//                            cluedo.guessing.suggestion(cluedo);
//                        } else if (player.isInRoom(player, cluedo.board, cluedo)) { // is still in room and needs to pick an exit
//                            player.findRoom(player, cluedo.board, cluedo).printExits(player, cluedo.board, cluedo);
//                            cluedo.moving.movePlayer(player,cluedo);
//                        } else { //not in room
//                            cluedo.moving.movePlayer(player,cluedo);
//                        }
                        if(player.isInRoom(player, cluedo.board, cluedo)) {
                            if(cluedo.guessing.isJustEnteredRoom()) {
                                cluedo.guessing.suggestion(cluedo);
                            }else {
                                cluedo.moving.movePlayer(player,cluedo);
                            }
                        }else {//not in room
                            cluedo.moving.movePlayer(player,cluedo);
                        }
                    }
                }
            }
        }
    }
//        Game g = new Game();
//        Player megan = new Player("Megan");
//        megan.setPositionPoint(new Point(9, 1));
//        g.moving.movePlayer(megan, g);

}
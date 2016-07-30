package controller;

import model.*;
import model.Character;
import model.Squares.DoorSquare;
import model.Squares.NullSquare;
import model.Squares.RoomSquare;
import model.Squares.Square;
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

        Player megan = new Player("Megan");
        Player tristan = new Player("Tristan");
        megan.setPositionPoint(new Point(12,22)); //Hall
        tristan.setPositionPoint(new Point(12,22));
        playersList.add(megan);
        playersList.add(tristan);

        List<Card> cards = new ArrayList<>();
        cards.add(new Weapon(Weapon.Weapons.Spanner));
        cards.add(new Room(Room.Rooms.Study));
        Hand hand = new Hand(cards);
        tristan.setHand(hand);
        suggestion();


    }
    /**
     * Get a list of squares the player can move too
     * @return List<int><int>
     */
    public boolean move(Point point, Player player, int roll){
        //Create List of Availabe Squares
        Set<Point> availableSquares;


        //If player is in a room square
        if(board.returnSquare(player.getPositionPoint())instanceof RoomSquare){
            System.out.println("In Room");
            availableSquares = new HashSet<>();
            Room current = ((RoomSquare) board.returnSquare(player.getPositionPoint())).getRoom();
            System.out.println(current.toString());
            Set<Square> doorSquares = new HashSet<Square>();
            for(int x = 0; x < 25; x++){
                for(int y = 0; y < 25; y++){
                    if(board.board[x][y] instanceof DoorSquare){
                        DoorSquare rs = (DoorSquare) board.board[x][y];
                        System.out.println(rs.getRoom().getName());
                        if(rs.getRoom().getName().equals(current.getName())){
                            availableSquares.addAll(avaialableMoves(new Point(x,y),roll-1));
                        }
                    }
                }
            }
            System.out.println(availableSquares.size());
            if (availableSquares.contains(point)) {
                //If movement is a room sqaure
                if (board.returnSquare(point) instanceof RoomSquare) {
                    Room room = ((RoomSquare) board.returnSquare(point)).getRoom();
                    for (Point rsPoint : availableSquares) {
                        Square rs = board.board[(int) rsPoint.getX()][(int) rsPoint.getY()];
                        if (rs instanceof RoomSquare) {
                            if (((RoomSquare) rs).getRoom().equals(room)) {
                                player.setPositionPoint(point);
                                return true;
                            }
                        }
                    }
                    return false;
                }
                //Move players point to that position
                player.setPositionPoint(point);
                return true;
            }
            //square cannot be moved too
            return false;


        }
        //Player is not in room square
        else {
            availableSquares = avaialableMoves(player.getPositionPoint(), roll);
            //Remove Squares that Players are in
            for (Player p : playersList) {
                Point checkPoint = p.getPositionPoint();
                if (availableSquares.contains(checkPoint)) {
                    availableSquares.remove(checkPoint);
                }
            }
            //CHeck if inteded movement is possible
            if (availableSquares.contains(point)) {
                System.out.println("Hi");
                //If movement is a room sqaure
                if (board.returnSquare(point) instanceof RoomSquare) {
                    System.out.println("Hi");
                    Room room = ((RoomSquare) board.returnSquare(point)).getRoom();
                    for (Point rsPoint : availableSquares) {
                        Square rs = board.board[(int) rsPoint.getX()][(int) rsPoint.getY()];
                        if (rs instanceof RoomSquare) {
                            if (((RoomSquare) rs).getRoom().equals(room)) {
                                player.setPositionPoint(point);
                                return true;
                            }
                        }
                    }
                    return false;
                }
                //Move players point to that position
                player.setPositionPoint(point);
                return true;
            }
            //square cannot be moved too
            return false;
        }

    }

    public Board getBoard(){
        return this.board;
    }

    Set<Point> avaialableMoves(Point p, int roll){
        Point playerPos = p;
        Set<Point> visitable;
        visitable = getNeighbours(playerPos,roll,new HashSet<Point>());
        return visitable;
    }

    Set<Point> getNeighbours(Point c,int remainingMoves, Set <Point> visited) {
        if (remainingMoves != 0) {
            if(c.getX() < 0 || c.getY() > 24){
                return null;
            }
            if(c.x + 1 < 25) {
                if (!(board.board[c.x + 1][c.y] instanceof NullSquare) ) {
                    Point toAdd = new Point(c.x + 1, c.y);
                    visited.add(toAdd);
                    getNeighbours(toAdd, remainingMoves - 1, visited);
                }
            }
            if(c.x - 1 >= 0) {
                if (!(board.board[c.x - 1][c.y] instanceof NullSquare)) {
                    Point toAdd = new Point(c.x - 1, c.y);
                    visited.add(toAdd);
                    getNeighbours(toAdd, remainingMoves - 1, visited);
                }
            }
            if(c.y + 1 < 25){
                if (!(board.board[c.x][c.y + 1] instanceof NullSquare)) {
                    Point toAdd = new Point(c.x, c.y + 1);
                    visited.add(toAdd);
                    getNeighbours(toAdd, remainingMoves - 1, visited);
                }
            }
            if(c.y - 1 >= 0){
                if (!(board.board[c.x][c.y - 1] instanceof NullSquare)) {
                    Point toAdd = new Point(c.x, c.y - 1);
                    visited.add(toAdd);
                    getNeighbours(toAdd, remainingMoves - 1, visited);
                }
            }
        }
        return visited;


    }


}
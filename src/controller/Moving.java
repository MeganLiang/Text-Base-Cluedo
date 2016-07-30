package controller;

import model.Board;
import model.Move;
import model.Player;
import model.Room;
import model.Squares.DoorSquare;
import model.Squares.NullSquare;
import model.Squares.RoomSquare;
import model.Squares.Square;
import java.awt.*;
import java.util.*;
import java.util.List;

import static model.Board.*;


public class Moving {
    /**
     * Get a list of squares the player can move too
     * @return List<int><int>
     */
//    public static boolean moveCheck(Point point, Player player, int roll){
//        //Get List of Available Squares
//        Set<Point> availableSquares = availableMoves(player, roll);
//        System.out.println("available squares");
//        for(Point p: availableSquares) {
//            System.out.println(p.toString());
//        }
//
//        //Remove Squares that Players are in
//        for(Player p: Game.getPlayerList()){
//            Point checkPoint = p.getPositionPoint();
//            if(availableSquares.contains(checkPoint)){
//                availableSquares.remove(checkPoint);
//            }
//        }
//
//        //CHeck if intended movement is possible
//        if(availableSquares.contains(point)){
//            //Move players point to that position
//            player.setPositionPoint(point);
//            return true;
//        }
//        //square cannot be moved too
//        return false;
//
//    }
    /**
     * Get a list of squares the player can move to
     * @return List<int><int>
     */
    public static boolean moveCheck(Point point, Player player, int roll){
        //Create List of Available Squares
        Set<Point> availableSquares;


        //If player is in a room square
        if(Game.getBoard().returnSquare(player.getPositionPoint())instanceof RoomSquare){
            System.out.println("In Room");
            availableSquares = new HashSet<>();
            Room current = ((RoomSquare) Game.getBoard().returnSquare(player.getPositionPoint())).getRoom();
            System.out.println(current.toString());
            Set<Square> doorSquares = new HashSet<>();
            for(int x = 0; x < 25; x++) {
                for (int y = 0; y < 25; y++) {
                    if (board[x][y] instanceof DoorSquare) {
                        DoorSquare rs = (DoorSquare) board[x][y];
                        System.out.println(rs.getRoom().getName());
                        if (rs.getRoom().getName().equals(current.getName())) {
                            availableSquares.addAll(availableMoves(new Point(x, y), roll - 1));
                        }
                    }
                }
            }
            for(Player p: Game.getPlayerList()) {
                Guessing.chooseAccusation(player);
                if (!player.hasMadeAccusation()) { //player has not made an accusation
                    Moving.movePlayer(p);
                }
            }
            System.out.println(availableSquares.size());
            if (availableSquares.contains(point)) {
                //If movement is a room sqaure
                if (Game.getBoard().returnSquare(point) instanceof RoomSquare) {
                    Room room = ((RoomSquare) Game.getBoard().returnSquare(point)).getRoom();
                    for (Point rsPoint : availableSquares) {
                        Square rs = board[(int) rsPoint.getX()][(int) rsPoint.getY()];
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
            availableSquares = availableMoves(player.getPositionPoint(), roll);
            //Remove Squares that Players are in
            for (Player p : Game.getPlayerList()) {
                Point checkPoint = p.getPositionPoint();
                if (availableSquares.contains(checkPoint)) {
                    availableSquares.remove(checkPoint);
                }
            }
            //CHeck if intended movement is possible
            if (availableSquares.contains(point)) {
                System.out.println("Hi");
                //If movement is a room sqaure
                if (Game.getBoard().returnSquare(point) instanceof RoomSquare) {
                    System.out.println("Hi");
                    Room room = ((RoomSquare) Game.getBoard().returnSquare(point)).getRoom();
                    for (Point rsPoint : availableSquares) {
                        Square rs = board[(int) rsPoint.getX()][(int) rsPoint.getY()];
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
    private static Set<Point> availableMoves(Point p, int roll){
        Point playerPos = p;
        Set<Point> visitable;
        visitable = getNeighbours(playerPos,roll,new HashSet<Point>());
        return visitable;
    }

    private static Set<Point> getNeighbours(Point c, int remainingMoves, Set<Point> visited) {
        if (remainingMoves != 0) {
            if(c.getX() < 0 || c.getY() > 24){
                return null;
            }
            if(c.x + 1 < 25) {
                if (!(board[c.x + 1][c.y] instanceof NullSquare) ) {
                    Point toAdd = new Point(c.x + 1, c.y);
                    visited.add(toAdd);
                    getNeighbours(toAdd, remainingMoves - 1, visited);
                }
            }
            if(c.x - 1 >= 0) {
                if (!(board[c.x - 1][c.y] instanceof NullSquare)) {
                    Point toAdd = new Point(c.x - 1, c.y);
                    visited.add(toAdd);
                    getNeighbours(toAdd, remainingMoves - 1, visited);
                }
            }
            if(c.y + 1 < 25){
                if (!(board[c.x][c.y + 1] instanceof NullSquare)) {
                    Point toAdd = new Point(c.x, c.y + 1);
                    visited.add(toAdd);
                    getNeighbours(toAdd, remainingMoves - 1, visited);
                }
            }
            if(c.y - 1 >= 0){
                if (!(board[c.x][c.y - 1] instanceof NullSquare)) {
                    Point toAdd = new Point(c.x, c.y - 1);
                    visited.add(toAdd);
                    getNeighbours(toAdd, remainingMoves - 1, visited);
                }
            }
        }
        return visited;

    }


    public static void movePlayer(Player player) {
            Random random = new Random();
            int diceRoll = random.nextInt(6 - 1 + 1) + 1;
            //int diceRoll = 6;
            System.out.println("You rolled a " + diceRoll);
            System.out.println("Player location: x="+ player.getPositionPoint().getX() + " y=" + player.getPositionPoint().getY());
            String commands = "";
            List<Move.Moves> moves = new ArrayList<>(Arrays.asList(Move.Moves.values()));


                boolean validMove = false;
                Point newPosition;
                while(!validMove) {
                    commands = Game.textBaseCluedo.moving();
                    String[] commandArray = commands.trim().split("\\s+");

                    for(int i=0; i<commandArray.length; i++) { //error checking
                        while(!moves.contains(Move.Moves.fromString(commandArray[i]))) {
                            commands = Game.textBaseCluedo.invalidArrayInput();
                            commandArray = commands.trim().split("\\s+");
                        }
                    }
//                    while(commandArray.length != diceRoll) { //error checking
//                        commands = Game.textBaseCluedo.invalidArrayInput();
//                        commandArray = commands.trim().split("\\s+");
//                    }
                    //convert commandArray to point coordinates
                    newPosition = convertArrayToPoint(commandArray, player);
                    validMove =  moveCheck(newPosition, player, diceRoll); //check if valid move
                    System.out.println("IS this a valid move " + validMove);
                }


    }

    private static Point convertArrayToPoint(String[] commandArray, Player player) {
        Point playerPosition = player.getPositionPoint();
        int x = (int)playerPosition.getX();
        int y = (int)playerPosition.getY();
        for (String aCommandArray : commandArray) {
            if (aCommandArray.equals(Move.Moves.W.getText()) || aCommandArray.equals(Move.Moves.w.getText())) {
                y--;
            } else if (aCommandArray.equals(Move.Moves.A.getText()) || aCommandArray.equals(Move.Moves.a.getText())) {
                x--;
            } else if (aCommandArray.equals(Move.Moves.S.getText()) || aCommandArray.equals(Move.Moves.s.getText())) {
                y++;
            } else if (aCommandArray.equals(Move.Moves.D.getText()) || aCommandArray.equals(Move.Moves.d.getText())) {
                x++;
            }
        }
        Point point = new Point(x,y);
        System.out.println(point.toString());
        return point;

    }

}

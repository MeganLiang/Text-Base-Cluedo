package controller;

import model.Move;
import model.Player;
import model.Room;
import model.Squares.*;

import java.awt.*;
import java.util.*;
import java.util.List;


public class Moving {
    /**
     * Get a list of squares the player can move to
     * @return List<int><int>
     */
    public boolean moveCheck(Point point, Player player, int roll, Game cluedo){
        if(point.getX() < 0 || point.getX() > 23 || point.getY() < 0 || point.getY() > 24){
            return false;
        }

        //Create List of Available Squares
        Set<Point> availableSquares;


        //If player is in a room square
        if(cluedo.getBoard().returnSquare(player.getPositionPoint())instanceof RoomSquare){
            System.out.println("In Room");
            availableSquares = new HashSet<>();
            Room current = ((RoomSquare) cluedo.getBoard().returnSquare(player.getPositionPoint())).getRoom();
            System.out.println(current.toString());
            Set<Square> doorSquares = new HashSet<>();
            for(int x = 0; x < cluedo.getBoard().getWIDTH(); x++) {
                for (int y = 0; y < cluedo.getBoard().getHEIGHT(); y++) {
                    if (cluedo.getBoard().getBoard()[x][y] instanceof DoorSquare) {
                        DoorSquare rs = (DoorSquare) cluedo.getBoard().getBoard()[x][y];
                        System.out.println(rs.getRoom().getName());
                        if (rs.getRoom().getName().equals(current.getName())) {
                            availableSquares.addAll(availableMoves(new Point(x, y), roll - 1, cluedo));
                        }
                    }
                }
            }

            System.out.println(availableSquares.size());
            if (availableSquares.contains(point)) {
                //If movement is a room sqaure
                if (cluedo.getBoard().returnSquare(point) instanceof RoomSquare) {
                    Room room = ((RoomSquare) cluedo.getBoard().returnSquare(point)).getRoom();
                    for (Point rsPoint : availableSquares) {
                        Square rs = cluedo.getBoard().getBoard()[(int) rsPoint.getX()][(int) rsPoint.getY()];
                        if (rs instanceof RoomSquare) {
                            if (((RoomSquare) rs).getRoom().equals(room)) {
                                player.setPreviousPoint(player.getPositionPoint());
                                player.setPositionPoint(point);
                                cluedo.getPaintBoard().repaint(player,cluedo,null);
                                System.out.println();
                                return true;
                            }
                        }
                    }
                    return false;
                }
                //Move players point to that position
                player.setPreviousPoint(player.getPositionPoint());
                player.setPositionPoint(point);
                cluedo.getPaintBoard().repaint(player,cluedo,null);
                System.out.println();
                return true;
            }
            //square cannot be moved too
            return false;

        }
        //Player is not in room square
        else {
            availableSquares = availableMoves(player.getPositionPoint(), roll, cluedo);
            //Remove Squares that Players are in
            for (Player p : cluedo.getPlayerList()) {
                Point checkPoint = p.getPositionPoint();
                if (availableSquares.contains(checkPoint)) {
                    availableSquares.remove(checkPoint);
                }
            }
            //Check if intended movement is possible
            if (availableSquares.contains(point)) {
                //If movement is a room square
                if (cluedo.getBoard().returnSquare(point) instanceof RoomSquare ) {
                    Room room = ((RoomSquare) cluedo.getBoard().returnSquare(point)).getRoom();
                    for (Point rsPoint : availableSquares) {
                        Square rs = cluedo.getBoard().returnSquare(rsPoint);

                        if (rs instanceof DoorSquare) {
                            if (((DoorSquare) rs).getRoom().getName().equals(room.getName())) {
                                player.setPreviousPoint(player.getPositionPoint());
                                player.setPositionPoint(point);
                                System.out.println("Your are passing through a door");
                                cluedo.getPaintBoard().repaint(player,cluedo,null);
                                System.out.println();
                                return true;
                            }
                        }
                    }
                    return false;
                }
                //Move players point to that position
                player.setPreviousPoint(player.getPositionPoint());
                player.setPositionPoint(point);
                cluedo.getPaintBoard().repaint(player,cluedo,null);
                System.out.println();
                return true;
            }
            //square cannot be moved too
            return false;
        }

    }
    private Set<Point> availableMoves(Point p, int roll, Game cluedo){
        Set<Point> playerLocations = new HashSet<Point>();
        for (Player play : cluedo.getPlayerList()) {
            Point checkPoint = play.getPositionPoint();
            playerLocations.add(checkPoint);
        }
        Point playerPos = p;
        Set<Point> visitable;
        visitable = getNeighbours(playerPos,roll,new HashSet<Point>(), playerLocations, cluedo);
        return visitable;
    }

    private Set<Point> getNeighbours(Point c, int remainingMoves, Set<Point> visited, Set<Point> playerLocations, Game cluedo) {
        if (remainingMoves != 0) {
            if(c.getX() < 0 || c.getX() > cluedo.getBoard().getWIDTH()-1 || c.getY() < 0 || c.getY() > cluedo.getBoard().getHEIGHT()){
                return null;
            }
            if(c.x + 1 < cluedo.getBoard().getWIDTH()) {
                if (!(cluedo.getBoard().getBoard()[c.x + 1][c.y] instanceof NullSquare) || !(cluedo.getBoard().getBoard()[c.x + 1][c.y] instanceof StairwaySquare) || playerLocations.contains(new Point(c.x+1,c.y))) {
                    Point toAdd = new Point(c.x + 1, c.y);
                    visited.add(toAdd);
                    getNeighbours(toAdd, remainingMoves - 1, visited, playerLocations, cluedo);
                }
            }
            if(c.x - 1 >= 0) {
                if (!(cluedo.getBoard().getBoard()[c.x - 1][c.y] instanceof NullSquare)|| !(cluedo.getBoard().getBoard()[c.x - 1][c.y] instanceof StairwaySquare)|| playerLocations.contains(new Point(c.x-1,c.y))) {
                    Point toAdd = new Point(c.x - 1, c.y);
                    visited.add(toAdd);
                    getNeighbours(toAdd, remainingMoves - 1, visited, playerLocations, cluedo);
                }
            }
            if(c.y + 1 < cluedo.getBoard().getHEIGHT()){
                if (!(cluedo.getBoard().getBoard()[c.x][c.y + 1] instanceof NullSquare)|| !(cluedo.getBoard().getBoard()[c.x][c.y + 1] instanceof StairwaySquare)|| playerLocations.contains(new Point(c.x,c.y + 1))) {
                    Point toAdd = new Point(c.x, c.y + 1);
                    visited.add(toAdd);
                    getNeighbours(toAdd, remainingMoves - 1, visited, playerLocations, cluedo);
                }
            }
            if(c.y - 1 >= 0){
                if (!(cluedo.getBoard().getBoard()[c.x][c.y - 1] instanceof NullSquare)|| !(cluedo.getBoard().getBoard()[c.x][c.y - 1] instanceof StairwaySquare)|| playerLocations.contains(new Point(c.x,c.y - 1))) {
                    Point toAdd = new Point(c.x, c.y - 1);
                    visited.add(toAdd);
                    getNeighbours(toAdd, remainingMoves - 1, visited, playerLocations, cluedo);
                }
            }
        }
        return visited;

    }


    public void movePlayer(Player player, Game cluedo) {
        System.out.println("=============================");
        System.out.println(player.getName() + "'s turn");
        Random random = new Random();
        int diceRoll = random.nextInt(12 - 1) + 2;
        //int diceRoll = 6;
        System.out.println("You rolled a " + diceRoll);
        System.out.println("Your location: x="+ player.getPositionPoint().getX() + " y=" + player.getPositionPoint().getY());
        String commands = "";
        List<Move.Moves> moves = new ArrayList<>(Arrays.asList(Move.Moves.values()));
        boolean validMove = false;
        Point newPosition;
        while(!validMove) {
            commands = cluedo.getTextBaseCluedo().moving(player);
            String[] commandArray = commands.trim().split("\\s+");
             for(int i=0; i<commandArray.length; i++) { //error checking
                 while(!moves.contains(Move.Moves.fromString(commandArray[i]))) {
                     commands = cluedo.getTextBaseCluedo().invalidArrayInput();
                     commandArray = commands.trim().split("\\s+");
                 }
             }
//                    while(commandArray.length != diceRoll) { //error checking
//                        commands = Game.textBaseCluedo.invalidArrayInput();
//                        commandArray = commands.trim().split("\\s+");
//                    }
                    //convert commandArray to point coordinates
            newPosition = convertArrayToPoint(commandArray, player);
            validMove =  moveCheck(newPosition, player, diceRoll, cluedo); //check if valid move
            System.out.println("Your move is " + validMove);
        }
    }

    private Point convertArrayToPoint(String[] commandArray, Player player) {
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
    public boolean playerAtPoint(Point p, Game cluedo){
        for (Player player : cluedo.getPlayerList()) {
            Point checkPoint = player.getPositionPoint();
            if (checkPoint.equals(p)) {
                return true;
            }
        }
        return false;
    }

}

package controller;

import model.Move;
import model.Player;
import model.Squares.NullSquare;
import model.Suggestion;

import java.awt.*;
import java.util.*;
import java.util.List;


public class Moving {
    /**
     * Get a list of squares the player can move too
     * @return List<int><int>
     */
    public static boolean moveCheck(Point point, Player player, int roll){
        //Get List of Available Squares
        Set<Point> availableSquares = avaialableMoves(player, roll);
        System.out.println("available squares");
        for(Point p: availableSquares) {
            System.out.println(p.toString());
        }

        //Remove Squares that Players are in
        for(Player p: Game.getPlayerList()){
            Point checkPoint = p.getPositionPoint();
            if(availableSquares.contains(checkPoint)){
                availableSquares.remove(checkPoint);
            }
        }

        //CHeck if intended movement is possible
        if(availableSquares.contains(point)){
            //Move players point to that position
            player.setPositionPoint(point);
            return true;
        }
        //square cannot be moved too
        return false;

    }

    static Set<Point> avaialableMoves(Player p, int roll){
        Point playerPos = p.getPositionPoint();
        Set<Point> visitable;
        visitable = getNeighbours(playerPos,roll,new HashSet<Point>());
        return visitable;
    }

    static Set<Point> getNeighbours(Point c,int remainingMoves, Set <Point> visited) {
        if (remainingMoves != 0) {
            if(c.x + 1 < 25) {
                if (!(Game.board.board[c.x + 1][c.y] instanceof NullSquare) ) {
                    Point toAdd = new Point(c.x + 1, c.y);
                    visited.add(toAdd);
                    getNeighbours(toAdd, remainingMoves - 1, visited);
                }
            }
            if(c.x - 1 >= 0) {
                if (!(Game.board.board[c.x - 1][c.y] instanceof NullSquare)) {
                    Point toAdd = new Point(c.x - 1, c.y);
                    visited.add(toAdd);
                    getNeighbours(toAdd, remainingMoves - 1, visited);
                }
            }
            if(c.y + 1 < 25){
                if (!(Game.board.board[c.x][c.y + 1] instanceof NullSquare)) {
                    Point toAdd = new Point(c.x, c.y + 1);
                    visited.add(toAdd);
                    getNeighbours(toAdd, remainingMoves - 1, visited);
                }
            }
            if(c.y - 1 >= 0){
                if (!(Game.board.board[c.x][c.y - 1] instanceof NullSquare)) {
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

    public static Point convertArrayToPoint(String[] commandArray, Player player) {
        Point playerPosition = player.getPositionPoint();
        int x = (int)playerPosition.getX();
        int y = (int)playerPosition.getY();
        for(int i=0; i< commandArray.length; i++) {
            if(commandArray[i].equals(Move.Moves.W.getText()) || commandArray[i].equals(Move.Moves.w.getText())) {
                y--;
            } else if (commandArray[i].equals(Move.Moves.A.getText()) || commandArray[i].equals(Move.Moves.a.getText())) {
                x--;
            } else if (commandArray[i].equals(Move.Moves.S.getText()) || commandArray[i].equals(Move.Moves.s.getText())) {
                y++;
            } else if (commandArray[i].equals(Move.Moves.D.getText()) || commandArray[i].equals(Move.Moves.d.getText())) {
                x++;
            }
        }
        Point point = new Point(x,y);
        System.out.println(point.toString());
        return point;

    }

}

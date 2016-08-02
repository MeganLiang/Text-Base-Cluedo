package controller;

import model.*;
import model.Character;
import model.Squares.*;
import view.TextBaseCluedo;

import java.awt.*;
import java.util.*;
import java.util.List;

import static controller.Setup.*;

public class Game {

    private static Setup setup = new Setup();
    public static TextBaseCluedo textBaseCluedo = new TextBaseCluedo();

    private static List<Player> playersList = new ArrayList<>();
    private static Board board = new Board();

    /**
     * player can make accusations to win the game
     * @return Accusation
     */
    public static Accusation accusation() {
        String s = textBaseCluedo.accuse(); //weapon room character
        String[] splitS = s.trim().split("\\s+");

        while(!setup.getAvailableWeapons().contains(Weapon.Weapons.fromString(splitS[0]))
                || !getAvailableRooms().contains(Room.Rooms.fromString(splitS[1]))
                || !getAvailableCharacters().contains(Character.Characters.fromString(splitS[2]))) { //invalid input, can be duplicate character or not a token
            System.out.println("Unexpected input, try again");
            s = textBaseCluedo.accuse();
            splitS = s.trim().split("\\s+");
        }
        Weapon w = new Weapon(Weapon.Weapons.valueOf(splitS[0]));
        Room r = new Room(Room.Rooms.valueOf(splitS[1]));
        Character c = new Character(Character.Characters.valueOf(splitS[2]));
        return new Accusation(w,r,c);

    }

    /**
     * players can make suggestion to gather intelligence for their accusation
     * @return Suggestion
     */
    public static void suggestion() {
        for(Player player: playersList) {
            if (player.isInRoom(player,board)) {
                System.out.println(player.getName() + ", you are in the " + player.whatRoom(player,board).getName());
                String s = textBaseCluedo.suggest(); //weapon room character
                String[] splitS = s.trim().split("\\s+");

                while (splitS.length != 3
                        || !Setup.getAvailableWeapons().contains(Weapon.Weapons.fromString(splitS[0]))
                        || !getAvailableRooms().contains(Room.Rooms.fromString(splitS[1]))
                        || !getAvailableCharacters().contains(Character.Characters.fromString(splitS[2]))
                        || !player.whatRoom(player,board).getName().equals(new Room(Room.Rooms.valueOf(splitS[1])).getName())) { //invalid input, can be duplicate character or not a token
                    System.out.println("Unexpected input, try again. You can only make suggestions about the room");
                    s = textBaseCluedo.suggest();
                    splitS = s.trim().split("\\s+");
                }

                Weapon w = new Weapon(Weapon.Weapons.valueOf(splitS[0]));
                Room r = new Room(Room.Rooms.valueOf(splitS[1]));
                Character c = new Character(Character.Characters.valueOf(splitS[2]));
                Suggestion suggestion = new Suggestion(w, r, c);
                //return new Suggestion(w, r, c);
                proveSuggestions(suggestion,player);
            }
        }
        //return null;
    }

    private static void proveSuggestions(Suggestion suggestion, Player excludePlayer) {
        for(Player player: playersList) {
            if(player != excludePlayer) {
                System.out.print(player.getName());
                System.out.println("============================");
                for (Card c : player.getHand().getCards()) {
                    if (c instanceof Weapon) {
                        Weapon weapon = (Weapon) c;
                        if (weapon.getName().equals(suggestion.getWeapon().getName())) {
                            System.out.println(weapon.getName());
                        }
                    } else if (c instanceof Room) {
                        Room room = (Room) c;
                        if (room.getName().equals(suggestion.getRoom().getName())) {
                            System.out.println(room.getName());
                        }
                    } else if (c instanceof Character) {
                        Character character = (Character) c;
                        if (character.getName().equals(suggestion.getCharacter().getName())) {
                            System.out.println(character.getName());
                        }
                    }
                }
            }
        }
    }

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
//        initGame();
//        chooseCharacters(setup.getNumPlayers());
//        setup.dealCards();
//        setup.placePlayersAtStart();

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
     * @return List<point>
     */
    public boolean move(Point point, Player player, int roll){
        if(point.getX() < 0 || point.getX() > 23 || point.getY() < 0 || point.getY() > 24){
            return false;
        }

        //Create List of Availabe Squares
        Set<Point> availableSquares;
        //If moving to stairway square
        if(board.returnSquare(point)instanceof StairwaySquare) {
            System.out.println("In stairway");
            //make sure player is in a room
            if(!(board.returnSquare(player.getPositionPoint())instanceof RoomSquare)){
                System.out.println("In room check failed");
                return false;
            }
            StairwaySquare st = (StairwaySquare) board.returnSquare(point);
            //if in same room as chosen staircase
            String playerLRoom = ((RoomSquare) board.returnSquare(player.getPositionPoint())).getRoom().getName();
            String stairwayRoom = st.inRoom.getName();
            //System.out.println(((RoomSquare) board.returnSquare(player.getPositionPoint())).getRoom().getName());
            //System.out.println(st.inRoom.getName());

            if(playerLRoom.equals(stairwayRoom)){
                System.out.println("Room names equal");
                Room goingTo = st.outRoom;
                System.out.println("Room going to: " + goingTo.getName());
                for(int x = 0; x < 24; x++) {
                    for (int y = 0; y < 25; y++) {
                        if (board.board[x][y] instanceof RoomSquare) {
                            RoomSquare rs = (RoomSquare) board.board[x][y];
                            //System.out.println("Looking at room: " + rs.getRoom().getName());
                            if(rs.getRoom().getName().equals(goingTo.getName())){
                                System.out.println("Names equal");
                                if(!(playerAtPoint(new Point(x,y)))){
                                    System.out.println("No player here");
                                    player.setPositionPoint(new Point(x,y));
                                    return true;
                                }
                            }
                        }
                    }
                }
                return false;
            }
            //not in same room as staircase
            else{
                return false;
            }


        }

        //If player is in a room square
        else if(board.returnSquare(player.getPositionPoint())instanceof RoomSquare){
            System.out.println("In Room");
            availableSquares = new HashSet<>();
            Room current = ((RoomSquare) board.returnSquare(player.getPositionPoint())).getRoom();
            System.out.println(current.toString());
            Set<Square> doorSquares = new HashSet<Square>();
            for(int x = 0; x < 24; x++){
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
            if(c.getX() < 0 || c.getY() > 24 || c.getX() > 23 || c.getY() < 0){
                return null;
            }
            if(c.x + 1 < 24) {
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

    public boolean playerAtPoint(Point p){
        for (Player player : playersList) {
            Point checkPoint = player.getPositionPoint();
            if (checkPoint.equals(p)) {
                return true;
            }
        }
        return false;
    }


}
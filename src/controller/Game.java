package controller;

import model.*;
import model.Character;
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
            if (player.isInRoom(player)) {
                System.out.println(player.getName() + ", you are in the " + player.whatRoom(player).getName());
                String s = textBaseCluedo.suggest(); //weapon room character
                String[] splitS = s.trim().split("\\s+");

                while (splitS.length != 3
                        || !Setup.getAvailableWeapons().contains(Weapon.Weapons.fromString(splitS[0]))
                        || !getAvailableRooms().contains(Room.Rooms.fromString(splitS[1]))
                        || !getAvailableCharacters().contains(Character.Characters.fromString(splitS[2]))
                        || !player.whatRoom(player).getName().equals(new Room(Room.Rooms.valueOf(splitS[1])).getName())) { //invalid input, can be duplicate character or not a token
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



        }

    }
    /**
     * Get a list of squares the player can move too
     * @return List<int><int>
     */
    public boolean move(Point point, Player player, int roll){
        Set<Point> avaiableSquares = avaialableMoves(player, roll);

        if(avaiableSquares.contains(point)){
            board.board[player.getPoint().x][player.getPoint().y].setNull();
            player.setPosition(point);
            board.board[point.x][point.y].setCurrent(player);
            return true;
        }
        //square cannot be moved too
        return false;

    }

    Set<Point> avaialableMoves(Player p, int roll){
        Point playerPos = player.getPoint();
        Set<Point> visitable;
        visitable = getNeighbours(playerPos,roll,new HashSet<Point>());
        return visitable;
    }

    Set<Point> getNeighbours(Point c,int remainingMoves, Set <Point> visited) {
        if (remainingMoves != 0) {
            if(c.x + 1 < 25) {
                if (board[c.x + 1][c.y] != null && board[c.x + 1][c.y].isFree() == true) {
                    Point toAdd = new Point(c.x + 1, c.y);
                    visited.add(toAdd);
                    getNeighbours(toAdd, remainingMoves - 1, visited);
                }
            }
            if(c.x - 1 >= 0) {
                if (board[c.x - 1][c.y] != null  && board[c.x - 1][c.y].isFree() == true) {
                    Point toAdd = new Point(c.x - 1, c.y);
                    visited.add(toAdd);
                    getNeighbours(toAdd, remainingMoves - 1, visited);
                }
            }
            if(c.y + 1 < 24){
                if (board[c.x][c.y + 1] != null && board[c.x][c.y + 1].isFree() == true) {
                    Point toAdd = new Point(c.x, c.y + 1);
                    visited.add(toAdd);
                    getNeighbours(toAdd, remainingMoves - 1, visited);
                }
            }
            if(c.y - 1 <= 0){
                if (board[c.x][c.y - 1] != null && board[c.x][c.y - 1].isFree() == true) {
                    Point toAdd = new Point(c.x, c.y - 1);
                    visited.add(toAdd);
                    getNeighbours(toAdd, remainingMoves - 1, visited);
                }
            }
        }
        return visited;


    }


}
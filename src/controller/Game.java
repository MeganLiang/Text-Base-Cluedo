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
            System.out.println(player.getName());
            if (player.isInRoom(player)) {
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
                proveSuggestions(suggestion);
            }
        }
        //return null;
    }

    private static void proveSuggestions(Suggestion suggestion) {
        for(Player player: playersList) {

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
        //Player tristan = new Player("Tristan");
        megan.setPositionPoint(new Point(12,22)); //Hall
        playersList.add(megan);
        //playersList.add(tristan);
        suggestion();


    }


}
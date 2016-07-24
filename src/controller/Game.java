package controller;

import model.*;
import model.Character;
import view.TextBaseCluedo;

import java.util.*;

import static controller.Setup.chooseCharacters;
import static controller.Setup.getAvailableRooms;
import static controller.Setup.initGame;

public class Game {

    private static Setup setup = new Setup();
    public static TextBaseCluedo textBaseCluedo = new TextBaseCluedo();

    private static List<Player> playersList = new ArrayList<>();

    /**
     * player can make accusations to win the game
     * @return Accusation
     */
    public static Accusation accusation() {
        String s = textBaseCluedo.accuse(); //weapon room character
        String[] splitS = s.trim().split("\\s+");

        while(!setup.getAvailableWeapons().contains(Weapon.Weapons.fromString(splitS[0]))
                || !getAvailableRooms().contains(Room.Rooms.fromString(splitS[1]))
                || !setup.getAvailableCharacters().contains(Character.Characters.fromString(splitS[2]))) { //invalid input, can be duplicate character or not a token
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
    public static Suggestion suggestion() {
        String s = textBaseCluedo.suggest(); //weapon room character
        String[] splitS = s.trim().split("\\s+");

        while(!setup.getAvailableWeapons().contains(Weapon.Weapons.fromString(splitS[0]))
                || !setup.getAvailableRooms().contains(Room.Rooms.fromString(splitS[1]))
                || !setup.getAvailableCharacters().contains(Character.Characters.fromString(splitS[2]))) { //invalid input, can be duplicate character or not a token
            System.out.println("Unexpected input, try again");
            s = textBaseCluedo.suggest();
            splitS = s.trim().split("\\s+");
        }
        Weapon w = new Weapon(Weapon.Weapons.valueOf(splitS[0]));
        Room r = new Room(Room.Rooms.valueOf(splitS[1]));
        Character c = new Character(Character.Characters.valueOf(splitS[2]));
        return new Suggestion(w,r,c);
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
        initGame();
        chooseCharacters(setup.getNumPlayers());
        setup.dealCards();
//        Player megan = new Player("Megan");
//        playersList.add(megan);
//        megan.setInRoom(true);
//        playersList.add(new Player("Tristan"));
//        movePlayer();

    }

}
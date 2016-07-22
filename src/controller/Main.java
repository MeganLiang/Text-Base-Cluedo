package controller;

import model.*;
import model.Character;
import view.TextBaseCluedo;

import java.util.*;

/**
 * Created by megan on 18/07/16.
 */
public class Main {
    private static int diceRoll;
    private static Solution gameSolution;
    private static TextBaseCluedo textBaseCluedo;
    private static int numPlayers;

    public Main() {
        textBaseCluedo = new TextBaseCluedo();
        //players = new PriorityQueue<>(textBaseCluedo.getNumberOfPlayers()); //in textBaseCluedo
    }

    /**
     * one character, one weapon and one room card are selected at random and is the solution
     */
    public static Solution initSolution() {
        Weapon randomWeapon = new Weapon(null);
        Room randomRoom = new Room(null);
        Character randomCharacter = new Character(null);

        randomRoom.setRoomName((Room.Rooms) randomRoom.getRandom(Room.Rooms.class));
        randomWeapon.setWeaponName((Weapon.Weapons) randomWeapon.getRandom(Weapon.Weapons.class));
        randomCharacter.setCharacterName(randomCharacter.getRandom(Character.Characters.class));


        gameSolution = new Solution(randomWeapon, randomRoom, randomCharacter);
        return gameSolution;
    }

    public static void dealCards() {
        initSolution();
        numPlayers = textBaseCluedo.getNumP();
        dealWeapons(Weapon.Weapons.class);
        dealRooms(Room.Rooms.class);
        dealCharacters(Character.Characters.class);
        for(Player p: textBaseCluedo.getPlayerList()) {
            System.out.println("Player's hand: ============");
            p.printHand();
        }
    }

    private static void dealCharacters( Class<Character.Characters> charactersClass) {
        int numChar = charactersClass.getEnumConstants().length-1; //5, number of available weapons minus the solution weapon

        int dealtEvenly = numChar/numPlayers;
        System.out.println("c/p: " + dealtEvenly);


        List<Character.Characters> charsList = new ArrayList<>(Arrays.asList(Character.Characters.values()));
        charsList.remove(gameSolution.getCharacter().getEnum());
        System.out.println("_______________________________________");
//
//        for(Character.Characters c : charsList) {
//            System.out.println(c);
//        }
        Collections.shuffle(charsList);

        int index = 0;
        for(Player player : textBaseCluedo.getPlayerList()) {
            for (int i = 0; i < dealtEvenly; i++) {
                Character c = new Character(charsList.get(index));
                player.getHand().addToCharacterssHand(c);
                index++;
            }
//            System.out.println("Player's hand: ============");
//            player.printHand();
        }
        System.out.println("Everyone can see: ");
        for(int i = index; i < charsList.size(); i++) {
            System.out.println(charsList.get(i));
        }


    }
    private static void dealRooms( Class<Room.Rooms> roomsClass) {
        int numRooms = roomsClass.getEnumConstants().length-1; //5, number of available weapons minus the solution weapon

        int dealtEvenly = numRooms/numPlayers;
        System.out.println("r/p: " + dealtEvenly);


        List<Room.Rooms> roomsList = new ArrayList<>(Arrays.asList(Room.Rooms.values()));
        roomsList.remove(gameSolution.getRoom().getEnum());
        System.out.println("_______________________________________");
//
//        for(Room.Rooms r : roomsList) {
//            System.out.println(r);
//        }
        Collections.shuffle(roomsList);

        int index = 0;
        for(Player player : textBaseCluedo.getPlayerList()) {
            for (int i = 0; i < dealtEvenly; i++) {
                Room r = new Room(roomsList.get(index));
                player.getHand().addToRoomsHand(r);
                index++;
            }
//            System.out.println("Player's hand: ============");
//            player.printHand();
        }
        System.out.println("Everyone can see: ");
        for(int i = index; i < roomsList.size(); i++) {
            System.out.println(roomsList.get(i));
        }


    }
    private static void dealWeapons(Class<Weapon.Weapons> weaponClass) {
        int numWeapons = weaponClass.getEnumConstants().length-1; //5, number of available weapons minus the solution weapon

        int dealtEvenly = numWeapons/numPlayers;
        System.out.println("w/p: " + dealtEvenly);


        List<Weapon.Weapons> weaponsList = new ArrayList<>(Arrays.asList(Weapon.Weapons.values()));
        weaponsList.remove(gameSolution.getWeapon().getEnum());
        System.out.println("_______________________________________");
//
//
//        for(Weapon.Weapons w : weaponsList) {
//            System.out.println(w);
//        }
        Collections.shuffle(weaponsList);

        int index = 0;
        for(Player player : textBaseCluedo.getPlayerList()) {
            for (int i = 0; i < dealtEvenly; i++) {
                Weapon w = new Weapon(weaponsList.get(index));
                player.getHand().addToWeaponsHand(w);
                index++;
            }
//            System.out.println("Player's hand: ============");
//            player.printHand();
        }
        System.out.println("Everyone can see: ");
        for(int i = index; i < weaponsList.size(); i++) {
            System.out.println(weaponsList.get(i));
        }


    }

    public static void main(String[] args) {

        textBaseCluedo.getNumberOfPlayers();
        textBaseCluedo.chooseCharacters(textBaseCluedo.getNumP());
        dealCards();
    }
}

package controller;

import model.*;
import model.Character;
import view.TextBaseCluedo;

import java.util.*;

/**
 * Created by megan on 18/07/16.
 */
public class Main {
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
    private static Solution initSolution() {
        Weapon randomWeapon = new Weapon(null);
        Room randomRoom = new Room(null);
        Character randomCharacter = new Character(null);

        randomRoom.setRoomName((Room.Rooms) randomRoom.getRandom(Room.Rooms.class));
        randomWeapon.setWeaponName((Weapon.Weapons) randomWeapon.getRandom(Weapon.Weapons.class));
        randomCharacter.setCharacterName(randomCharacter.getRandom(Character.Characters.class));


        gameSolution = new Solution(randomWeapon, randomRoom, randomCharacter);
        gameSolution.printSolution();
        return gameSolution;
    }

    public static void dealCards() {
        initSolution();
        deal(Weapon.Weapons.class, Character.Characters.class, Room.Rooms.class);
        for(Player p: textBaseCluedo.getPlayerList()) {
            System.out.println("Player's hand: ============");
            p.printHand();
        }
    }

    private static void deal(Class<Weapon.Weapons> weaponClass, Class<Character.Characters> characterClass, Class<Room.Rooms> roomClass) {
        int numWeapons = weaponClass.getEnumConstants().length-1; //5, number of available weapons minus the solution weapon
        int numChars = characterClass.getEnumConstants().length-1;
        int numRooms = roomClass.getEnumConstants().length-1;

        int numCards = numWeapons+numChars+numRooms;

        int dealtEvenly = numCards/numPlayers;
        System.out.println("c/p: " + dealtEvenly);

        List<Weapon.Weapons> weaponsList = new ArrayList<>(Arrays.asList(Weapon.Weapons.values()));
        weaponsList.remove(gameSolution.getWeapon().getEnum());
        List<Character.Characters> charactersList = new ArrayList<>(Arrays.asList(Character.Characters.values()));
        charactersList.remove(gameSolution.getCharacter().getEnum());
        List<Room.Rooms> roomsList = new ArrayList<>(Arrays.asList(Room.Rooms.values()));
        roomsList.remove(gameSolution.getRoom().getEnum());

        List<Card> cardsList = new ArrayList<>();
        for(Weapon.Weapons w: weaponsList) {
            Weapon weapon = new Weapon(w);
            cardsList.add(weapon);
        }
        for(Character.Characters c: charactersList) {
            Character character = new Character(c);
            cardsList.add(character);
        }
        for(Room.Rooms r: roomsList) {
            Room room = new Room(r);
            cardsList.add(room);
        }
        System.out.println("_______________________________________");

        Collections.shuffle(cardsList);

        int index = 0;
        for(Player player : textBaseCluedo.getPlayerList()) {
            for (int i = 0; i < dealtEvenly; i++) {
                Card c = cardsList.get(index);
                player.getHand().addToHand(c);
                index++;
            }
        }
        System.out.println("Everyone can see: ");
        for(int i = index; i < cardsList.size(); i++) {
            System.out.println(cardsList.get(i));
        }


    }

    public static void main(String[] args) {
        numPlayers = textBaseCluedo.getNumberOfPlayers();
        textBaseCluedo.chooseCharacters(numPlayers);
        dealCards();
    }
}

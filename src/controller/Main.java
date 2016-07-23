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
    private static TextBaseCluedo textBaseCluedo = new TextBaseCluedo();
    private static int numPlayers;
    private static List<Player> playersList = new ArrayList<>();

    private static Set<Weapon.Weapons> availableWeapons;
    private static Set<Room.Rooms> availableRooms;
    private static Set<Character.Characters> availableCharacters;

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

    private static void dealCards() {
        initSolution();
        deal(Weapon.Weapons.class, Character.Characters.class, Room.Rooms.class);
        for(Player p: playersList) {
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
        for(Player player : playersList) {
            for (int i = 0; i < dealtEvenly; i++) {
                Card c = cardsList.get(index);
                player.getHand().addToHand(c);
                index++;
            }
        }
        System.out.println("Everyone can see: ");
        for(int i = index; i < cardsList.size(); i++) {
            System.out.println(cardsList.get(i).getName());
        }


    }

    public static Accusation accusation() {
        String s = textBaseCluedo.accuse(); //weapon room character
        String[] splitS = s.trim().split("\\s+");

        while(!availableWeapons.contains(Weapon.Weapons.fromString(splitS[0]))
                || !availableRooms.contains(Room.Rooms.fromString(splitS[1]))
                || !availableCharacters.contains(Character.Characters.fromString(splitS[2]))) { //invalid input, can be duplicate character or not a token
            System.out.println("Unexpected input, try again");
            s = textBaseCluedo.accuse();
            splitS = s.trim().split("\\s+");
        }
        Weapon w = new Weapon(Weapon.Weapons.valueOf(splitS[0]));
        Room r = new Room(Room.Rooms.valueOf(splitS[1]));
        Character c = new Character(Character.Characters.valueOf(splitS[2]));
        Accusation accusation = new Accusation(w,r,c);
        return accusation;



    }
    public static Suggestion suggestion() {
        String s = textBaseCluedo.suggest(); //weapon room character
        String[] splitS = s.trim().split("\\s+");

        while(!availableWeapons.contains(Weapon.Weapons.fromString(splitS[0]))
                || !availableRooms.contains(Room.Rooms.fromString(splitS[1]))
                || !availableCharacters.contains(Character.Characters.fromString(splitS[2]))) { //invalid input, can be duplicate character or not a token
            System.out.println("Unexpected input, try again");
            s = textBaseCluedo.suggest();
            splitS = s.trim().split("\\s+");
        }
        Weapon w = new Weapon(Weapon.Weapons.valueOf(splitS[0]));
        Room r = new Room(Room.Rooms.valueOf(splitS[1]));
        Character c = new Character(Character.Characters.valueOf(splitS[2]));
        Suggestion suggestion = new Suggestion(w,r,c);
        return suggestion;
    }
    public static Set<Character.Characters> chooseCharacters(int numOfPlayers) {
        int count = 0;
        Set<Character.Characters> chosenCharacters = new HashSet<>();
        textBaseCluedo.printHelp();
        while (count != numOfPlayers) {

            String playerName = textBaseCluedo.getPlayers();
            Player player = new Player(playerName);
            playersList.add(player);
            player.setName(playerName);

            String next = textBaseCluedo.choosingCharacters();
            if(next.contains("help")) {
                textBaseCluedo.printHelp();
                next = textBaseCluedo.choosingCharacters();
            }
            while(chosenCharacters.contains(Character.Characters.fromString(next)) || !availableCharacters.contains(Character.Characters.fromString(next))) { //invalid input, can be duplicate character or not a token
                next = textBaseCluedo.invalidCharacterInput();
            }
            if(!chosenCharacters.contains(Character.Characters.fromString(next))  && availableCharacters.contains(Character.Characters.fromString(next))) {
                chosenCharacters.add(Character.Characters.valueOf(next));
                player.setCharacter(Character.Characters.valueOf(next));
                count++;
            }
        }
        System.out.println("=======================================");
        return chosenCharacters;
    }

    public static List<Player> getPlayerList() {
        return playersList;
    }

    public static void main(String[] args) {
        availableCharacters = new HashSet<>(Arrays.asList(Character.Characters.values()));
        availableRooms= new HashSet<>(Arrays.asList(Room.Rooms.values()));
        availableWeapons = new HashSet<>(Arrays.asList(Weapon.Weapons.values()));

        numPlayers = textBaseCluedo.getNumberOfPlayers();
        chooseCharacters(numPlayers);
        dealCards();


    }
}

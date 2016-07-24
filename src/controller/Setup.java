package controller;

import model.*;
import model.Character;

import java.util.*;

/**
 * Created by liangmeij on 24/07/16.
 */
public class Setup {
    private static Game game = new Game();
    private static Solution gameSolution;
    private static int numPlayers;

    /**
     * one character, one weapon and one room card are selected at random and is the solution
     */
    public static Solution initGame() {
        game.availableCharacters = new HashSet<>(Arrays.asList(Character.Characters.values()));
        game.availableRooms = new ArrayList<>(Arrays.asList(Room.Rooms.values()));
        game.availableWeapons = new HashSet<>(Arrays.asList(Weapon.Weapons.values()));

        numPlayers = game.textBaseCluedo.getNumberOfPlayers();

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

    /**
     * solution is picked, cards are dealt evenly to players
     */
    public static void dealCards() {
        deal(Weapon.Weapons.class, Character.Characters.class, Room.Rooms.class);
        for(Player p: game.getPlayerList()) {
            System.out.println("Player's hand: ============");
            p.printHand();
        }
    }

    /**
     * the implementation for dealing the cards
     * @param weaponClass the num class Weapons
     * @param characterClass the enum class Characters
     * @param roomClass the enum class Rooms
     */
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
        for(Player player : game.getPlayerList()) {
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
    /**
     * players are asked to choose a character from the game, which determines their
     * starting position
     * @param numOfPlayers
     * @return Set<Characters></>
     */
    public static Set<Character.Characters> chooseCharacters(int numOfPlayers) {
        int count = 0;
        Set<Character.Characters> chosenCharacters = new HashSet<>();
        game.textBaseCluedo.printHelp();
        while (count != numOfPlayers) {

            String playerName = game.textBaseCluedo.getPlayers();
            Player player = new Player(playerName);
            game.addToPlayersList(player);
            player.setName(playerName);

            String next = game.textBaseCluedo.choosingCharacters();
            if(next.contains("help")) {
                game.textBaseCluedo.printHelp();
                next = game.textBaseCluedo.choosingCharacters();
            }
            while(chosenCharacters.contains(Character.Characters.fromString(next)) || !game.availableCharacters.contains(Character.Characters.fromString(next))) { //invalid input, can be duplicate character or not a token
                next = game.textBaseCluedo.invalidCharacterInput();
            }
            if(!chosenCharacters.contains(Character.Characters.fromString(next))  && game.availableCharacters.contains(Character.Characters.fromString(next))) {
                chosenCharacters.add(Character.Characters.valueOf(next));
                player.setCharacter(Character.Characters.valueOf(next));
                count++;
            }
        }
        System.out.println("=======================================");
        return chosenCharacters;
    }

    public static int getNumPlayers() {
        return numPlayers;
    }

    public static Solution getGameSolution() {
        return gameSolution;
    }
}

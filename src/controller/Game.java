package controller;

import model.*;
import model.Character;
import view.TextBaseCluedo;

import java.util.*;

import static controller.Setup.chooseCharacters;
import static controller.Setup.initGame;

public class Game {

    private static Setup setup;
    public static TextBaseCluedo textBaseCluedo = new TextBaseCluedo();

    private static List<Player> playersList = new ArrayList<>();

    public static Set<Weapon.Weapons> availableWeapons;
    public static List<Room.Rooms> availableRooms;
    public static Set<Character.Characters> availableCharacters;


    /**
     * player can make accusations to win the game
     * @return Accusation
     */
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
        return new Accusation(w,r,c);

    }

    /**
     * players can make suggestion to gather intelligence for their accusation
     * @return Suggestion
     */
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
        return new Suggestion(w,r,c);
    }


    public static Set<Weapon.Weapons> getAvailableWeapons() {
        return availableWeapons;
    }

    public static List<Room.Rooms> getAvailableRooms() {
        return availableRooms;
    }

    public static Set<Character.Characters> getAvailableCharacters() {
        return availableCharacters;
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
        setup.deal mCards();
//        Player megan = new Player("Megan");
//        playersList.add(megan);
//        megan.setInRoom(true);
//        playersList.add(new Player("Tristan"));
//        movePlayer();

    }

    public static void movePlayer() {
        for(Player player : playersList) {
            System.out.println("Hi " + player.getName());
            Random random = new Random();
            int diceRoll = random.nextInt(6 - 1 + 1) + 1;
            System.out.println("You rolled a " + diceRoll);
            String commands = "";
            List<Move.Moves> moves = new ArrayList<>(Arrays.asList(Move.Moves.values()));
            if(player.isInRoom()) {
                System.out.println("You are in a room");
                commands = textBaseCluedo.movingInRoom();
                String[] commandArray = commands.trim().split("\\s+");
                String exitRoute = commandArray[0];
                for(int i=1; i<commandArray.length; i++) {
                    while(!moves.contains(Move.Moves.fromString(commandArray[i]))) {
                        commands = textBaseCluedo.invalidArrayInput();
                        commandArray = commands.trim().split("\\s+");
                    }
                }
                while(commandArray.length != diceRoll+1) {
                    commands = textBaseCluedo.invalidArrayInput();
                    commandArray = commands.trim().split("\\s+");
                }
            }else {
                commands = textBaseCluedo.moving(); //not in room
                String[] commandArray = commands.trim().split("\\s+");

                for(int i=0; i<commandArray.length; i++) {
                    while(!moves.contains(Move.Moves.fromString(commandArray[i]))) {
                        commands = textBaseCluedo.invalidArrayInput();
                        commandArray = commands.trim().split("\\s+");
                    }
                }
                while(commandArray.length != diceRoll) {
                    commands = textBaseCluedo.invalidArrayInput();
                    commandArray = commands.trim().split("\\s+");
                }
            }



        }
    }
}

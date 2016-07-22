package view;


import model.Character;
import model.Player;

import java.util.*;

public class TextBaseCluedo {//implements UserInput{


    private static Scanner reader = new Scanner(System.in);
    private static List<Player> players = new ArrayList<>();
    private static Set<String> availableCharacters = new HashSet<>();
    private static int numP = 0;

    //use queue for players, fifo

    /**
     * initialise the possible characters
     */
    public static void addCharacterTokens() {
        availableCharacters.add("MissScarlett");
        availableCharacters.add("ColonelMustard");
        availableCharacters.add("MrsWhite");
        availableCharacters.add("ReverendGreen");
        availableCharacters.add("MrsPeacock");
        availableCharacters.add("ProfessorPlum");
    }

    /**
     * Get the number of players
     * @return the number of players
     */
    public static int getNumberOfPlayers() {
        int numPlayers = 0;
        System.out.println("=======================================");
        while(numPlayers < 3 || numPlayers > 7) {
            System.out.println("How many players?");
            numPlayers = reader.nextInt();
            if(numPlayers < 3 || numPlayers > 7 ) {
                System.out.println("Please enter a number between 3-6");
            }
        }
        System.out.println("Number of players is " + numPlayers);
        System.out.println("=======================================");
        numP = numPlayers;
        return numPlayers;
    }

    public static int getNumP() {
        return numP;
    }

    /**
     * ask for player names
     */
    public static Player getPlayers() {
        System.out.println("Enter your name");
        String playerName = reader.next();
        System.out.println("Your name is " + playerName);

        Player player = new Player(playerName);
        player.setName(playerName);
        players.add(player);

        return player;
    }

    public static List<Player> getPlayerList() {
        return players;
    }
    /**
     * User Interface for player to enter names and choose valid characters
     * @param numOfPlayers
     * @return a set of chosen characters
     */
    public static Set<Character> chooseCharacters(int numOfPlayers) {
        addCharacterTokens();
        int count = 0;
        Set<Character> chosenCharacters = new HashSet<>();
        Set<String> chosenCharacterNames = new HashSet<>();
        printHelp();
        while (count != numOfPlayers) {
            Player player = getPlayers();
            System.out.println("Which character do you want? Enter 'help' for a list of possible characters");
            String next = reader.next();

            if(next.contains("help")) {
                printHelp();
                System.out.println("Which character do you want? Enter 'help' for a list of possible characters");
                next = reader.next();

            }
            while(chosenCharacterNames.contains(next) || !availableCharacters.contains(next)) { //invalid input, can be duplicate character or not a token
                System.out.println("Unexpected input, please enter a valid character");
                next = reader.next();
            }


            if(!chosenCharacterNames.contains(next) && availableCharacters.contains(next)) {
                String characterName = next;
                Character character = new Character(Character.Characters.valueOf(next));
                chosenCharacters.add(character);
                chosenCharacterNames.add(next);
                player.setCharacter(Character.Characters.valueOf(next));
                count++;
            }
        }
        System.out.println("=======================================");
        return chosenCharacters;
    }

    private static void printHelp() {
        System.out.println("=======================================");
        for(Character.Characters c: Character.Characters.values()) {
            System.out.println(c.toString());
        }
        System.out.println("=======================================");
    }


    public static void main(String[] args) {
        addCharacterTokens();
        chooseCharacters(2);
    }

}

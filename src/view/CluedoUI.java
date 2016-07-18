package view;


import model.Character;

import java.util.*;

public class CluedoUI {//implements UserInput{

    private static int diceRoll;
    private static Scanner reader = new Scanner(System.in);
    private static List<String> playerNames = new ArrayList<>();
    private static Set<String> availableCharacters = new HashSet<>();
    //use queue for players, fifo

    /**
     * initailise the possible characters
     */
    public static void addCharacterTokens() {
//        availableCharacters.add(new Character(Character.CharacterName.MissScarlett));
//        availableCharacters.add(new Character(Character.CharacterName.ColonelMustard));
//        availableCharacters.add(new Character(Character.CharacterName.MrsWhite));
//        availableCharacters.add(new Character(Character.CharacterName.ReverendGreen));
//        availableCharacters.add(new Character(Character.CharacterName.MrsPeacock));
//        availableCharacters.add(new Character(Character.CharacterName.ProfessorPlum));

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
        while(numPlayers <= 3 || numPlayers > 7) {
            System.out.println("How many players?");
            numPlayers = reader.nextInt();
            if(numPlayers <= 3 || numPlayers > 7 ) {
                System.out.println("Please enter a number between 3-6");
            }
        }
        System.out.println("Number of players is " + numPlayers);
        System.out.println("=======================================");
        return numPlayers;
    }

    /**
     * ask for player names
     */
    public static void getPlayerNames() {
        System.out.println("Enter your name");
        String playerName = reader.next();
        playerNames.add(playerName);
    }

    /**
     * User Interface for player to enter names and choose valid characters
     * @param numOfPlayers
     * @return a set of chosen characters
     */
    public static Set<Character> chooseCharacters(int numOfPlayers) {
        int count = 0;
        Set<Character> chosenCharacters = new HashSet<>();
        Set<String> chosenCharacterNames = new HashSet<>();
        System.out.println("=======================================");
        for(String c: availableCharacters) {
            System.out.println(c.toString());
        }
        System.out.println("=======================================");
        while (count != numOfPlayers) {
            getPlayerNames();
            System.out.println("Which character do you want? Enter 'help' for a list of possible characters");
            String next = reader.next();

            if(next.contains("help")) {
                printHelp();
                System.out.println("Which character do you want? Enter 'help' for a list of possible characters");
                reader.next();
                count++;
            }else {
                while(chosenCharacterNames.contains(next) || !availableCharacters.contains(next)) { //invalid input, can be duplicate character or not a token
                    System.out.println("Unexpected input, please enter a valid character");
                    next = reader.next();
                    System.out.println("UI: " + next);
                }
                if(!chosenCharacterNames.contains(next) && availableCharacters.contains(next)) {
                    String characterName = next;
                    Character character = new Character(characterName);
                    System.out.println("all sussed");
                    chosenCharacters.add(character);
                    chosenCharacterNames.add(next);
                    count++;
                }
            }
        }
        System.out.println("=======================================");
        return chosenCharacters;
    }

    private static void printHelp() {
        System.out.println("MissScarlett");
        System.out.println("ColonelMustard");
        System.out.println("MrsWhite");
        System.out.println("ReverendGreen");
        System.out.println("MrsPeacock");
        System.out.println("ProfessorPlum");
    }


    public static void playCluedo() {
        getNumberOfPlayers();
        diceRoll = 1 + (int)(Math.random() * 6);

    }

    public static void main(String[] args) {
        addCharacterTokens();
        chooseCharacters(3);
    }

}

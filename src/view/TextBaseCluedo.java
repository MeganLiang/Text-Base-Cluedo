package view;

import model.Character;

import java.util.*;

public class TextBaseCluedo {//implements UserInput{

    private Scanner reader = new Scanner(System.in);

    /**
     * Get the number of players
     * @return the number of players
     */
    public int getNumberOfPlayers() {
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
        return numPlayers;
    }

    /**
     * ask for player names
     */
    public String getPlayers() {
        System.out.println("Enter your name");
        String playerName = reader.next();
        System.out.println("Your name is " + playerName);

        return playerName;
    }

    /**
     * User Interface for player to enter names and choose valid characters
     * @return a set of chosen characters
     */
    public String choosingCharacters() {
        System.out.println("Which character do you want? Enter 'help' for a list of possible characters");
        return reader.next();

    }
    public String invalidCharacterInput() {
        System.out.println("Unexpected input, please enter a valid character");
        return reader.next();
    }
    public String invalidArrayInput() {
        System.out.println("Unexpected input, please enter a sequence");
        return reader.nextLine();
    }
    public String suggest() {
        System.out.println("What is your suggestion? Enter [Weapon] [Room] [Character]");
        return reader.nextLine();
    }
    public String accuse() {
        System.out.println("What is your accusation? Enter [Weapon] [Room] [Character]");
        return reader.nextLine();
    }
    public String moving() {
        System.out.println("Where would you like to move? Enter [U] [D] [L] [R]");
        return reader.nextLine();
    }

    public String movingInRoom() {
        System.out.println("Where would you like to move? Enter [Exit] [U] [D] [L] [R]");
        return reader.nextLine();
    }
    public void printHelp() {
        System.out.println("=======================================");
        for(Character.Characters c: Character.Characters.values()) {
            System.out.println(c.toString());
        }
        System.out.println("=======================================");
    }
}


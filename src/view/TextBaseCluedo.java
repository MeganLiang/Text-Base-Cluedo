package view;


import model.*;
import model.Character;

import java.util.*;

public class TextBaseCluedo {//implements UserInput{

    private static Scanner reader = new Scanner(System.in);

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
        return numPlayers;
    }


    /**
     * ask for player names
     */
    public static String getPlayers() {
        System.out.println("Enter your name");
        String playerName = reader.next();
        System.out.println("Your name is " + playerName);

        return playerName;
    }


    /**
     * User Interface for player to enter names and choose valid characters
     * @return a set of chosen characters
     */
    public static String choosingCharacters() {
        System.out.println("Which character do you want? Enter 'help' for a list of possible characters");
        String next = reader.next();

        return next;

    }
    public static String invalidCharacterInput() {
        System.out.println("Unexpected input, please enter a valid character");
        String next = reader.next();
        return next;
    }
    public static String suggest() {
        System.out.println("What is your suggestion? Enter [Weapon] [Room] [Character]");
        String suggestion = reader.nextLine();
        return suggestion;
    }
    public static String accuse() {
        System.out.println("What is your accusation? Enter [Weapon] [Room] [Character]");
        String accusation = reader.nextLine();
        return accusation;
    }
    public static void printHelp() {
        System.out.println("=======================================");
        for(Character.Characters c: Character.Characters.values()) {
            System.out.println(c.toString());
        }
        System.out.println("=======================================");
    }


    public static void main(String[] args) {
    }

}


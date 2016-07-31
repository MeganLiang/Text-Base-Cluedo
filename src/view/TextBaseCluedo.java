package view;

import model.Character;

import java.util.*;

public class TextBaseCluedo implements UserInput{

    private Scanner reader = new Scanner(System.in);

    /**
     * Get the number of players
     * @return the number of players
     */
    public int getNumberOfPlayers() {
        System.out.println("How many players?");
        while(!reader.hasNextInt()) {
            System.out.println("Invalid! Please enter an integer");
            reader.next();
        }
        int numPlayers = reader.nextInt();

        return numPlayers;
    }

    /**
     * ask for player names
     */
    public String getPlayers() {
        System.out.println("Enter your name");
        String playerName = reader.next();

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
        return reader.nextLine().trim();
    }
    public String suggest() {
        System.out.println("=======================================");
        System.out.println("What is your suggestion? Enter [Weapon] [Room] [Character]");

        return reader.nextLine().trim();
    }
    public String accuse() {
        System.out.println("=======================================");
        System.out.println("What is your accusation? Enter [Weapon] [Room] [Character]");

        return reader.nextLine().trim();
    }
    public String moving() {
        System.out.println("=======================================");
        System.out.println("Where would you like to move? Enter [W] [A] [S] [D]");

        return reader.nextLine().trim();
    }

    public String movingInRoom() {
        System.out.println("=======================================");
        System.out.println("Where would you like to move? Enter [Exit] [W] [A] [S] [D]");

        return reader.nextLine();
    }
    public void printHelp() {
        System.out.println("=======================================");
        for(Character.Characters c: Character.Characters.values()) {
            System.out.println(c.toString());
        }
        System.out.println("=======================================");
    }

    public String accusationOption() {

        System.out.println("Would you like to make an accusation?");
        System.out.println("=======================================");
        return reader.next().trim();
    }
}
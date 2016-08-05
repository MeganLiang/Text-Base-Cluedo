package view;

import model.Character;
import model.Player;

import java.util.*;

public class TextBaseCluedo implements UserInput{

    /**
     * Get the number of players
     * @return the number of players
     */
    public int getNumberOfPlayers() {
        Scanner reader = new Scanner(System.in);
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
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter your name");
        String playerName = reader.next();

        return playerName;
    }

    /**
     * User Interface for player to enter names and choose valid characters
     * @return a set of chosen characters
     */
    public String choosingCharacters() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Which character do you want? Enter 'help' for a list of possible characters");
        return reader.next();

    }
    public String invalidCharacterInput() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Unexpected input, please enter a valid character");
        return reader.next();
    }
    public String invalidArrayInput() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Unexpected input, please enter a sequence");
        return reader.nextLine().trim();
    }
    public String [] suggest() {
        Scanner reader = new Scanner(System.in);
        System.out.println("=======================================");
        System.out.println("What is your suggestion? Enter [Weapon] [Room] [Character]");
        String input = reader.nextLine().trim();
        String [] suggested = input.split("\\s+");
        return suggested;
    }
    public String [] accuse() {
        Scanner reader = new Scanner(System.in);
        System.out.println("=======================================");
        System.out.println("What is your accusation? Enter [Weapon] [Room] [Character]");
        String input = reader.nextLine().trim();
        String [] suggested = input.split("\\s+");
        return suggested;
    }
    public String moving(Player player) {
        Scanner reader = new Scanner(System.in);
        System.out.println("=======================================");
        System.out.println(player.getName() + ", where would you like to move? Enter [W] [A] [S] [D]. Enter your move all at once with spaces in between");
        System.out.println("Note, if you roll a 12, you may move 1-12 moves, if you roll a 1, you must move once");

        return reader.nextLine().trim();
    }

    public void printHelp() {
        System.out.println("=======================================");
        for(Character.Characters c: Character.Characters.values()) {
            System.out.println(c.toString());
        }
        System.out.println("=======================================");
    }

    public String accusationOption() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Would you like to make an accusation?");
        System.out.println("=======================================");
        return reader.next().trim();
    }
}
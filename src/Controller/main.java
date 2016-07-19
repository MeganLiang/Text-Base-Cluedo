package Controller;

import model.*;
import model.Character;
import view.TextBaseCluedo;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by megan on 18/07/16.
 */
public class main {
    private static int diceRoll;
    private static Solution gameSolution;
    private static TextBaseCluedo textBaseCluedo;
    private static Queue<HumanPlayer> players;

    public main() {
        textBaseCluedo = new TextBaseCluedo();
        players = new PriorityQueue<>(textBaseCluedo.getNumberOfPlayers()); //queue with capacity of active players
    }

    /**
     * one character, one weapon and one room card are selected at random and is the solution
     */
    public static Solution initSolution() {
        Weapon randomWeapon = new Weapon(null);
        Room randomRoom = new Room(null);
        Character randomCharacter = new Character(null);
        randomRoom.getRandom(Room.Rooms.class);
        randomWeapon.getRandom(Weapon.Weapons.class);
        randomCharacter.getRandom(Character.Characters.class);

        gameSolution = new Solution(randomWeapon, randomRoom, randomCharacter);
        return gameSolution;
    }
    public static void dealCards(TextBaseCluedo userInput) {
        int numPlayers = userInput.getNumberOfPlayers();
        initSolution();
        dealWeapons(gameSolution, Weapon.Weapons.class, userInput);
    }

    private static void dealWeapons(Solution solution, Class weaponClass, TextBaseCluedo userInput) {
        int numWeapons = weaponClass.getEnumConstants().length-1; //5, number of available weapons minus the solution weapon
        int numPlayers = userInput.getNumberOfPlayers();

        for(int i=0; i<numWeapons; i++) {
            Weapon randomWeapon = new Weapon(null);
            randomWeapon.getRandom(weaponClass);
        }
    }

    public static void main(String[] args) {
        initSolution();
        //dealCards(textBaseCluedo);
    }
}

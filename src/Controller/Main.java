package Controller;

import model.*;
import model.Character;
import view.TextBaseCluedo;

import java.util.*;

/**
 * Created by megan on 18/07/16.
 */
public class Main {
    private static int diceRoll;
    private static Solution gameSolution;
    private static TextBaseCluedo textBaseCluedo;

    public Main() {
        textBaseCluedo = new TextBaseCluedo();
        //players = new PriorityQueue<>(textBaseCluedo.getNumberOfPlayers()); //in textBaseCluedo
    }

    /**
     * one character, one weapon and one room card are selected at random and is the solution
     */
    public static Solution initSolution() {
        Weapon randomWeapon = new Weapon(null);
        Room randomRoom = new Room(null);
        Character randomCharacter = new Character(null);

        randomRoom.setRoomName((Room.Rooms) randomRoom.getRandom(Room.Rooms.class));
        randomWeapon.setWeaponName((Weapon.Weapons) randomWeapon.getRandom(Weapon.Weapons.class));
        randomCharacter.setCharacterName(randomCharacter.getRandom(Character.Characters.class));


        gameSolution = new Solution(randomWeapon, randomRoom, randomCharacter);
        return gameSolution;
    }

    public static void dealCards() {
        initSolution();
        dealWeapons(gameSolution, Weapon.Weapons.class);
    }

    private static void dealWeapons(Solution solution, Class weaponClass) {
        int numWeapons = weaponClass.getEnumConstants().length-1; //5, number of available weapons minus the solution weapon
        int numPlayers = textBaseCluedo.getNumP();

        int dealtEvenly = numWeapons/numPlayers;
        System.out.println("w/p: " + dealtEvenly);

        List<Weapon> weaponsList = new ArrayList<>();
        weaponsList.add(new Weapon(Weapon.Weapons.CandleStick));
        weaponsList.add(new Weapon(Weapon.Weapons.Dagger));
        weaponsList.add(new Weapon(Weapon.Weapons.LeadPipe));
        weaponsList.add(new Weapon(Weapon.Weapons.Revolver));
        weaponsList.add(new Weapon(Weapon.Weapons.Rope));
        Weapon spanner = new Weapon(Weapon.Weapons.Spanner);
        weaponsList.add(spanner);

        weaponsList.remove(new Weapon(Weapon.Weapons.Spanner)); //this no work??

        System.out.println("_______________________________________");
        for(Weapon w: weaponsList) {
            System.out.println(w.toString());
        }
        Collections.shuffle(weaponsList);
        int numW = 0;
        for(Player player : textBaseCluedo.getPlayerList()) {
            for (int i = 0; i < dealtEvenly; i++) {
                Weapon w = weaponsList.get(numW);
                player.getHand().addToWeaponsHand(w);
                numW++;
            }
            System.out.println("Player's hand: ============");
            player.printHand();
        }
        System.out.println("Everyone can see: ");
        for(int i = numW; i < weaponsList.size(); i++) {
            System.out.println(weaponsList.get(i));
        }


    }

    public static void main(String[] args) {
        textBaseCluedo.getNumberOfPlayers();
        textBaseCluedo.chooseCharacters(textBaseCluedo.getNumP());
        dealCards();
    }
}

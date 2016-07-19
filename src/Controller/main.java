package Controller;

import model.Character;
import model.Room;
import model.Weapon;

/**
 * Created by megan on 18/07/16.
 */
public class main {

    public static void initSolution() {
        Weapon randomWeapon = new Weapon(null);
        Room randomRoom = new Room(null);
        Character randomCharacter = new Character(null);
        randomRoom.getRandom(Room.Rooms.class);
        randomWeapon.getRandom(Weapon.Weapons.class);
        randomCharacter.getRandom();
    }

    public static void main(String[] args) {
        initSolution();
    }
}

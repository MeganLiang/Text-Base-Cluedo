package model;

import java.security.SecureRandom;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * A class representing the murder weapon in Cluedo. This is represented by an enum and a location
 * Created by megan on 15/07/16.
 */
public class Weapon implements Card{

    private Weapons weaponName; //enum representing all the possible weapons
    private Room location; // location of the weapon
    private static final SecureRandom random = new SecureRandom();

    public Weapon(Weapons name) {
        this.weaponName = name;
    }

    public enum  Weapons { //enum of the possible murder weapons
        CandleStick(0),
        Dagger(1),
        Rope(2),
        LeadPipe(3),
        Revolver(4),
        Spanner(5);

        private static final Map<Integer, Weapons> lookup = new HashMap<>();

        static {
            for(Weapons w : EnumSet.allOf(Weapons.class)) {
                lookup.put(w.getCode(), w);
            }
        }
        private int code;
        Weapons(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
        public static Weapons get(int code) {
            return lookup.get(code);
        }
    }

    public Room getLocation() {
        return location;
    }

    @Override
    public String getName() {
        return weaponName.toString();
    }


    public Weapons getEnum() {
        return weaponName;
    }

    public void setWeaponName(Weapons weaponName) {
        this.weaponName = weaponName;
    }

    public Enum<? extends Enum> getRandom(Class clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return (Weapons) clazz.getEnumConstants()[x];
    }

    public void setLocation(Room location) {
        this.location = location;
    }


    public static void main(String[] args) {
        //getRandom(Weapons.class);
    }
}

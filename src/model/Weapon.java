package model;

import java.security.SecureRandom;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

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
        CandleStick("CandleStick"),
        Dagger("Dagger"),
        Rope("Rope"),
        LeadPipe("LeadPipe"),
        Revolver("Revolver"),
        Spanner("Spanner");

        private String text;

        Weapons(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }

        public static Weapons fromString(String text) {
            if (text != null) {
                for (Weapons b : Weapons.values()) {
                    if (text.equalsIgnoreCase(b.text)) {
                        return b;
                    }
                }
            }
            return null;
        }

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

    public static void main(String[] args) {
        //getRandom(Weapons.class);
    }
}

package model;

import java.security.SecureRandom;
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
        CandleStick,
        Dagger,
        Rope,
        LeadPipe,
        Revolver,
        Spanner
    };

//    public static Weapons getRandom(Class clazz) {
//        int x = random.nextInt(clazz.getEnumConstants().length);
//        System.out.println(clazz.getEnumConstants()[x]);
//        return (Weapons) clazz.getEnumConstants()[x];
//    }

    public Room getLocation() {
        return location;
    }

    @Override
    public String getName() {
        return weaponName.toString();
    }

    public Enum<? extends Enum> getRandom(Class clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        System.out.println(clazz.getEnumConstants()[x]);
        return (Weapons) clazz.getEnumConstants()[x];
    }


    public String toString() {
        return weaponName.toString();
    }
    public void setLocation(Room location) {
        this.location = location;
    }

//    public static final Weapon[] weapons = new Weapon[] {
//            new Weapon("CandleStick"),
//            new Weapon("Dagger"),
//            new Weapon("Rope"),
//            new Weapon("LeadPipe"),
//            new Weapon("Revolver"),
//            new Weapon("Spanner")
//    };


//    public static Weapon getRandom() {
//        Random r = new Random();
//        Weapon w = weapons[r.nextInt(weapons.length)];
//        //System.out.println(w);
//        return w;
//    }

    public static void main(String[] args) {

        //getRandom(Weapons.class);
    }
}

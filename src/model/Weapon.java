package model;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by megan on 15/07/16.
 */
public class Weapon implements Card{

    private Weapons weaponName;
    private Room location;
    private static final SecureRandom random = new SecureRandom();

    public Weapon(Weapons name) {
        this.weaponName = name;
    }

    public enum  Weapons {
        CandleStick,
        Dagger,
        Rope,
        LeadPipe,
        Revolver,
        Spanner
    };

    public static Weapons getRandom(Class clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        System.out.println(clazz.getEnumConstants()[x]);
        return (Weapons) clazz.getEnumConstants()[x];
    }

    @Override
    public Room getLocation() {
        return location;
    }

    @Override
    public String getName() {
        return weaponName.toString();
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
        getRandom(Weapons.class);
    }
}

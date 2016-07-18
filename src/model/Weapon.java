package model;

import model.Room;

import java.util.Random;

/**
 * Created by megan on 15/07/16.
 */
public class Weapon implements Token{

    private String weaponName;
    private Room location;


    public Weapon(String name) {
        this.weaponName = name;
    }
    public Room getLocation() {
        return location;
    }

    @Override
    public String getName() {
        return weaponName;
    }

    public String toString() {
        return weaponName;
    }
    public void setLocation(Room location) {
        this.location = location;
    }

    public static final Weapon[] weapons = new Weapon[] {
            new Weapon("CandleStick"),
            new Weapon("Dagger"),
            new Weapon("Rope"),
            new Weapon("LeadPipe"),
            new Weapon("Revolver"),
            new Weapon("Spanner")

    };


    public static Weapon getRandom()
    {
        Random r = new Random();
        Weapon w = weapons[r.nextInt(weapons.length)];
        //System.out.println(w);
        return w;

    }

//    public static void main(String[] args) {
//        getRandom();
//    }
}

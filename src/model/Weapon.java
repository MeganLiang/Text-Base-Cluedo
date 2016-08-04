package model;
import java.security.SecureRandom;

/**
 * A class representing the murder weapon in Cluedo. This is represented by an enum and a location
 */
public class Weapon implements Card{

    private Weapons weaponName; //enum representing all the possible weapons
    private final SecureRandom random = new SecureRandom();
    private Room inRoom;
    private Room prevRoom;
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

        public static Weapons fromString(String text) {
            if (text != null) {
                for (Weapons b : Weapons.values()) {
                    if (text.equals(b.text)) {
                        return b;
                    }
                }
            }
            return null;
        }

    }

    public String WeaponSymbol(Weapons w) {
        String toreturn = w.toString().substring(0,2);
        return toreturn;
    }
    @Override
    public String getName() {
        return weaponName.toString();
    }


    public Weapons getEnum() {
        return weaponName;
    }

    public Room getInRoom() {
        return inRoom;
    }

    public void setInRoom(Room inRoom) {
        this.inRoom = inRoom;
    }

    public Room getPrevRoom() {
        return prevRoom;
    }

    public void setPrevRoom(Room prevRoom) {
        this.prevRoom = prevRoom;
    }

    public void setWeaponName(Weapons weaponName) {
        this.weaponName = weaponName;
    }

    public Enum<? extends Enum> getRandom(Class clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return (Weapons) clazz.getEnumConstants()[x];
    }

}


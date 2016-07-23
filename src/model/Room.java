package model;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by megan on 15/07/16.
 */
public class Room implements Card  {

    private Rooms roomType; //enum which has possible tokens for rooms
    private static final SecureRandom random = new SecureRandom();

    public Room(Rooms roomType) {
        this.roomType = roomType;
    }

    public enum Rooms { // enum which represents 9 rooms in Cluedo game
        Kitchen,
        BallRoom,
        Conservatory,
        DiningRoom,
        BilliardRoom,
        Library,
        Lounge,
        Hall,
        Study
    }

    /**
     * randomly generates a room
     * @param clazz
     * @return a enum Rooms
     */
    public static Enum<? extends Enum> getRandom(Class clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return (Rooms) clazz.getEnumConstants()[x];
    }


    public void setRoomName(Rooms roomType) {
        this.roomType = roomType;
    }


    @Override
    public String getName() {
        return roomType.toString();
    }

    public Rooms getEnum() {
        return roomType;
    }

    public static void main(String[] args) {
        getRandom(Rooms.class);
    }
}

package model;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by megan on 15/07/16.
 */
public class Room implements Card {

    private Rooms roomType;
    private static final SecureRandom random = new SecureRandom();

    public Room(Rooms roomType) {
        this.roomType = roomType;
    }


    public enum Rooms {
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

    public static Rooms getRandom(Class clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        System.out.println(clazz.getEnumConstants()[x]);
        return (Rooms) clazz.getEnumConstants()[x];
    }

    public Rooms getRoomType() {
        return roomType;
    }

    public void setRoomType(Rooms roomType) {
        this.roomType = roomType;
    }

    @Override
    public Room getLocation() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }


//    public static final Room[] rooms = new Room[] {
//            new Room(Rooms.BallRoom),
//            new Room(Rooms.Conservatory),
//            new Room(Rooms.Kitchen),
//            new Room(Rooms.Study),
//            new Room(Rooms.Hall),
//            new Room(Rooms.Lounge),
//            new Room(Rooms.Library),
//            new Room(Rooms.DiningRoom),
//            new Room(Rooms.BilliardRoom)
//    };

//    public static Room getRandom() {
//        Random r = new Random();
//        Room room = rooms[r.nextInt(rooms.length)];
//        return room;
//
//    }


    public static void main(String[] args) {
        getRandom(Rooms.class);
    }
}

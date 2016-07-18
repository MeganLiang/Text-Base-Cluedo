package model;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by megan on 15/07/16.
 */
public class Room extends Location {
    public enum RoomName {
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

    private RoomName roomType;

    public static final Room[] rooms = new Room[] {
            new Room(RoomName.BallRoom),
            new Room(RoomName.Conservatory),
            new Room(RoomName.Kitchen),
            new Room(RoomName.Study),
            new Room(RoomName.Hall),
            new Room(RoomName.Lounge),
            new Room(RoomName.Library),
            new Room(RoomName.DiningRoom),
            new Room(RoomName.BilliardRoom)
    };

    public static Room getRandom() {
        Random r = new Random();
        Room room = rooms[r.nextInt(rooms.length)];
        return room;

    }
    private static final SecureRandom random = new SecureRandom();

    public static RoomName randomEnum(Class clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        System.out.println((RoomName) clazz.getEnumConstants()[x]);
        return (RoomName) clazz.getEnumConstants()[x];
    }


    public Room(RoomName roomType) {
        this.roomType = roomType;
    }

    public RoomName getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomName roomType) {
        this.roomType = roomType;
    }

    public static void main(String[] args) {
        randomEnum(RoomName.class);
    }
}

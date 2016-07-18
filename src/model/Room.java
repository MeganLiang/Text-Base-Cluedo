package model;

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

    public Room(RoomName roomType) {
        this.roomType = roomType;
    }

    public RoomName getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomName roomType) {
        this.roomType = roomType;
    }
}

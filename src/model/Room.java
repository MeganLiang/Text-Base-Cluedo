package model;

/**
 * Created by megan on 15/07/16.
 */
public class Room extends Location {
    public enum RoomType {
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

    private RoomType roomType;

    public Room(RoomType roomType) {
        this.roomType = roomType;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
}

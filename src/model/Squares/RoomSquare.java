package model.Squares;

import model.Room;

/**
 * Created by Tristan on 24/07/2016.
 */
public class RoomSquare extends Square {
    private Room room;

    public RoomSquare(Room room, int x, int y){
        super(x,y);
        this.room = room;
    }
    public String toString() {
        return "R";
    }

    public Room getRoom() {
        return room;
    }
}

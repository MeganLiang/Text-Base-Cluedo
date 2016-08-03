package model.Squares;

import model.Room;

/**
 * Created by liangmeij on 25/07/16.
 */
public class StairwaySquare extends Square {
    public Room inRoom;
    public Room outRoom;
    public StairwaySquare(Room inroom, Room outroom, int xPos, int ypos) {
        super(xPos, ypos);
        this.inRoom = inroom;
        this.outRoom = outroom;
    }

    public Room getInRoom() {
        return inRoom;
    }

    public Room getOutRoom() {
        return outRoom;
    }
}
package model.Squares;

import model.Room;

/**
 * Created by Tristan on 24/07/2016.
 */
public class RoomSquare extends Square {
    private Room room;

    public RoomSquare(Room room, int x, int y){
        super(x,y);
    }
    public String toString() {
        return "R";
    }
}
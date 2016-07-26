package model.Squares;

import model.Room;

/**
 * Created by liangmeij on 25/07/16.
 */
public class StairwaySquare extends Square {
    Room roomSquare;
    public StairwaySquare(Room inroom, int xPos, int ypos) {
        super(xPos, ypos);
        this.roomSquare = inroom;
    }
}

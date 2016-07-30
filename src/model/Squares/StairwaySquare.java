package model.Squares;

import model.Room;

public class StairwaySquare extends Square {
    Room roomSquare;
    public StairwaySquare(Room inroom, int xPos, int ypos) {
        super(xPos, ypos);
        this.roomSquare = inroom;
    }
}

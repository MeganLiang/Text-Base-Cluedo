package model.Squares;

import model.Room;


public class DoorSquare extends Square {
    Room room;

    public DoorSquare(Room room, int x, int y){
        super(x,y);
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }
}

package model.Squares;

import model.Player;
import model.Room;

/**
 * Created by Tristan on 24/07/2016.
 */
public class DoorSquare extends Square {
    Room room;
    String direction;

    public DoorSquare(Room room, int x, int y){
        super(x,y);
        this.room = room;
        this.direction = direction;
    }

    public Room getRoom() {
        return room;
    }
}

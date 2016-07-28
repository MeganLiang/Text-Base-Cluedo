package model.Squares;

import model.Room;

/**
 * Created by Tristan on 24/07/2016.
 */
public class DoorSquare extends Square {
    Room room;
    String direction;
    Player current = null;

    public DoorSquare(Room room, int x, int y){
        super(x,y);
        this.room = room;
        this.direction = direction;
    }

    //returns if the square is free or not
    public boolean isFree(){
        if (current != null){
            return false;
        }
        return true;
    }

    //sets current to player
    public void setPlayer(Player player){
        this.current = player;
    }

    //sets current player as null
    public void setNull(){
        this.current = null;
    }
}

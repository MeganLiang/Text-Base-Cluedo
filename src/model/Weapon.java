package model;

import model.Room;

/**
 * Created by megan on 15/07/16.
 */
public class Weapon {

    public enum Token {
        CandleStick,
        Dagger,
        LeadPipe,
        Revolver,
        Rope,
        Spanner
    }
    private Room location;
    private Token token;

    public Weapon(Room location, Token token) {
        this.location = location;
        this.token = token;
    }

    public Room getLocation() {
        return location;
    }
}

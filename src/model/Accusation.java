package model;

/**
 * Accusations are when the Player believes they know the solution. Once made, the
 * player either wins the game or loses
 * Created by megan on 23/07/16.
 */
public class Accusation extends Envelope {
    public Accusation(Weapon weapon, Room room, Character character) {
        super(weapon, room, character);
    }
}

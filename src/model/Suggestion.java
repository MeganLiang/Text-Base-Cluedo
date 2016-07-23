package model;

/**
 * Suggestions are made by the Player when the player is in a room and can only make suggestions about the room
 *
 */
public class Suggestion extends Envelope {
    public Suggestion(Weapon weapon, Room room, Character character) {
        super(weapon, room, character);
    }
}

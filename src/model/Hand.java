package model;

import java.util.List;

/**
 * Created by megan on 19/07/16.
 */
public class Hand {
    private List<Weapon> weaponsHand;
    private List<Character> charactersHand;
    private List<Room> roomsHand;

    public Hand(List<Weapon> weaponsHand, List<Character> charactersHand, List<Room> roomsHand) {
        this.weaponsHand = weaponsHand;
        this.charactersHand = charactersHand;
        this.roomsHand = roomsHand;
    }

    public void addToWeaponsHand(Weapon w) {
        weaponsHand.add(w);
    }

    public void addToCharacterssHand(Character c) {
        charactersHand.add(c);
    }
    public void addToRoomsHand(Room r) {
        roomsHand.add(r);
    }

    public List<Weapon> getWeaponsHand() {
        return weaponsHand;
    }

    public List<Character> getCharactersHand() {
        return charactersHand;
    }

    public List<Room> getRoomsHand() {
        return roomsHand;
    }
}



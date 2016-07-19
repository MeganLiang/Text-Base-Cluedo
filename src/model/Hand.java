package model;

import java.util.List;

/**
 * Created by megan on 19/07/16.
 */
public class Hand {
    private List<Weapon> weaponsHand;
    private List<Character> CharactersHand;
    private List<Room> roomsHand;

    public Hand(List<Weapon> weaponsHand, List<Character> charactersHand, List<Room> roomsHand) {
        this.weaponsHand = weaponsHand;
        CharactersHand = charactersHand;
        this.roomsHand = roomsHand;
    }


}

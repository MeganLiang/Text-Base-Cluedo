package model;

/**
 * Created by megan on 19/07/16.
 */
public class HumanPlayer {
    private String name;
    private Character.Characters character;
    private Hand hand;

    public HumanPlayer(String name) {
        this.name = name;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Character.Characters getCharacter() {
        return character;
    }

    public void setCharacter(Character.Characters character) {
        this.character = character;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

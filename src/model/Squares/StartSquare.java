package model.Squares;

import model.Character;

/**
 * Created by liangmeij on 25/07/16.
 */
public class StartSquare extends Square {
    Character.Characters character;
    public StartSquare(Character.Characters c,int xPos, int ypos) {
        super(xPos, ypos);
        this.character = c;
    }

    public Character.Characters getCharacter() {
        return character;
    }
}

package model.Squares;

import model.Character;

public class StartSquare extends Square {
    private Character.Characters character;
    public StartSquare(Character.Characters c,int xPos, int ypos) {
        super(xPos, ypos);
        this.character = c;
    }

    public Character.Characters getCharacter() {
        return character;
    }
}

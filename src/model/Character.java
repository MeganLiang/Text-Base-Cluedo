package model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by megan on 15/07/16.
 */
public class Character {

    public enum CharacterName {
        MissScarlett,
        ColonelMustard,
        MrsWhite,
        ReverendGreen,
        MrsPeacock,
        ProfessorPlum
    }
    private Location location;
//    private String characterName;
    private CharacterName characterName;
//
//    public Character(String name) {
//        this.characterName = name;
//    }
public Character(CharacterName name) {
    this.characterName = name;
}

    public static final Character[] characters = new Character[] {
            new Character(CharacterName.MissScarlett),
            new Character(CharacterName.ColonelMustard),
            new Character(CharacterName.MrsWhite),
            new Character(CharacterName.ReverendGreen),
            new Character(CharacterName.MrsPeacock),
            new Character(CharacterName.ProfessorPlum),

    };

    public static Character getRandom() {
        Random r = new Random();
        Character c = characters[r.nextInt(characters.length)];
        System.out.println(c.toString());
        return c;

    }

//    public String toString() {
//        return characterName;
//    }
    public String toString() {
        return characterName.toString();
    }
}

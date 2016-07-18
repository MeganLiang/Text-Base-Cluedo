package model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by megan on 15/07/16.
 */
public class Character {

//    public enum CharacterName {
//        MissScarlett,
//        ColonelMustard,
//        MrsWhite,
//        ReverendGreen,
//        MrsPeacock,
//        ProfessorPlum
//    }
    private Location location;
    private String characterName;


    public Character(String name) {
        this.characterName = name;
    }
    public static final Character[] characters = new Character[] {
            new Character("MissScarlet"),
            new Character("ColonelMustard"),
            new Character("MrsWhite"),
            new Character("ReverendGreen"),
            new Character("MrsPeacock"),
            new Character("ProfessorPlum"),

    };

    public static Character getRandom() {
        Random r = new Random();
        Character c = characters[r.nextInt(characters.length)];
        return c;

    }

    public String toString() {
        return characterName;
    }
}

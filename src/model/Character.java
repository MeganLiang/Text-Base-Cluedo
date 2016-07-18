package model;

import java.util.HashSet;
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
    public String toString() {
        return characterName;
    }
}

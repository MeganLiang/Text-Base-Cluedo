package model;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by megan on 15/07/16.
 */
public class Character implements Card{

    private Location location;
    private Characters characterName;
    private static final SecureRandom random = new SecureRandom();
    
    public Character(Characters name) {
        this.characterName = name;
    }
    
    public enum Characters {
        MissScarlett,
        ColonelMustard,
        MrsWhite,
        ReverendGreen,
        MrsPeacock,
        ProfessorPlum
    }

    @Override
    public String getName() {
        return characterName.toString();
    }

    public void setCharacterName(Characters characterName) {
        this.characterName = characterName;
    }

    public static Characters getRandom(Class clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        System.out.println(clazz.getEnumConstants()[x]);
        return (Characters) clazz.getEnumConstants()[x];
    }

//    public static final Character[] characters = new Character[] {
//            new Character(Characters.MissScarlett),
//            new Character(Characters.ColonelMustard),
//            new Character(Characters.MrsWhite),
//            new Character(Characters.ReverendGreen),
//            new Character(Characters.MrsPeacock),
//            new Character(Characters.ProfessorPlum),
//
//    };
//
//    public static Character getRandom() {
//        Random r = new Random();
//        Character c = characters[r.nextInt(characters.length)];
//        System.out.println(c.toString());
//        return c;
//
//    }

}

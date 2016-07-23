package model;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by megan on 15/07/16.
 */
public class Character implements Card{

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

    public Character.Characters getEnum() {
        return characterName;
    }

    public void setCharacterName(Characters characterName) {
        this.characterName = characterName;
    }

    public static Characters getRandom(Class clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return (Characters) clazz.getEnumConstants()[x];
    }



}

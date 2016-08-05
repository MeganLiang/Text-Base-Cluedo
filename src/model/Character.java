package model;

import java.security.SecureRandom;

/**
 * Character card in Cluedo
 */
public class Character implements Card{

    private Characters characterName;
    private static final SecureRandom random = new SecureRandom();


    public Character(Characters name) {
        this.characterName = name;
    }
    /**
     * Valid Character Enum
     *
     */
    public enum Characters {
        MissScarlett("MissScarlett"),
        ColonelMustard("ColonelMustard"),
        MrsWhite("MrsWhite"),
        ReverendGreen("ReverendGreen"),
        MrsPeacock("MrsPeacock"),
        ProfessorPlum("ProfessorPlum");

        private String text;

        Characters(String text) {
            this.text = text;
        }

        /**
         * Get name (String) for comparisons
         * @param text
         * @return
         */
        public static Characters fromString(String text) {
            if (text != null) {
                for (Characters b : Characters.values()) {
                    if (text.equals(b.text)) {
                        return b;
                    }
                }
            }
            return null;
        }

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

    /**
     * Pick a random card to deal
     * @param clazz
     * @return
     */
    public Characters getRandom(Class clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return (Characters) clazz.getEnumConstants()[x];
    }


}

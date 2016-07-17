package model;

/**
 * Created by megan on 15/07/16.
 */
public class Player {

    public enum Character {
        MissScarlett,
        ColonelMustard,
        MrsWhite,
        ReverendGreen,
        MrsPeacock,
        ProfessorPlum
    }
    private Location location;
    private Character character;

    public Player(Location location, Character character) {
        this.character = character;
        this.location = location;
    }
}

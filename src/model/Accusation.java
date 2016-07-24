package model;

/**
 * Accusations are when the Player believes they know the solution. Once made, the
 * player either wins the game or loses
 * Created by megan on 23/07/16.
 */
public class Accusation extends Envelope {
    public Accusation(Weapon weapon, Room room, Character character) {
        super(weapon, room, character);
    }
    @Override
    public boolean equals(Object o) {
        if(o==null) {
            return false;
        }else if(!(o instanceof Solution)) {
            return false;
        }else {
            Solution solution = (Solution) o;
            if(solution.getWeapon().getEnum().equals(this.weapon.getEnum())
                    && solution.getRoom().getEnum().equals(this.room.getEnum())
                    && solution.getCharacter().getEnum().equals(this.character.getEnum())) {
                return true;
            }
        }
        return false;
    }
}

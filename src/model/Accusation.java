package model;

/**
 * Accusations are when the Player believes they know the solution. Once made, the
 * player either wins the game or loses
 */
public class Accusation extends Envelope {
    public Accusation(Weapon weapon, Room room, Character character) {
        super(weapon, room, character);
    }

    /**
     * Equals() for comparing a Solution with a Accuation
     * @param o
     * @return
     */
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

    public void printAccusation() {
        System.out.println("I accused " + character.getName() + " of committing the crime in the " + room.getName() + " with the " + weapon.getName());
    }
}

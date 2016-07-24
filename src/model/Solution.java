package model;

public class Solution extends Envelope {

    public Solution(Weapon weapon, Room room, Character character) {
        super(weapon, room, character);
    }

    public void printSolution() {
        System.out.println(weapon.getName());
        System.out.println(character.getName());
        System.out.println(room.getName());
    }
    @Override
    public boolean equals(Object o) {
        if(o==null) {
            return false;
        }else if(!(o instanceof Accusation)) {
            return false;
        }else {
            Accusation accusation = (Accusation) o;
            if(accusation.getWeapon().getEnum().equals(this.weapon.getEnum())
                    && accusation.getRoom().getEnum().equals(this.room.getEnum())
                    && accusation.getCharacter().getEnum().equals(this.character.getEnum())) {
                return true;
            }
        }
        return false;
    }
}

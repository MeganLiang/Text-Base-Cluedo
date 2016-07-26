import controller.Game;
import model.*;
import model.Character;
import org.junit.Test;
import java.awt.*;
import static controller.Game.addToPlayersList;
import static controller.Game.suggestion;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CluedoTests {
    private Game game = new Game();


    @Test
    public void testingSolutionEqualsAccusation() {
        Solution solution = new Solution(new Weapon(Weapon.Weapons.Revolver), new Room(Room.Rooms.BallRoom), new Character(Character.Characters.ColonelMustard));
        Accusation accusation = new Accusation(new Weapon(Weapon.Weapons.Revolver), new Room(Room.Rooms.BallRoom), new Character(Character.Characters.ColonelMustard));
        assertTrue("solution equals accusation", solution.equals(accusation));
    }

    @Test
    public void getCharacterStartPosition1() {
        Player megan = new Player("Megan");
        megan.setCharacter(Character.Characters.ColonelMustard);
        megan.startingSquare(Character.Characters.ColonelMustard);
        // coordinates returned
        assertEquals(megan.startingSquare(Character.Characters.ColonelMustard), new Point(0,17));
    }

    @Test
    public void getCharacterStartPosition2() {
        Player megan = new Player("Megan");
        megan.setCharacter(Character.Characters.ReverendGreen);
        megan.startingSquare(Character.Characters.ReverendGreen);
        // coordinates returned
        assertEquals(megan.startingSquare(Character.Characters.ReverendGreen), new Point(14,0));
    }

    @Test
    public void testPlayerInRoom() {
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(1, 1));
        assertEquals(megan.isInRoom(megan), true);
    }
//    @Test
//    public void testSuggestions() {
//        Player megan = new Player("Megan");
//        megan.setPositionPoint(new Point(1,1)); //in kitchen
//        addToPlayersList(megan);
//        suggestion();
//
//    }


}
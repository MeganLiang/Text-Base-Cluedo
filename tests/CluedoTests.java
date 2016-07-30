import controller.Game;
import model.*;
import model.Character;
import org.junit.Test;
import java.awt.*;

import static controller.Moving.moveCheck;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
        Board board = new Board();
        Player megan = new Player("Megan");
        megan.setCharacter(Character.Characters.ColonelMustard);
        megan.startingSquare(Character.Characters.ColonelMustard, board);
        // coordinates returned
        assertEquals(megan.startingSquare(Character.Characters.ColonelMustard, board), new Point(0,17));
    }

    @Test
    public void getCharacterStartPosition2() {
        Board board = new Board();
        Player megan = new Player("Megan");
        megan.setCharacter(Character.Characters.ReverendGreen);
        megan.startingSquare(Character.Characters.ReverendGreen, board);
        // coordinates returned
        assertEquals(megan.startingSquare(Character.Characters.ReverendGreen,board), new Point(14,0));
    }

    @Test
    public void testPlayerInRoom() {
        Board board = new Board();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(1, 1));
        assertEquals(megan.isInRoom(megan, board), true);
    }
    @Test
    public void testPlayerInRoom2() {
        Board board = new Board();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(0,17)); //starting square 6
        if(megan.isInRoom(megan,board)) {
            assertFalse("Player not in room", megan.isInRoom(megan,board));
        }

    }
    @Test
    public void testPlayerMoveBasic1() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(0,7));
        Point moveTo = new Point(1,7);
        boolean m = moveCheck(moveTo,megan,6);
        System.out.println(m);
        assertEquals(m,true);
        assertEquals(megan.getPositionPoint(),moveTo);
    }


}
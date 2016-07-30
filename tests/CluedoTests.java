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
        Board b = new Board();
        Player megan = new Player("Megan");
        megan.setCharacter(Character.Characters.ColonelMustard);
        megan.startingSquare(Character.Characters.ColonelMustard,b);
        // coordinates returned
        assertEquals(megan.startingSquare(Character.Characters.ColonelMustard,b), new Point(0,17));
    }

    @Test
    public void getCharacterStartPosition2() {
        Board b = new Board();
        Player megan = new Player("Megan");
        megan.setCharacter(Character.Characters.ReverendGreen);
        megan.startingSquare(Character.Characters.ReverendGreen,b);
        // coordinates returned
        assertEquals(megan.startingSquare(Character.Characters.ReverendGreen,b), new Point(14,0));
    }

    @Test
    public void testPlayerInRoom() {
        Board b = new Board();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(1, 1));
        assertEquals(megan.isInRoom(megan,b), true);
    }

    @Test
    public void testPlayerMoveBasic1() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(1,8));
        Point moveTo = new Point(1,9);
        //System.out.println(g.move(moveTo,megan,1));
        assertEquals(g.move(moveTo,megan,1),true);
        assertEquals(megan.getPositionPoint(),moveTo);
    }

    @Test
    public void testPlayerMoveBasic2() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(0,8));
        Point moveTo = new Point(5,8);
        assertEquals(g.move(moveTo,megan,5),true);
        assertEquals(megan.getPositionPoint(),moveTo);
    }

    @Test
    public void testPlayerMoveBasic3() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(0,8));
        Point moveTo = new Point(3,9);
        assertEquals(g.move(moveTo,megan,5),true);
        assertEquals(megan.getPositionPoint(),moveTo);
    }

    @Test
    public void testPlayerMoveIncorrect1() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(0,8));
        Point moveTo = new Point(5,10);
        assertEquals(g.move(moveTo,megan,1),false);
        //assertEquals(megan.getPositionPoint(),);
    }

    @Test
    public void testPlayerMoveIncorrectOccupied() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(0,8));
        Player tristan = new Player("Tristan");
        g.addToPlayersList(megan);
        megan.setPositionPoint(new Point(0,8));
        tristan.setPositionPoint(new Point(1,8));
        g.addToPlayersList(tristan);
        Point moveTo = new Point(1,8);
        assertEquals(g.move(moveTo,megan,1),false);
        assertEquals(megan.getPositionPoint(),new Point(0,8));
    }

    @Test
    public void testPlayerMoveIncorrectRoom() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(0,8));
        Point moveTo = new Point(0,6);
        assertEquals(g.move(moveTo,megan,3),false);
        //assertEquals(megan.getPositionPoint(),new Point(0,8));
    }

    @Test
    public void testPlayerMoveCorrectRoom() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(2,7));
        Point moveTo = new Point(3,6);
        assertEquals(g.move(moveTo,megan,6),true);
        //assertEquals(megan.getPositionPoint(),new Point(0,8));
    }

    @Test
    public void testPlayerMoveLeaveRoomCorrect1() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(3,6));
        Point moveTo = new Point(2,7);
        assertEquals(g.move(moveTo,megan,6),true);
        //assertEquals(megan.getPositionPoint(),new Point(0,8));
    }

    @Test
    public void testPlayerLeavingBoard() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(24,1));
        Point moveTo = new Point(25,1);
        assertEquals(g.move(moveTo,megan,6),false);
        //assertEquals(megan.getPositionPoint(),new Point(0,8));
    }

    @Test
    public void testPlayerMoveLeaveRoomCorrect2() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(4,22));
        Point moveTo = new Point(5,18);
        assertEquals(g.move(moveTo,megan,5),true);
        //assertEquals(megan.getPositionPoint(),new Point(0,8));
    }

    @Test
    public void testPlayerMoveLeaveRoomCorrect3() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(12,4));
        Point moveTo = new Point(6,5);
        assertEquals(g.move(moveTo,megan,5),true);
        //assertEquals(megan.getPositionPoint(),new Point(0,8));
    }

    @Test
    public void testPlayerMoveLeaveRoomIncorrect1() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(12,4));
        Point moveTo = new Point(6,5);
        assertEquals(g.move(moveTo,megan,5),true);
        //assertEquals(megan.getPositionPoint(),new Point(0,8));
    }

}
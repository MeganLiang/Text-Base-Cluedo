import controller.Game;
import controller.Guessing;
import controller.Moving;
import model.*;
import model.Character;
import org.junit.Test;
import java.awt.*;
import java.awt.List;
import java.util.*;

import static controller.Moving.moveCheck;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CluedoTests {

    @Test
    public void testRefutingSuggestions() {
        Player megan = new Player("Megan");
        Player tristan = new Player("Tristan");
        Game.addToPlayersList(megan);
        Game.addToPlayersList(tristan);
        java.util.List<Card> cardsM = new ArrayList<>();
        cardsM.add(new Weapon(Weapon.Weapons.Revolver));
        java.util.List<Card> cardsT = new ArrayList<>();
        cardsT.add(new Room(Room.Rooms.Study));
        cardsT.add(new Weapon(Weapon.Weapons.Spanner));
        megan.setHand(new Hand(cardsM));
        tristan.setHand(new Hand(cardsT));
        megan.setInRoom(true);
        megan.setPositionPoint(new Point(23,24));//study
        tristan.setInRoom(false);
        tristan.setPositionPoint(new Point(9,0));
        Suggestion s = new Suggestion(new Weapon(Weapon.Weapons.Spanner), new Room(Room.Rooms.Study), new Character(Character.Characters.MrsWhite));
        Guessing.proveSuggestions(s, megan);
    }

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
        Board b = new Board();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(1, 1));
        assertEquals(megan.isInRoom(megan,b), true);
    }

    @Test
    public void testPlayerMoveBasic2() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(0,8));
        Point moveTo = new Point(5,8);
        assertEquals(Moving.moveCheck(moveTo, megan, 5),true);
        assertEquals(megan.getPositionPoint(),moveTo);
    }

    @Test
    public void testPlayerMoveBasic3() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(0,8));
        Point moveTo = new Point(3,9);
        assertEquals(Moving.moveCheck(moveTo,megan,5),true);
        assertEquals(megan.getPositionPoint(),moveTo);
    }

    @Test
    public void testPlayerMoveIncorrect1() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(0,8));
        Point moveTo = new Point(5,10);
        assertEquals(Moving.moveCheck(moveTo,megan,1),false);
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
        assertEquals(Moving.moveCheck(moveTo,megan,1),false);
        assertEquals(megan.getPositionPoint(),new Point(0,8));
    }

    @Test
    public void testPlayerMoveIncorrectRoom() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(0,8));
        Point moveTo = new Point(0,6);
        assertEquals(Moving.moveCheck(moveTo,megan,3),false);
        //assertEquals(megan.getPositionPoint(),new Point(0,8));
    }

    @Test
    public void testPlayerMoveCorrectRoom() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(2,7));
        Point moveTo = new Point(3,6);
        assertEquals(Moving.moveCheck(moveTo,megan,6),true);
        //assertEquals(megan.getPositionPoint(),new Point(0,8));
    }

    @Test
    public void testPlayerMoveLeaveRoomCorrect1() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(3,6));
        Point moveTo = new Point(2,7);
        assertEquals(Moving.moveCheck(moveTo,megan,6),true);
        //assertEquals(megan.getPositionPoint(),new Point(0,8));
    }

    @Test
    public void testPlayerLeavingBoard() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(23,1));
        Point moveTo = new Point(24,1);
        assertEquals(Moving.moveCheck(moveTo,megan,6),false);
        //assertEquals(megan.getPositionPoint(),new Point(0,8));
    }

    @Test
    public void testPlayerMoveLeaveRoomCorrect2() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(4,22));
        Point moveTo = new Point(5,18);
        assertEquals(Moving.moveCheck(moveTo,megan,5),true);
        //assertEquals(megan.getPositionPoint(),new Point(0,8));
    }

    @Test
    public void testPlayerMoveLeaveRoomCorrect3() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(12,4));
        Point moveTo = new Point(6,5);
        assertEquals(Moving.moveCheck(moveTo,megan,5),true);
        //assertEquals(megan.getPositionPoint(),new Point(0,8));

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

    @Test
    public void testPlayerMoveLeaveRoomIncorrect1() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(12,4));
        Point moveTo = new Point(6,5);
        assertEquals(Moving.moveCheck(moveTo,megan,5),true);
        //assertEquals(megan.getPositionPoint(),new Point(0,8));
    }

}
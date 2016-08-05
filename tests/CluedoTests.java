import controller.Game;
import controller.Guessing;
import controller.Moving;
import model.*;
import model.Character;
import org.junit.Test;
import java.awt.*;
import java.awt.List;
import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CluedoTests {

    @Test
    public void testingSolutionEqualsAccusation() {
        Solution solution = new Solution(new Weapon(Weapon.Weapons.Revolver), new Room(Room.Rooms.BallRoom), new Character(Character.Characters.ColonelMustard));
        Accusation accusation = new Accusation(new Weapon(Weapon.Weapons.Revolver), new Room(Room.Rooms.BallRoom), new Character(Character.Characters.ColonelMustard));
        assertTrue("solution equals accusation", solution.equals(accusation));
    }

    @Test
    public void getCharacterStartPosition1() {
        Game cluedo = new Game();
        Board board = new Board();
        Player megan = new Player("Megan");
        megan.setCharacter(Character.Characters.ColonelMustard);
        megan.startingSquare(Character.Characters.ColonelMustard, board, cluedo);
        // coordinates returned
        assertEquals(megan.startingSquare(Character.Characters.ColonelMustard, board,cluedo), new Point(0,17));
    }

    @Test
    public void getCharacterStartPosition2() {
        Game cluedo = new Game();
        Board board = new Board();
        Player megan = new Player("Megan");
        megan.setCharacter(Character.Characters.ReverendGreen);
        megan.startingSquare(Character.Characters.ReverendGreen, board,cluedo);
        // coordinates returned
        assertEquals(megan.startingSquare(Character.Characters.ReverendGreen,board,cluedo), new Point(14,0));
    }

    @Test
    public void testRefutingSuggestions() {
        Game cluedo = new Game();
        Player megan = new Player("Megan");
        Player tristan = new Player("Tristan");
        cluedo.addToPlayersList(megan);
        cluedo.addToPlayersList(tristan);
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
        Guessing.proveSuggestions(s, megan, cluedo);
    }


    @Test
    public void testPlayerInRoom() {
        Game cluedo = new Game();
        Board b = new Board();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(1, 1));
        assertEquals(megan.isInRoom(megan,b, cluedo), true);
    }

    @Test
    public void testPlayerMoveBasic2() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setCharacter(Character.Characters.MrsWhite);
        megan.setPositionPoint(new Point(0,8));
        Point moveTo = new Point(5,8);
        assertEquals(g.getMoving().moveCheck(moveTo, megan, 5, g),true);
        assertEquals(megan.getPositionPoint(),moveTo);
    }

    @Test
    public void testPlayerMoveBasic4() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(6,2));
        Point moveTo = new Point(5,2);
        assertEquals(g.getMoving().moveCheck(moveTo, megan, 6, g),false);
        //assertEquals(megan.getPositionPoint(),new Point(9,2));
    }

    @Test
    public void testPlayerMoveBasic3() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setCharacter(Character.Characters.MrsWhite);
        megan.setPositionPoint(new Point(15,6));
        Point moveTo = new Point(19,6);
        assertEquals(g.getMoving().moveCheck(moveTo,megan,5,g),true);
        assertEquals(megan.getPositionPoint(),moveTo);
    }

    @Test
    public void testPlayerMoveIncorrect1() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(0,8));
        Point moveTo = new Point(5,10);
        assertEquals(g.getMoving().moveCheck(moveTo,megan,1,g),false);
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
        assertEquals(g.getMoving().moveCheck(moveTo,megan,1,g),false);
        assertEquals(megan.getPositionPoint(),new Point(0,8));
    }


    @Test
    public void testPlayerMoveIncorrectRoom2() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(8,1));
        Point moveTo = new Point(10,1);
        assertEquals(g.getMoving().moveCheck(moveTo,megan,3,g),false);
    }

    @Test
    public void testPlayerMoveCorrectRoom() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setCharacter(Character.Characters.MrsWhite);
        megan.setPositionPoint(new Point(7,5));
        Point moveTo = new Point(9,5);
        assertEquals(g.getMoving().moveCheck(moveTo,megan,6,g),true);
    }

    @Test
    public void testPlayerMoveLeaveRoomCorrect1() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setCharacter(Character.Characters.MrsWhite);
        megan.setPositionPoint(new Point(3,6));
        Point moveTo = new Point(2,7);
        assertEquals(g.getMoving().moveCheck(moveTo,megan,6,g),true);
    }

    @Test
    public void testPlayerLeavingBoard() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(23,1));
        Point moveTo = new Point(24,1);
        assertEquals(g.getMoving().moveCheck(moveTo,megan,6,g),false);
    }

    @Test
    public void testPlayerMoveLeaveRoomCorrect2() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setCharacter(Character.Characters.MrsWhite);
        megan.setPositionPoint(new Point(4,22));
        Point moveTo = new Point(5,18);
        assertEquals(g.getMoving().moveCheck(moveTo,megan,5,g),true);
    }

    @Test
    public void testPlayerMoveLeaveRoomCorrect3() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setCharacter(Character.Characters.MrsWhite);
        megan.setPositionPoint(new Point(12,4));
        Point moveTo = new Point(6,5);
        assertEquals(g.getMoving().moveCheck(moveTo,megan,5,g),true);

    }
    @Test
    public void testPlayerInRoom2() {
        Game g= new Game();
        Board board = new Board();
        Player megan = new Player("Megan");

        megan.setPositionPoint(new Point(0,17)); //starting square 6
        if(megan.isInRoom(megan,board, g)) {
            assertFalse("Player not in room", megan.isInRoom(megan,board,g));
        }

    }
    @Test
    public void testPlayerMoveBasic1() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setCharacter(Character.Characters.MrsWhite);
        megan.setPositionPoint(new Point(0,7));
        Point moveTo = new Point(1,7);
        boolean m = g.getMoving().moveCheck(moveTo,megan,6,g);
        System.out.println(m);
        assertEquals(m,true);
        assertEquals(megan.getPositionPoint(),moveTo);
    }

    @Test
    public void testPlayerMoveLeaveRoomIncorrect1() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setCharacter(Character.Characters.MrsWhite);
        megan.setPositionPoint(new Point(12,4));
        Point moveTo = new Point(6,5);
        assertEquals(g.getMoving().moveCheck(moveTo,megan,5,g),true);
    }

    @Test
    public void testPreviousMove() {
        Player megan = new Player("Megan");
        megan.setCharacter(Character.Characters.MrsWhite);
        megan.setPositionPoint(new Point(12,4));
        megan.setPreviousPoint(new Point(12,4));
        megan.setPositionPoint(new Point(12,3));
        assertEquals(megan.getPreviousPoint(), new Point(12,4));
    }
    @Test
    public void outOfBoundsMoving_1() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setCharacter(Character.Characters.MrsWhite);
        megan.setPositionPoint(new Point(23,24));
        Point moveTo = new Point(24,24);
        assertEquals(g.getMoving().moveCheck(moveTo,megan,1,g),false);
    }

    @Test
    public void outOfBoundsMoving_2() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setCharacter(Character.Characters.MrsWhite);
        megan.setPositionPoint(new Point(23,2));
        Point moveTo = new Point(25,2);
        assertEquals(g.getMoving().moveCheck(moveTo,megan,2,g),false);
    }

}
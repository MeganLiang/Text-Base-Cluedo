package model;

import controller.Game;
import model.Squares.DoorSquare;
import model.Squares.RoomSquare;
import model.Squares.Square;
import model.Squares.StairwaySquare;
import java.security.SecureRandom;
import java.util.*;

public class Room implements Card  {

    private Rooms roomType; //enum which has possible tokens for rooms
    private static final SecureRandom random = new SecureRandom();

    public Room(Rooms roomType) {
        this.roomType = roomType;
    }

    public enum Rooms { // enum which represents 9 rooms in Cluedo game
        Kitchen("Kitchen"),
        BallRoom("BallRoom"),
        Conservatory("Conservatory"),
        DiningRoom("DiningRoom"),
        BilliardRoom("BilliardRoom"),
        Library("Library"),
        Lounge("Lounge"),
        Hall("Hall"),
        Study("Study");

        private String text;

        Rooms(String text) {
            this.text = text;
        }

        public static Rooms fromString(String text) {
            if (text != null) {
                for (Rooms b: Rooms.values()) {
                    if (text.equals(b.text)) {
                        return b;
                    }
                }
            }
            return null;
        }
     }

    /**
     * randomly generates a room
     * @return a enum Rooms
     */
    @Override
    public Enum<? extends Enum> getRandom(Class clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return (Rooms) clazz.getEnumConstants()[x];
    }


    public void setRoomName(Rooms roomType) {
        this.roomType = roomType;
    }


    @Override
    public String getName() {
        return roomType.toString();
    }

    public Rooms getEnum() {
        return roomType;
    }

    public Set<Square> findExits(Player player) {
        Set<Square> exits = new HashSet<>();
        if (Game.getBoard().returnSquare(player.getPositionPoint()) instanceof RoomSquare) { //player in room
            Room currentRoom = ((RoomSquare) Game.getBoard().returnSquare(player.getPositionPoint())).getRoom();
            System.out.println(player + ", you are in the " + currentRoom.toString());
            for (int x = 0; x < Game.getBoard().getWIDTH(); x++) {
                for (int y = 0; y < Game.getBoard().getHEIGHT(); y++) {
                    if (Game.getBoard().getBoard()[x][y] instanceof DoorSquare) { //Door exit
                        DoorSquare doorSquare = (DoorSquare) Game.getBoard().getBoard()[x][y];
                        if (doorSquare.getRoom().getName().equals(currentRoom.getName())) {
                            exits.add(doorSquare);
                        }
                    } else if (Game.getBoard().getBoard()[x][y] instanceof StairwaySquare) { //stairway exit
                        StairwaySquare stairwaySquare = (StairwaySquare) Game.getBoard().getBoard()[x][y];
                        if (stairwaySquare.getRoom().getName().equals(currentRoom.getName())) {
                            exits.add(stairwaySquare);
                        }
                    }
                }
            }
        }
        return exits;
    }
    public void printExits(Player player, Board board) {
        Set<Square> roomExits = player.findRoom(player,board).findExits(player);
        System.out.println("Here are the coordinates of your exits: ");
        for (Square square: roomExits) {
            System.out.println("x= " + square.getxPos() + " y= " + square.getYpos());
        }
    }

    public static void main(String[] args) {
        //getRandom(Rooms.class);
    }
}

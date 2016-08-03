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
    private List<Weapon> weaponList = new ArrayList<>();

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
     * Weapons are teleported to different rooms
     * @param weapon
     */
    public void addWeapon(Weapon weapon) {
        this.weaponList.add(weapon);
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

    public Set<Square> findExits(Player player, Game cluedo) {
        Set<Square> exits = new HashSet<>();
        if (cluedo.getBoard().returnSquare(player.getPositionPoint()) instanceof RoomSquare) { //player in room
            Room currentRoom = ((RoomSquare) cluedo.getBoard().returnSquare(player.getPositionPoint())).getRoom();
            System.out.println(player.getName() + ", you are in the " + currentRoom.getName());
            for (int x = 0; x < cluedo.getBoard().getWIDTH(); x++) {
                for (int y = 0; y < cluedo.getBoard().getHEIGHT(); y++) {
                    if (cluedo.getBoard().getBoard()[x][y] instanceof DoorSquare) { //Door exit
                        DoorSquare doorSquare = (DoorSquare) cluedo.getBoard().getBoard()[x][y];
                        if (doorSquare.getRoom().getName().equals(currentRoom.getName())) {
                            exits.add(doorSquare);
                        }
                    } else if (cluedo.getBoard().getBoard()[x][y] instanceof StairwaySquare) { //stairway exit
                        StairwaySquare stairwaySquare = (StairwaySquare) cluedo.getBoard().getBoard()[x][y];
                        if (stairwaySquare.getInRoom().getName().equals(currentRoom.getName())) {
                            exits.add(stairwaySquare);
                        }
                    }
                }
            }
        }
        return exits;
    }
    public void printExits(Player player, Board board, Game cluedo) {
        Set<Square> roomExits = player.findRoom(player,board,cluedo).findExits(player, cluedo);
        System.out.println("Here are the coordinates of your exits: ");
        for (Square square: roomExits) {
            System.out.println("x= " + square.getxPos() + ", y= " + square.getYpos());
        }
    }

    public List<Weapon> getWeaponList() {
        return weaponList;
    }

    public static void main(String[] args) {
        //getRandom(Rooms.class);
    }
}

package view;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CluedoUI {//implements UserInput{

    private static int diceRoll;

    public static int getNumberOfPlayers() {
        Scanner reader = new Scanner(System.in);
        int numPlayers = 0;
        while(numPlayers <= 3 || numPlayers > 7) {
            System.out.println("How many players?");
            numPlayers = reader.nextInt();
            if(numPlayers <= 3 || numPlayers>6 ) {
                System.out.println("Please enter a number between 3-6");
            }
        }
        System.out.println("Number of players is " + numPlayers);

    }


    public static void allocateWeaponsToRooms() {
        Room room = new Room(null);
        Weapon w = new Weapon(null, null);

        int numRooms = 9;
        int randomNumber;
        ArrayList<Integer> list = new ArrayList<>(numRooms);
        for(int i=1; i<=numRooms; i++) {
            list.add(i);
        }
        Random random = new Random();
        while (list.size() > 0) {
            int index = random.nextInt(list.size());
            randomNumber = list.remove(index);
            System.out.println("list removed " + randomNumber);

            switch(randomNumber) {
                case 1:
                    room.setRoomType(Room.RoomType.Kitchen);
                    break;
                case 2:
                    room.setRoomType(Room.RoomType.BallRoom);
                    break;
                case 3:
                    room.setRoomType(Room.RoomType.Conservatory);
                    break;
                case 4:
                    room.setRoomType(Room.RoomType.DiningRoom);
                    break;
                case 5:
                    room.setRoomType(Room.RoomType.BilliardRoom);
                    break;
                case 6:
                    room.setRoomType(Room.RoomType.Library);
                    break;
                case 7:
                    room.setRoomType(Room.RoomType.Lounge);
                    break;
                case 8:
                    room.setRoomType(Room.RoomType.Hall);
                    break;
                case 9:
                    room.setRoomType(Room.RoomType.Study);
                    break;
            }
            System.out.println(room.getRoomType());
        }
    }

    public static void playCluedo() {
        getNumberOfPlayers();
        diceRoll = 1 + (int)(Math.random() * 6);

    }

    public static void main(String[] args) {
        //playCluedo();
        allocateWeaponsToRooms();
    }

}

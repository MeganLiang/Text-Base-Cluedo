package model;

import java.io.*;

public class Board {
    private static File file = new File("data/board.txt");

    private static Square[][] board = new Square[x][y];

    private static void handleFile(File file)
            throws IOException {
        try (InputStream in = new FileInputStream(new File("data/board.txt"));
             Reader reader = new InputStreamReader(in);
             // buffer for efficiency
             Reader buffer = new BufferedReader(reader)) {
            handleCharacters(buffer);
        }
    }

    private static void handleCharacters(Reader reader)
            throws IOException {
        int r;
        while ((r = reader.read()) != -1) {
            char ch = (char) r;
            System.out.print(ch);
            char character = 'N';
            int counterX = 0;
            int counterY = 0;

            switch (character) {
                case 'N' :
                    Square nullSquare = null;
                    counterX++;
                    break;
                case 'B' :
                    NormalSquare normalSquare = new NormalSquare(counterX,counterY);
                    counterX++;
                    break;
                case ' ':
                    counterY++;
                    System.out.println("new line");
                    break;
                case 'K' :
                    RoomSquare kitchen = new RoomSquare(new Room(Room.Rooms.Kitchen), counterX, counterX);
                    counterX++;
                    break;
                case 'A' :
                    RoomSquare ballroom = new RoomSquare(new Room(Room.Rooms.BallRoom), counterX, counterY);
                    counterX++;
                    break;
                case 'C' :
                    RoomSquare conservatory = new RoomSquare(new Room(Room.Rooms.Conservatory), counterX, counterX);
                    counterX++;
                    break;
                case 'D' :
                    RoomSquare diningRoom = new RoomSquare(new Room(Room.Rooms.DiningRoom), counterX, counterX);
                    counterX++;
                    break;
                case 'I' :
                    RoomSquare billiardRoom = new RoomSquare(new Room(Room.Rooms.BilliardRoom), counterX, counterX);
                    counterX++;
                    break;
                case 'L' :
                    RoomSquare library = new RoomSquare(new Room(Room.Rooms.Library), counterX, counterX);
                    counterX++;
                    break;
                case 'S' :
                    RoomSquare study = new RoomSquare(new Room(Room.Rooms.Study), counterX, counterX);
                    counterX++;
                    break;
                case 'H' :
                    RoomSquare hall = new RoomSquare(new Room(Room.Rooms.Hall), counterX, counterX);
                    counterX++;
                    break;
                case 'O' :
                    RoomSquare lounge = new RoomSquare(new Room(Room.Rooms.Lounge), counterX, counterX);
                    counterX++;
                    break;
                case 'k' :
                    DoorSquare kExit = new DoorSquare(new Room(Room.Rooms.Kitchen), counterX, counterY);
                    counterX++;
                    break;
                case 'a' :
                    DoorSquare aExit = new DoorSquare(new Room(Room.Rooms.BallRoom), counterX, counterY);
                    counterX++;
                    break;
                case 'c' :
                    DoorSquare cExit = new DoorSquare(new Room(Room.Rooms.Conservatory), counterX, counterY);
                    counterX++;
                    break;
                case 'd' :
                    DoorSquare dExit = new DoorSquare(new Room(Room.Rooms.DiningRoom), counterX, counterY);
                    counterX++;
                    break;
                case 'i' :
                    DoorSquare iExit = new DoorSquare(new Room(Room.Rooms.BilliardRoom), counterX, counterY);
                    counterX++;
                    break;
                case 'l' :
                    DoorSquare lExit = new DoorSquare(new Room(Room.Rooms.Library), counterX, counterY);
                    counterX++;
                    break;
                case 's' :
                    DoorSquare sExit = new DoorSquare(new Room(Room.Rooms.Study), counterX, counterY);
                    counterX++;
                    break;
                case 'h' :
                    DoorSquare hExit = new DoorSquare(new Room(Room.Rooms.Hall), counterX, counterY);
                    counterX++;
                    break;
                case 'o' :
                    DoorSquare oExit = new DoorSquare(new Room(Room.Rooms.Lounge), counterX, counterY);
                    counterX++;
                    break;
                case '1' :
                    StartSquare mrsWhite = new StartSquare(Character.Characters.MrsWhite, counterX, counterY);
                    counterX++;
                    break;
                case '2' :
                    StartSquare revGreen = new StartSquare(Character.Characters.ReverendGreen, counterX, counterY);
                    counterX++;
                    break;
                case '3' :
                    StartSquare peacock = new StartSquare(Character.Characters.MrsPeacock, counterX, counterY);
                    counterX++;
                    break;
                case '4' :
                    StartSquare plum = new StartSquare(Character.Characters.ProfessorPlum, counterX, counterY);
                    counterX++;
                    break;
                case '5' :
                    StartSquare scarlett = new StartSquare(Character.Characters.MissScarlett, counterX, counterY);
                    counterX++;
                    break;
                case '6' :
                    StartSquare mustard = new StartSquare(Character.Characters.ColonelMustard, counterX, counterY);
                    counterX++;
                    break;
                case 'W' :
                    StairwaySquare kitchenStairway = new StairwaySquare(new Room(Room.Rooms.Kitchen), counterX, counterY);
                    counterX++;
                    break;
                case 'X' :
                    StairwaySquare conservStairway = new StairwaySquare(new Room(Room.Rooms.Conservatory), counterX, counterY);
                    counterX++;
                    break;
                case 'Y' :
                    StairwaySquare studyStairway = new StairwaySquare(new Room(Room.Rooms.Study), counterX, counterY);
                    counterX++;
                    break;
                case 'Z' :
                    StairwaySquare libraryStairway = new StairwaySquare(new Room(Room.Rooms.Library), counterX, counterY);
                    counterX++;
                    break;
                default:
                    System.out.println("File not read properly");
            }
        }
    }


    public static void main(String[] args) {
        try {
            handleFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


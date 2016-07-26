package model;

import model.Squares.*;

import java.io.*;

public class Board {
    private static File file = new File("data/board.txt");

    private static Square[][] board = new Square[25][25];

    private static void handleFile()
            throws IOException {
        try (InputStream in = new FileInputStream(file);
             Reader reader = new InputStreamReader(in);
             // buffer for efficiency
             Reader buffer = new BufferedReader(reader)) {
            handleCharacters(buffer);
        }
    }

    private static void handleCharacters(Reader reader)
            throws IOException {
        int r;
        for(int x=0; x<25;x++) {
            for(int y=0; y<25; y++) {
        while ((r = reader.read()) != -1) {
            char ch = (char) r;
                    switch (ch) {
                        case 'N':
                            board[x][y] = new NullSquare(x,y);
                            break;
                        case 'B':
                            BlankSquare blankSquare = new BlankSquare(x, y);
                            board[x][y] = blankSquare;
                            break;

                        case 'K':
                            RoomSquare kitchen = new RoomSquare(new Room(Room.Rooms.Kitchen), x, y);
                            board[x][y] = kitchen;
                            break;
                        case 'A':
                            RoomSquare ballroom = new RoomSquare(new Room(Room.Rooms.BallRoom), x, y);
                            board[x][y] = ballroom;
                            break;
                        case 'C':
                            RoomSquare conservatory = new RoomSquare(new Room(Room.Rooms.Conservatory), x, y);
                            board[x][y] = conservatory;
                            break;
                        case 'D':
                            RoomSquare diningRoom = new RoomSquare(new Room(Room.Rooms.DiningRoom), x, y);
                            board[x][y] = diningRoom;
                            break;
                        case 'I':
                            RoomSquare billiardRoom = new RoomSquare(new Room(Room.Rooms.BilliardRoom), x, y);
                            board[x][y] = billiardRoom;
                            break;
                        case 'L':
                            RoomSquare library = new RoomSquare(new Room(Room.Rooms.Library), x, y);
                            board[x][y] = library;
                            break;
                        case 'S':
                            RoomSquare study = new RoomSquare(new Room(Room.Rooms.Study), x, y);
                            board[x][y] = study;
                            break;
                        case 'H':
                            RoomSquare hall = new RoomSquare(new Room(Room.Rooms.Hall), x, y);
                            board[x][y] = hall;
                            break;
                        case 'O':
                            RoomSquare lounge = new RoomSquare(new Room(Room.Rooms.Lounge), x, y);
                            board[x][y] = lounge;
                            break;
                        case 'k':
                            DoorSquare kExit = new DoorSquare(new Room(Room.Rooms.Kitchen), x, y);
                            board[x][y] = kExit;
                            break;
                        case 'a':
                            DoorSquare aExit = new DoorSquare(new Room(Room.Rooms.BallRoom), x, y);
                            board[x][y] = aExit;
                            break;
                        case 'c':
                            DoorSquare cExit = new DoorSquare(new Room(Room.Rooms.Conservatory), x, y);
                            board[x][y] = cExit;
                            break;
                        case 'd':
                            DoorSquare dExit = new DoorSquare(new Room(Room.Rooms.DiningRoom), x, y);
                            board[x][y] = dExit;
                            break;
                        case 'i':
                            DoorSquare iExit = new DoorSquare(new Room(Room.Rooms.BilliardRoom), x, y);
                            board[x][y] = iExit;
                            break;
                        case 'l':
                            DoorSquare lExit = new DoorSquare(new Room(Room.Rooms.Library), x, y);
                            board[x][y] = lExit;
                            break;
                        case 's':
                            DoorSquare sExit = new DoorSquare(new Room(Room.Rooms.Study), x, y);
                            board[x][y] = sExit;
                            break;
                        case 'h':
                            DoorSquare hExit = new DoorSquare(new Room(Room.Rooms.Hall), x, y);
                            board[x][y] = hExit;
                            break;
                        case 'o':
                            DoorSquare oExit = new DoorSquare(new Room(Room.Rooms.Lounge), x, y);
                            board[x][y] = oExit;
                            break;
                        case '1':
                            StartSquare mrsWhite = new StartSquare(Character.Characters.MrsWhite, x, y);
                            board[x][y] = mrsWhite;
                            break;
                        case '2':
                            StartSquare revGreen = new StartSquare(Character.Characters.ReverendGreen, x, y);
                            board[x][y] = revGreen;
                            break;
                        case '3':
                            StartSquare peacock = new StartSquare(Character.Characters.MrsPeacock, x, y);
                            board[x][y] = peacock;
                            break;
                        case '4':
                            StartSquare plum = new StartSquare(Character.Characters.ProfessorPlum, x, y);
                            board[x][y] = plum;
                            break;
                        case '5':
                            StartSquare scarlett = new StartSquare(Character.Characters.MissScarlett, x, y);
                            board[x][y] = scarlett;
                            break;
                        case '6':
                            StartSquare mustard = new StartSquare(Character.Characters.ColonelMustard, x, y);
                            board[x][y] = mustard;
                            break;
                        case 'W':
                            StairwaySquare kitchenStairway = new StairwaySquare(new Room(Room.Rooms.Kitchen), x, y);
                            board[x][y] = kitchenStairway;
                            break;
                        case 'X':
                            StairwaySquare conservStairway = new StairwaySquare(new Room(Room.Rooms.Conservatory), x, y);
                            board[x][y] = conservStairway;
                            break;
                        case 'Y':
                            StairwaySquare studyStairway = new StairwaySquare(new Room(Room.Rooms.Study), x, y);
                            board[x][y] = studyStairway;
                            break;
                        case 'Z':
                            StairwaySquare libraryStairway = new StairwaySquare(new Room(Room.Rooms.Library), x, y);
                            board[x][y] = libraryStairway;
                            break;
                        case '\n':
                            break;
                        default:
                            System.out.println("File not read properly");
                    }
                }
            }

        }

    }

    public static void print() {
        for(int x=0; x<25; x++) {
            for(int y=0; y<25; y++) {

            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        try {
            handleFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        print();
    }

}


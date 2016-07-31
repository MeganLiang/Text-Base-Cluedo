package model;

import model.Squares.*;

import java.awt.*;
import java.io.*;

public class Board {
    private File file = new File("data/board.txt");
    private final int WIDTH = 24;
    private final int HEIGHT = 25;
    private Square[][] board = new Square[WIDTH][HEIGHT];

    public Board() {
        try {
            handleFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void handleFile() throws IOException {
        try (InputStream in = new FileInputStream(file);
             Reader reader = new InputStreamReader(in);
             // buffer for efficiency
             Reader buffer = new BufferedReader(reader)) {
            handleCharacters(buffer);
        }
    }

    private void handleCharacters(Reader reader) throws IOException {
        int r;
        int x=0;
        int y=0;

        while ((r = reader.read()) != -1) {

            char ch = (char) r;
            // System.out.println(ch);

            switch (ch) {
                case 'N':
                    board[x][y] = new NullSquare(x, y);
                    //  System.out.println("N " + x +" "+ y);
                    x++;
                    break;
                case 'B':
                    BlankSquare blankSquare = new BlankSquare(x, y);
                    board[x][y] = blankSquare;
                    x++;
                    break;

                case 'K':
                    RoomSquare kitchen = new RoomSquare(new Room(Room.Rooms.Kitchen), x, y);
                    board[x][y] = kitchen;
                    x++;
                    break;
                case 'A':
                    RoomSquare ballroom = new RoomSquare(new Room(Room.Rooms.BallRoom), x, y);
                    board[x][y] = ballroom;
                    x++;
                    break;
                case 'C':
                    RoomSquare conservatory = new RoomSquare(new Room(Room.Rooms.Conservatory), x, y);
                    board[x][y] = conservatory;
                    x++;
                    break;
                case 'D':
                    RoomSquare diningRoom = new RoomSquare(new Room(Room.Rooms.DiningRoom), x, y);
                    board[x][y] = diningRoom;
                    x++;
                    break;
                case 'I':
                    RoomSquare billiardRoom = new RoomSquare(new Room(Room.Rooms.BilliardRoom), x, y);
                    board[x][y] = billiardRoom;
                    //System.out.println("x " + x + "y " + y);
                    x++;
                    break;
                case 'L':
                    RoomSquare library = new RoomSquare(new Room(Room.Rooms.Library), x, y);
                    board[x][y] = library;
                    x++;
                    break;
                case 'S':
                    RoomSquare study = new RoomSquare(new Room(Room.Rooms.Study), x, y);
                    board[x][y] = study;
                    x++;
                    break;
                case 'H':
                    RoomSquare hall = new RoomSquare(new Room(Room.Rooms.Hall), x, y);
                    board[x][y] = hall;
                    x++;
                    break;
                case 'O':
                    RoomSquare lounge = new RoomSquare(new Room(Room.Rooms.Lounge), x, y);
                    board[x][y] = lounge;
                    x++;
                    break;
                case 'k':
                    DoorSquare kExit = new DoorSquare(new Room(Room.Rooms.Kitchen), x, y);
                    board[x][y] = kExit;
                    x++;
                    break;
                case 'a':
                    DoorSquare aExit = new DoorSquare(new Room(Room.Rooms.BallRoom), x, y);
                    board[x][y] = aExit;
                    x++;
                    break;
                case 'c':
                    DoorSquare cExit = new DoorSquare(new Room(Room.Rooms.Conservatory), x, y);
                    board[x][y] = cExit;
                    x++;
                    break;
                case 'd':
                    DoorSquare dExit = new DoorSquare(new Room(Room.Rooms.DiningRoom), x, y);
                    board[x][y] = dExit;
                    x++;
                    break;
                case 'i':
                    DoorSquare iExit = new DoorSquare(new Room(Room.Rooms.BilliardRoom), x, y);
                    board[x][y] = iExit;
                    //System.out.println("i");
                    x++;
                    break;
                case 'l':
                    DoorSquare lExit = new DoorSquare(new Room(Room.Rooms.Library), x, y);
                    board[x][y] = lExit;
                    x++;
                    break;
                case 's':
                    DoorSquare sExit = new DoorSquare(new Room(Room.Rooms.Study), x, y);
                    board[x][y] = sExit;
                    x++;
                    break;
                case 'h':
                    DoorSquare hExit = new DoorSquare(new Room(Room.Rooms.Hall), x, y);
                    board[x][y] = hExit;
                    x++;
                    break;
                case 'o':
                    DoorSquare oExit = new DoorSquare(new Room(Room.Rooms.Lounge), x, y);
                    board[x][y] = oExit;
                    x++;
                    break;
                case '1':
                    StartSquare mrsWhite = new StartSquare(Character.Characters.MrsWhite, x, y);
                    board[x][y] = mrsWhite;
                    x++;
                    break;
                case '2':
                    StartSquare revGreen = new StartSquare(Character.Characters.ReverendGreen, x, y);
                    board[x][y] = revGreen;
                    x++;
                    break;
                case '3':
                    StartSquare peacock = new StartSquare(Character.Characters.MrsPeacock, x, y);
                    board[x][y] = peacock;
                    x++;
                    break;
                case '4':
                    StartSquare plum = new StartSquare(Character.Characters.ProfessorPlum, x, y);
                    board[x][y] = plum;
                    x++;
                    break;
                case '5':
                    StartSquare scarlett = new StartSquare(Character.Characters.MissScarlett, x, y);
                    board[x][y] = scarlett;
                    x++;
                    break;
                case '6':
                    StartSquare mustard = new StartSquare(Character.Characters.ColonelMustard, x, y);
                    board[x][y] = mustard;
                    x++;
                    break;
                case 'W':
                    StairwaySquare kitchenStairway = new StairwaySquare(new Room(Room.Rooms.Kitchen), x, y);
                    board[x][y] = kitchenStairway;
                    x++;
                    break;
                case 'X':
                    StairwaySquare conservStairway = new StairwaySquare(new Room(Room.Rooms.Conservatory), x, y);
                    board[x][y] = conservStairway;
                    x++;
                    break;
                case 'Y':
                    StairwaySquare studyStairway = new StairwaySquare(new Room(Room.Rooms.Study), x, y);
                    board[x][y] = studyStairway;
                    x++;
                    break;
                case 'Z':
                    StairwaySquare libraryStairway = new StairwaySquare(new Room(Room.Rooms.Library), x, y);
                    board[x][y] = libraryStairway;
                    x++;
                    break;
                case '\n':
                    x = 0;
                    y++;
                    break;
                default:
                    System.out.println("File not read properly");
            }
        }

    }

    public static void main(String[] args) {
        new Board();
    }

    public Square returnSquare(Point p){
        return board[(int)p.getX()][(int)p.getY()];
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public Square[][] getBoard() {
        return board;
    }
}


package view;

import controller.Game;
import model.Character;
import model.Player;
import model.Room;
import model.Squares.*;
import model.Weapon;

import java.awt.*;
import java.io.*;

public class PaintBoard {
    private File file = new File("data/board.txt");
    private final int WIDTH = 24;
    private final int HEIGHT = 25;
    private String[][] cluedoBoard = new String[WIDTH][HEIGHT];

    public PaintBoard() {
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
            readBoard(buffer);
        }
    }

    private void readBoard(Reader reader) throws IOException {
        int r;
        int x=0;
        int y=0;

        while ((r = reader.read()) != -1) {

            char ch = (char) r;

            switch (ch) {
                case 'N':
                    cluedoBoard[x][y] = "|#";
                    x++;
                    break;
                case 'B':
                    cluedoBoard[x][y] = "|_";
                    x++;
                    break;

                case 'K':
                    cluedoBoard[x][y] = "|K";
                    x++;
                    break;
                case 'A':
                    cluedoBoard[x][y] = "|A";
                    x++;
                    break;
                case 'C':
                    cluedoBoard[x][y] = "|C";
                    x++;
                    break;
                case 'D':
                    cluedoBoard[x][y] = "|D";
                    x++;
                    break;
                case 'I':
                    cluedoBoard[x][y] = "|I";
                    x++;
                    break;
                case 'L':
                    cluedoBoard[x][y] = "|L";
                    x++;
                    break;
                case 'S':
                    cluedoBoard[x][y] = "|S";
                    x++;
                    break;
                case 'H':
                    cluedoBoard[x][y] = "|H";
                    x++;
                    break;
                case 'O':
                    cluedoBoard[x][y] = "|O";
                    x++;
                    break;
                case 'k':
                    cluedoBoard[x][y] = "|k";
                    x++;
                    break;
                case 'a':
                    cluedoBoard[x][y] = "|a";
                    x++;
                    break;
                case 'c':
                    cluedoBoard[x][y] = "|c";
                    x++;
                    break;
                case 'd':
                    cluedoBoard[x][y] = "|d";
                    x++;
                    break;
                case 'i':
                    cluedoBoard[x][y] = "|i";
                    x++;
                    break;
                case 'l':
                    cluedoBoard[x][y] = "|l";
                    x++;
                    break;
                case 's':
                    cluedoBoard[x][y] = "|s";
                    x++;
                    break;
                case 'h':
                    cluedoBoard[x][y] = "|h";
                    x++;
                    break;
                case 'o':
                    cluedoBoard[x][y] = "|o";
                    x++;
                    break;
                case '1':
                    cluedoBoard[x][y] = "|_";
                    x++;
                    break;
                case '2':
                    cluedoBoard[x][y] = "|_";
                    x++;
                    break;
                case '3':
                    cluedoBoard[x][y] = "|_";
                    x++;
                    break;
                case '4':
                    cluedoBoard[x][y] = "|_";
                    x++;
                    break;
                case '5':
                    cluedoBoard[x][y] = "|_";
                    x++;
                    break;
                case '6':
                    cluedoBoard[x][y] = "|_";
                    x++;
                    break;
                case 'W':
                    cluedoBoard[x][y] = "|=";
                    x++;
                    break;
                case 'X':
                    cluedoBoard[x][y] = "|=";
                    x++;
                    break;
                case 'Y':
                    cluedoBoard[x][y] = "|=";
                    x++;
                    break;
                case 'Z':
                    cluedoBoard[x][y] = "|=";
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

    public void paintBoard() {

        for(int y=0; y<HEIGHT; y++) {
            for(int x=0; x<WIDTH; x++) {
                System.out.print(cluedoBoard[x][y]);
            }
            System.out.println();
        }
        System.out.println();
    }
    public void updateArray( Player player, Game cluedo, Weapon weapon) {
        if (weapon != null) {
            for(int y=0; y<HEIGHT; y++) {
                for (int x = 0; x < WIDTH; x++) {
                    Room roomPosition = weapon.getInRoom();
                    if (cluedo.getBoard().getBoard()[x][y] instanceof RoomSquare) { // it is a room, render weapons
                        RoomSquare roomSquare = (RoomSquare) cluedo.getBoard().getBoard()[x][y];
                        Room room = roomSquare.getRoom();
                        if (roomPosition.getName().equals(room.getName())) {
                            cluedoBoard[x][y] = weapon.WeaponSymbol(weapon.getEnum());
                            //paintBoard();
                            return;
                        }
                    }
                }
            }
            //phantom weapons, erase weapon at previous location

        }

        if(player != null) {
            for(int y=0; y<HEIGHT; y++) {
                for(int x=0; x<WIDTH; x++) {

                    Point playerPosition = player.getPositionPoint();
                    Point playerPrev = player.getPreviousPoint();
                    if (playerPosition.getY() == y && playerPosition.getX() == x) {
                        cluedoBoard[x][y] = "|" + player.getCharacter().ordinal();
                    }
                    if (playerPrev != null) {
                        if (playerPrev.getX() == x && playerPrev.getY() == y) { //fix the previous square to its original state
                            if (cluedo.getBoard().getBoard()[(int) playerPrev.getX()][(int) playerPrev.getY()] instanceof RoomSquare) {
                                RoomSquare room = (RoomSquare) cluedo.getBoard().getBoard()[(int) playerPrev.getX()][(int) playerPrev.getY()];
                                if (room.getRoom().getName().equals("Kitchen")) {
                                    cluedoBoard[x][y] = "|K";
                                } else if (room.getRoom().getName().equals("BallRoom")) {
                                    cluedoBoard[x][y] = "|A";
                                } else if (room.getRoom().getName().equals("Conservatory")) {
                                    cluedoBoard[x][y] = "|C";
                                } else if (room.getRoom().getName().equals("DiningRoom")) {
                                    cluedoBoard[x][y] = "|D";
                                } else if (room.getRoom().getName().equals("BilliardRoom")) {
                                    cluedoBoard[x][y] = "|I";
                                } else if (room.getRoom().getName().equals("Library")) {
                                    cluedoBoard[x][y] = "|L";
                                } else if (room.getRoom().getName().equals("Lounge")) {
                                    cluedoBoard[x][y] = "|O";
                                } else if (room.getRoom().getName().equals("Hall")) {
                                    cluedoBoard[x][y] = "|H";
                                } else if (room.getRoom().getName().equals("Study")) {
                                    cluedoBoard[x][y] = "|S";
                                }
                            } else if (cluedo.getBoard().getBoard()[(int) playerPrev.getX()][(int) playerPrev.getY()] instanceof DoorSquare) {
                                DoorSquare door = (DoorSquare) cluedo.getBoard().getBoard()[(int) playerPrev.getX()][(int) playerPrev.getY()];
                                if (door.getRoom().getName().equals("Kitchen")) {
                                    cluedoBoard[x][y] = "|k";
                                } else if (door.getRoom().getName().equals("BallRoom")) {
                                    cluedoBoard[x][y] = "|a";
                                } else if (door.getRoom().getName().equals("Conservatory")) {
                                    cluedoBoard[x][y] = "|c";
                                } else if (door.getRoom().getName().equals("DiningRoom")) {
                                    cluedoBoard[x][y] = "|d";
                                } else if (door.getRoom().getName().equals("BilliardRoom")) {
                                    cluedoBoard[x][y] = "|i";
                                } else if (door.getRoom().getName().equals("Library")) {
                                    cluedoBoard[x][y] = "|l";
                                } else if (door.getRoom().getName().equals("Lounge")) {
                                    cluedoBoard[x][y] = "|o";
                                } else if (door.getRoom().getName().equals("Hall")) {
                                    cluedoBoard[x][y] = "|h";
                                } else if (door.getRoom().getName().equals("Study")) {
                                    cluedoBoard[x][y] = "|s";
                                }
                            } else if (cluedo.getBoard().getBoard()[(int) playerPrev.getX()][(int) playerPrev.getY()] instanceof BlankSquare
                                    || cluedo.getBoard().getBoard()[(int) playerPrev.getX()][(int) playerPrev.getY()] instanceof StartSquare) {
                                cluedoBoard[x][y] = "|_";
                            } else if (cluedo.getBoard().getBoard()[(int) playerPrev.getX()][(int) playerPrev.getY()] instanceof StairwaySquare) {
                                cluedoBoard[x][y] = "|=";
                            }
                        }
                    }
                }
            }
        }
    }
    public void repaint(Player player, Game cluedo, Weapon weapon) {
        paintBoard();
    }
    public static void main(String [] args) {
        Game cluedo = new Game();
        PaintBoard p = new PaintBoard();
//        p.paintBoard();
        Player megan = new Player("Megan");
        megan.setCharacter(Character.Characters.MrsPeacock);
        megan.setPreviousPoint(null);
        megan.setPositionPoint(new Point(7,0));
        Weapon weapon = new Weapon(Weapon.Weapons.CandleStick);
        weapon.setInRoom(new Room(Room.Rooms.BallRoom));
        p.repaint(null, cluedo, weapon);
        weapon.setInRoom(new Room(Room.Rooms.Kitchen));
        p.repaint(null, cluedo, weapon);

    }
}

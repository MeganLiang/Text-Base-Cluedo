package view;

import model.Character;
import model.Room;
import model.Squares.*;

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
            // System.out.println(ch);

            switch (ch) {
                case 'N':
                    cluedoBoard[x][y] = "|#";
                    //  System.out.println("N " + x +" "+ y);
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

    public void printBoard() {

        for(int x=0; x<HEIGHT; x++) {
            for(int y=0; y<WIDTH; y++) {
                System.out.print(cluedoBoard[y][x]);
            }
            System.out.println();
        }
    }
    public static void main(String [] args) {
        PaintBoard p = new PaintBoard();
        p.printBoard();
    }
}

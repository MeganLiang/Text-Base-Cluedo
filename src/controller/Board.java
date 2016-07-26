package controller;

import model.*;

/**
 * Created by Tristan on 24/07/2016.
 */
//public class Board {
//    int x = 25;
//    int y = 24;
//    Square[][] board = new Square[x][y];
//
//    public Square[][] getBoard() {
//        return board;
//    }
//
//    public int getX() {
//        return x;
//    }
//
//    public int getY() {
//        return y;
//    }
//
//    public Board(){
//
//        //Fill the board with normal squares
//        for(int x = 0; x > 25; x++){
//            for(int y = 0; y > 24; y++){
//                board[x][y] = new BlankSquare();
//            }
//        }
//        /*
//        Correctly filll in the board
//        BlankEdges bottom
//        */
//
//        board[0][0] = null;
//        board[0][1] = null;
//        board[0][2] = null;
//        board[0][3] = null;
//        board[0][4] = null;
//        board[0][5] = null;
//        board[0][6] = null;
//        board[0][8] = null;
//        board[0][9] = null;
//        board[0][10] = null;
//        board[0][11] = null;
//        board[0][12] = null;
//        board[0][13] = null;
//        board[0][14] = null;
//        board[0][15] = null;
//        board[0][17] = null;
//        board[0][18] = null;
//        board[0][19] = null;
//        board[0][20] = null;
//        board[0][21] = null;
//        board[0][22] = null;
//        board[0][23] = null;
//        //Blank edges Left
//        board[24][0] = null;
//        board[24][1] = null;
//        board[24][2] = null;
//        board[24][3] = null;
//        board[24][4] = null;
//        board[24][5] = null;
//        board[24][6] = null;
//        board[24][8] = null;
//        board[24][7] = null;
//        board[24][10] = null;
//        board[24][11] = null;
//        board[24][12] = null;
//        board[24][13] = null;
//        board[24][14] = null;
//        board[24][15] = null;
//        board[24][17] = null;
//        board[24][18] = null;
//        board[24][16] = null;
//        board[24][20] = null;
//        board[24][21] = null;
//        board[24][22] = null;
//        board[24][23] = null;
//        //Blandedges right
//        board[4][23] = null;
//        board[10][23] = null;
//        board[11][23] = null;
//        board[24][23] = null;
//        board[17][23] = null;
//        board[19][23] = null;
//        board[24][23] = null;
//        //Blade Edges Left
//        board[6][0] = null;
//        board[8][0] = null;
//        board[16][0] = null;
//        board[18][0] = null;
//        board[24][0] = null;
//        //Lounge
//        board[0][0] = null;
//        board[0][1] = null;
//        board[0][2] = null;
//        board[0][3] = null;
//        board[0][4] = null;
//        board[0][5] = null;
//        board[1][0] = null;
//        board[1][1] = null;
//        board[1][2] = null;
//        board[1][3] = null;
//        board[1][4] = null;
//        board[1][5] = null;
//        board[1][6] = null;
//        board[2][0] = null;
//        board[2][1] = null;
//        board[2][2] = null;
//        board[2][3] = null;
//        board[2][4] = null;
//        board[2][5] = null;
//        board[2][6] = null;
//        board[3][0] = null;
//        board[3][1] = null;
//        board[3][2] = null;
//        board[3][3] = null;
//        board[3][4] = null;
//        board[3][5] = null;
//        board[3][6] = null;
//        board[4][0] = null;
//        board[4][1] = null;
//        board[4][2] = null;
//        board[4][3] = null;
//        board[4][4] = null;
//        board[4][5] = null;
//        board[4][6] = null;
//        board[5][0] = null;
//        board[5][1] = null;
//        board[5][2] = null;
//        board[5][3] = null;
//        board[5][4] = null;
//        board[5][5] = null;
//        board[5][6] = new RoomSquare(new Room(Room.Rooms.Lounge));
//        board[6][6] = new DoorSquare(new Room(Room.Rooms.Lounge),"North");
//
//        //Hall
//        RoomSquare Hall = new RoomSquare(new Room(Room.Rooms.Hall));
//        board[6][9] = null;
//        board[6][10] = null;
//        board[6][11] = Hall;
//        board[6][12] = Hall;
//        board[6][13] = null;
//        board[6][14] = null;
//        board[5][9] = null;
//        board[5][10] = null;
//        board[5][11] = null;
//        board[5][12] = null;
//        board[5][13] = null;
//        board[5][14] = null;
//        board[4][9] = null;
//        board[4][10] = null;
//        board[4][11] = null;
//        board[4][12] = null;
//        board[4][13] = null;
//        board[4][14] = Hall;
//        board[3][9] = null;
//        board[3][10] = null;
//        board[3][11] = null;
//        board[3][12] = null;
//        board[3][13] = null;
//        board[3][14] = null;
//        board[2][9] = null;
//        board[2][10] = null;
//        board[2][11] = null;
//        board[2][12] = null;
//        board[2][13] = null;
//        board[2][14] = null;
//        board[1][9] = null;
//        board[1][10] = null;
//        board[1][11] = null;
//        board[1][12] = null;
//        board[1][13] = null;
//        board[1][14] = null;
//        board[0][9] = null;
//        board[0][10] = null;
//        board[0][11] = null;
//        board[0][12] = null;
//        board[0][13] = null;
//        board[0][14] = null;
//
//        board[7][11] = new DoorSquare(new Room(Room.Rooms.Hall),"NorthL");
//        board[7][12] = new DoorSquare(new Room(Room.Rooms.Hall),"NorthR");
//        board[4][15] = new DoorSquare(new Room(Room.Rooms.Hall),"East");
//
//        //Study
//        board[0][17] = null;
//        board[0][18] = null;
//        board[0][19] = null;
//        board[0][20] = null;
//        board[0][21] = null;
//        board[0][22] = null;
//        board[0][23] = null;
//
//        board[1][17] = null;
//        board[1][18] = null;
//        board[1][19] = null;
//        board[1][20] = null;
//        board[1][21] = null;
//        board[1][22] = null;
//        board[1][23] = null;
//
//        board[2][17] = null;
//        board[2][18] = null;
//        board[2][19] = null;
//        board[2][20] = null;
//        board[2][21] = null;
//        board[2][22] = null;
//        board[2][23] = null;
//
//        board[3][17] = new RoomSquare(new Room(Room.Rooms.Study));
//        board[3][18] = null;
//        board[3][19] = null;
//        board[3][20] = null;
//        board[3][21] = null;
//        board[3][22] = null;
//        board[3][23] = null;
//
//        board[4][17] = new DoorSquare(new Room(Room.Rooms.Study),"North");
//
//        //Library
//        RoomSquare library = new RoomSquare(new Room(Room.Rooms.Library));
//        board[6][18] = null;
//        board[6][19] = null;
//        board[6][20] = null;
//        board[6][21] = null;
//        board[6][22] = null;
//        board[6][23] = null;
//
//        board[7][17] = null;
//        board[7][18] = null;
//        board[7][19] = null;
//        board[7][20] = null;
//        board[7][21] = null;
//        board[7][22] = null;
//        board[7][23] = null;
//
//        board[8][16] = new DoorSquare(new Room(Room.Rooms.Library),"East");
//        board[8][17] = library;
//        board[8][18] = null;
//        board[8][19] = null;
//        board[8][20] = null;
//        board[8][21] = null;
//        board[8][22] = null;
//        board[8][23] = null;
//
//        board[9][17] = null;
//        board[9][18] = null;
//        board[9][19] = null;
//        board[9][20] = null;
//        board[9][21] = null;
//        board[9][22] = null;
//        board[9][23] = null;
//
//        board[10][18] = null;
//        board[10][19] = null;
//        board[10][20] = library;
//        board[11][20] = new DoorSquare(new Room(Room.Rooms.Library),"North");
//        board[10][21] = null;
//        board[10][22] = null;
//        board[10][23] = null;
//
//        //Billiard Room
//        RoomSquare br = new RoomSquare(new Room(Room.Rooms.BilliardRoom));
//
//        board[12][18] = null;
//        board[12][19] = null;
//        board[12][20] = null;
//        board[12][21] = null;
//        board[12][22] = br;
//        board[11][22] = new DoorSquare(new Room(Room.Rooms.BilliardRoom),"South");
//        board[12][23] = null;
//
//        board[13][18] = null;
//        board[13][19] = null;
//        board[13][20] = null;
//        board[13][21] = null;
//        board[13][22] = null;
//        board[13][23] = null;
//
//        board[14][18] = null;
//        board[14][19] = null;
//        board[14][20] = null;
//        board[14][21] = null;
//        board[14][22] = null;
//        board[14][23] = null;
//
//        board[15][17] = new DoorSquare(new Room(Room.Rooms.BilliardRoom),"West");
//        board[15][18] = br;
//        board[15][19] = null;
//        board[15][20] = null;
//        board[15][21] = null;
//        board[15][22] = null;
//        board[15][23] = null;
//
//        board[16][18] = null;
//        board[16][19] = null;
//        board[16][20] = null;
//        board[16][21] = null;
//        board[16][22] = null;
//        board[16][23] = null;
//
//        //Conservatory
//        board[19][19] = null;
//        board[19][20] = null;
//        board[19][21] = null;
//        board[19][22] = null;
//        board[19][23] = null;
//
//        board[19][18] = new DoorSquare(new Room(Room.Rooms.Conservatory),"South");
//        board[20][18] = new RoomSquare(new Room(Room.Rooms.Conservatory));
//        board[20][19] = null;
//        board[20][20] = null;
//        board[20][21] = null;
//        board[20][22] = null;
//        board[20][23] = null;
//
//        board[21][18] = null;
//        board[21][19] = null;
//        board[21][20] = null;
//        board[21][21] = null;
//        board[21][22] = null;
//        board[21][23] = null;
//
//        board[22][18] = null;
//        board[22][19] = null;
//        board[22][20] = null;
//        board[22][21] = null;
//        board[22][22] = null;
//        board[22][23] = null;
//
//        board[23][18] = null;
//        board[23][19] = null;
//        board[23][20] = null;
//        board[23][21] = null;
//        board[23][22] = null;
//        board[23][23] = null;
//
//        board[23][18] = null;
//        board[23][19] = null;
//        board[23][20] = null;
//        board[23][21] = null;
//        board[23][22] = null;
//        board[23][23] = null;
//
//        //BallRoom
//        RoomSquare ballroom = new RoomSquare(new Room(Room.Rooms.BallRoom));
//        board[17][8] = null;
//        board[17][9] = ballroom;
//        board[16][9] = new DoorSquare(new Room(Room.Rooms.BallRoom),"SouthL");
//        board[17][10] = null;
//        board[17][11] = null;
//        board[17][12] = null;
//        board[17][13] = null;
//        board[17][14] = ballroom;
//        board[16][14] = new DoorSquare(new Room(Room.Rooms.BallRoom),"SouthR");
//        board[17][15] = null;
//
//        board[18][8] = null;
//        board[18][9] = null;
//        board[18][10] = null;
//        board[18][11] = null;
//        board[18][12] = null;
//        board[18][13] = null;
//        board[18][14] = null;
//        board[18][15] = null;
//
//        board[19][7] = new DoorSquare(new Room(Room.Rooms.BallRoom),"West");
//        board[19][8] = ballroom;
//        board[19][9] = null;
//        board[19][10] = null;
//        board[19][11] = null;
//        board[19][12] = null;
//        board[19][13] = null;
//        board[19][14] = null;
//        board[19][15] = ballroom;
//        board[19][16] = new DoorSquare(new Room(Room.Rooms.BallRoom),"East");
//
//        board[20][8] = null;
//        board[20][9] = null;
//        board[20][10] = null;
//        board[20][11] = null;
//        board[20][12] = null;
//        board[20][13] = null;
//        board[20][14] = null;
//        board[20][15] = null;
//
//        board[21][8] = null;
//        board[21][9] = null;
//        board[21][10] = null;
//        board[21][11] = null;
//        board[21][12] = null;
//        board[21][13] = null;
//        board[21][14] = null;
//        board[21][15] = null;
//
//        board[22][8] = null;
//        board[22][9] = null;
//        board[22][10] = null;
//        board[22][11] = null;
//        board[22][12] = null;
//        board[22][13] = null;
//        board[22][14] = null;
//        board[22][15] = null;
//        board[22][15] = null;
//
//        board[23][10] = null;
//        board[23][11] = null;
//        board[23][12] = null;
//        board[23][13] = null;
//
//        board[24][10] = null;
//        board[24][11] = null;
//        board[24][12] = null;
//        board[24][13] = null;
//
//
//        //Kitchen
//        board[18][0] = null;
//        board[18][1] = null;
//        board[18][2] = null;
//        board[18][3] = null;
//        board[18][4] = new RoomSquare(new Room(Room.Rooms.Kitchen));
//        board[17][4] = new DoorSquare(new Room(Room.Rooms.Kitchen),"South");
//        board[18][5] = null;
//
//        board[19][0] = null;
//        board[19][1] = null;
//        board[19][2] = null;
//        board[19][3] = null;
//        board[19][4] = null;
//        board[19][5] = null;
//
//        board[20][0] = null;
//        board[20][1] = null;
//        board[20][2] = null;
//        board[20][3] = null;
//        board[20][4] = null;
//        board[20][5] = null;
//
//        board[21][0] = null;
//        board[21][1] = null;
//        board[21][2] = null;
//        board[21][3] = null;
//        board[21][4] = null;
//        board[21][5] = null;
//
//        board[22][0] = null;
//        board[22][1] = null;
//        board[22][2] = null;
//        board[22][3] = null;
//        board[22][4] = null;
//        board[22][5] = null;
//
//        board[23][0] = null;
//        board[23][1] = null;
//        board[23][2] = null;
//        board[23][3] = null;
//        board[23][4] = null;
//        board[23][5] = null;
//
//        board[24][0] = null;
//        board[24][1] = null;
//        board[24][2] = null;
//        board[24][3] = null;
//        board[24][4] = null;
//        board[24][5] = null;
//
//
//        //Dining Room
//        RoomSquare dining = new RoomSquare(new Room(Room.Rooms.DiningRoom));
//
//        board[15][0] = null;
//        board[15][1] = null;
//        board[15][2] = null;
//        board[15][3] = null;
//        board[15][4] = null;
//
//        board[14][0] = null;
//        board[14][1] = null;
//        board[14][2] = null;
//        board[14][3] = null;
//        board[14][4] = null;
//        board[14][5] = null;
//        board[14][6] = null;
//        board[14][7] = null;
//
//        board[13][0] = null;
//        board[13][1] = null;
//        board[13][2] = null;
//        board[13][3] = null;
//        board[13][4] = null;
//        board[13][5] = null;
//        board[13][6] = null;
//        board[13][7] = null;
//
//        board[12][0] = null;
//        board[12][1] = null;
//        board[12][2] = null;
//        board[12][3] = null;
//        board[12][4] = null;
//        board[12][5] = null;
//        board[12][6] = null;
//        board[12][7] = dining;
//        board[12][8] = new DoorSquare(new Room(Room.Rooms.DiningRoom),"East");
//
//        board[11][0] = null;
//        board[11][1] = null;
//        board[11][2] = null;
//        board[11][3] = null;
//        board[11][4] = null;
//        board[11][5] = null;
//        board[11][6] = null;
//        board[11][7] = null;
//
//        board[10][0] = null;
//        board[10][1] = null;
//        board[10][2] = null;
//        board[10][3] = null;
//        board[10][4] = null;
//        board[10][5] = null;
//        board[10][6] = null;
//        board[10][7] = null;
//
//        board[9][0] = null;
//        board[9][1] = null;
//        board[9][2] = null;
//        board[9][3] = null;
//        board[9][4] = null;
//        board[9][5] = null;
//        board[9][6] = dining;
//        board[8][6] = new DoorSquare(new Room(Room.Rooms.DiningRoom),"South");
//        board[9][7] = null;
//
//        //CARD MIDDLE
//        board[8][10] = null;
//        board[8][11] = null;
//        board[8][12] = null;
//        board[8][13] = null;
//        board[8][14] = null;
//
//        board[9][10] = null;
//        board[9][11] = null;
//        board[9][12] = null;
//        board[9][13] = null;
//        board[9][14] = null;
//
//        board[10][10] = null;
//        board[10][11] = null;
//        board[10][12] = null;
//        board[10][13] = null;
//        board[10][14] = null;
//
//        board[11][10] = null;
//        board[11][11] = null;
//        board[11][12] = null;
//        board[11][13] = null;
//        board[11][14] = null;
//
//        board[12][10] = null;
//        board[12][11] = null;
//        board[12][12] = null;
//        board[12][13] = null;
//        board[12][14] = null;
//
//        board[13][10] = null;
//        board[13][11] = null;
//        board[13][12] = null;
//        board[13][13] = null;
//        board[13][14] = null;
//
//        board[14][10] = null;
//        board[14][11] = null;
//        board[14][12] = null;
//        board[14][13] = null;
//        board[14][14] = null;
//    }
//
//    public void Move(Player player, String dir){
////        if(dir = "U"){
////
////        }
//    }
//
//}

package model;

import model.Squares.RoomSquare;
import model.Squares.StartSquare;

import java.awt.*;
import java.util.ArrayList;

public class Player {
    private String name;
    private Character.Characters character;
    private Hand hand;
    private Point point;
    private boolean inRoom;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand(new ArrayList<>());
        this.inRoom = false;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Character.Characters getCharacter() {
        return character;
    }

    public void setCharacter(Character.Characters character) {
        this.character = character;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printHand() {
        System.out.println(this.name + ": ");
        for(Card c : this.hand.getCards()) {
            System.out.println(c.getName());
        }
    }

    public Point startingSquare(Character.Characters characterEnum) {
        Board board = new Board();
        for(int x=0; x<25; x++) {
            for(int y=0; y<25; y++) {
                if(board.board[x][y] instanceof StartSquare) {
                    StartSquare startSquare = (StartSquare) board.board[x][y];
                    if(startSquare.getCharacter().equals(characterEnum)) {
                        System.out.println("this is the starting square: " + startSquare.getxPos() + " " + startSquare.getYpos());
                        return new Point(startSquare.getxPos(), startSquare.getYpos());
                    }
                }
            }
        }
        return null;
    }
    public boolean isInRoom(Player player) {
        Board board = new Board();
        for(int x=0; x<25; x++) {
            for (int y = 0; y < 25; y++) {
                if (board.board[player.getPositionPoint().x][player.getPositionPoint().y] instanceof RoomSquare) {
                    inRoom = true;
                    return inRoom;
                }
            }
        }
        inRoom = false;
        return inRoom;
    }

    public void setInRoom(boolean inRoom) {
        this.inRoom = inRoom;
    }

    public Point getPositionPoint() {
        return point;
    }

    public void setPositionPoint(Point point) {
        this.point = point;
    }
}

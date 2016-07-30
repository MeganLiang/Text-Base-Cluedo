package model.Squares;

public abstract class Square {
    int xPos;
    int yPos;

    public Square(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public int getYpos() {
        return yPos;
    }


}



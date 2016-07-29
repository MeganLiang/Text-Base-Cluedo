package model.Squares;

/**
 * Created by Tristan on 24/07/2016.
 */
public abstract class Square {
    int xPos;
    int yPos;

    public Square(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void checkMove() {
    }

    public int getxPos() {
        return xPos;
    }

    public int getYpos() {
        return yPos;
    }


}



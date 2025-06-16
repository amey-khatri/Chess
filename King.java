import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Amey Khatri
 * King.java
 * Date Last Modified:
 * Class Description:
 **/


public class King extends Piece {

    public King (Colour colour, String ID, int firstX, int firstY) {
        super(colour, ID, firstX, firstY);
    }


    public String toString() {
        if (this.getColour() == Colour.BLACK) {
            return "♔";
        }
        return "♚";
    }

    public boolean possibleMove (int x, int y) {
        boolean canMove = false;
        int xDiff = (Math.abs(x - this.getX()));
        int yDiff = (Math.abs(y - this.getY()));

            //All possible king movements
        if (xDiff == 0 && yDiff == 1) {
            canMove = true;
        } else if (xDiff == 1 && yDiff == 0) {
            canMove = true;
        } else if (xDiff == 1 && yDiff == 1) {
            canMove = true;
        }


        return canMove;
    }



}

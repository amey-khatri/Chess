/**
 * Amey Khatri
 * Knight.java
 * Date Last Modified:
 * Class Description:
 **/

import java.util.Scanner;

public class Knight extends Piece {
    public Knight (Colour colour, String ID, int firstX, int firstY) {
        super(colour, ID, firstX, firstY);

    }

    public String toString() {
        if (this.getColour() == Colour.BLACK) {
            return "♘";
        }
        return "♞";
    }

    public boolean possibleMove (int x, int y) {
        int xDiff = Math.abs(x- this.getX());
        int yDiff = Math.abs(y - this.getY());

        if (xDiff == 1 && yDiff == 2) {
            return true;
        }

        if (xDiff == 2 && yDiff == 1) {
            return true;
        }


        return false;
    }
}

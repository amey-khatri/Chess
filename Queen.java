/**
 * Amey Khatri
 * Queen.java
 * Date Last Modified:
 * Class Description:
 **/

import java.util.Scanner;

public class Queen extends Piece {
    public Queen (Colour colour, String ID, int firstX, int firstY) {
        super(colour, ID, firstX, firstY);

    }

    public String toString() {
        if (this.getColour() == Colour.BLACK) {
            return "♕";
        }
        return "♛";
    }

    public boolean possibleMove (int x, int y) {
        if (!this.straightPathClear(this.getX(), this.getY(), x, y)) {
            return false;
        }
        return true;
    }
}

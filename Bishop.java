/**
 * Amey Khatri
 * Bishop.java
 * Date Last Modified:
 * Class Description:
 **/

import java.util.Scanner;

public class Bishop extends Piece {
    public Bishop (Colour colour, String ID, int firstX, int firstY) {
        super(colour, ID, firstX, firstY);

    }

    public String toString() {
        if (this.getColour() == Colour.BLACK) {
            return "♗";
        }
        return "♝";
    }

    public boolean possibleMove (int x, int y) {
        if (this.straightPathClear(this.getX(), this.getY(), x, y)) {
            return true;
        } //If straight path is not clear, returns false. if clear, continues other checks

        return false;
    }
}

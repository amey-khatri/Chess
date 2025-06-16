/**
 * Amey Khatri
 * Pawn.java
 * Date Last Modified:
 * Class Description:
 **/

import java.util.Scanner;

public class Pawn extends Piece {
    public Pawn (Colour colour, String ID, int firstX, int firstY) {
        super(colour, ID, firstX, firstY);

    }

    public String toString() {
        if (this.getColour() == Colour.BLACK) {
            return "♙";
        }
        return "♟";
    }

    public boolean possibleMove (int x, int y) {
        int yDiff = Math.abs(y - this.getY());
        int xDiff = Math.abs(x - this.getX());

        if (this.getColour() == Colour.WHITE) {
            if (y > this.getY()) {
                return false;
            }
        } else {
            if (y < this.getY()) {
                return false;
            }
        } //Ensures pawn cannot move backwards

        if (yDiff == 1 && xDiff == 1) {
            if (this.getColour() != Board.getPiece(x, y).getColour()) {
                return true;
            }
        } //To capture piece, checks if in diagonal,

        if (x != this.getX()) {
            return false;
        } //Ensures move is in same vertical column

        if (Math.abs(yDiff) == 2) {
            if (!this.getisfirstmove()) {
                return false;
            }
        } else if (Math.abs(yDiff) > 2) {
            return false;
        } //Checks if pawn can move 2 on first move, checks that pawn is not moving more than 1 space otherwise

        return true;
    }
}

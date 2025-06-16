/**
 * Amey Khatri
 * Piece.java
 * Date Last Modified:
 * Class Description:
 **/


public abstract class Piece {
    private Colour colour;
    private String ID;
    private int x, y;
    private boolean isfirstmove;

    private boolean check;

    public Piece (Colour colour, String ID, int firstY, int firstX) {
        this.colour = colour;
        this.ID = ID;
        this.x = firstX;
        this.y = firstY;
        this.isfirstmove = true;
        this.check = false;

        if (colour == Colour.BLACK) {
            Board.black.add(this);
        } else {
            Board.white.add(this);
        }

        Board.setPiece(firstX, firstY, this);
    } //Initilizes piece and places it on board

    public void setInCheck(boolean inCheck) {
        check = inCheck;
    }

    public boolean getInCheck() {
        return check;
    }

    void move(int x, int y, Piece p) {
        int prevX = p.getX();
        int prevY = p.getY();

        Board.setPiece(x, y, p);
        Board.setPiece(prevX, prevY, null);
    } //Executes movement of piece. Moves piece to new location and sets previous location null

    public String getID() {
        return this.ID;
    } //Gets piece ID

    public abstract boolean possibleMove (int x, int y); //Abstact, extends to piece classes to check is move is valid for each piece


    public boolean straightPathClear (int startX, int startY, int finalX, int finalY) {
        // Vertical path check
        if (startX == finalX) {
            int smallY = Math.min(startY, finalY);
            int bigY = Math.max(startY, finalY);
            for (int y = smallY + 1; y < bigY; y++) {  // Excludes both startY and finalY
                if (Board.board[y][startX] != null) {
                    return false; // Found an obstacle, path is not clear
                }
            }
            return true;
        }
        // Horizontal path check
        if (startY == finalY) {
            int smallX = Math.min(startX, finalX);
            int bigX = Math.max(startX, finalX);
            for (int x = smallX + 1; x < bigX; x++) {  // Excludes both startX and finalX
                if (Board.board[startY][x] != null) {
                    return false; // Found an obstacle, path is not clear
                }
            }
            return true;
        }

        //Diagonal path check
        if (Math.abs(finalX - startX) == Math.abs(finalY - startY)) {
            int stepX = (finalX > startX) ? 1 : -1;
            int stepY = (finalY > startY) ? 1 : -1;
            int x = startX + stepX;
            int y = startY + stepY;
            while (x != finalX && y != finalY) {
                if (Board.board[y][x] != null) {
                    return false; // Found an obstacle, path is not clear
                }
                x += stepX;
                y += stepY;
            }
            return true;
        }

        return false;
    } //Checks if straight path is clear for rook, bishop and queen movements. Ignores initial and final squares.

    public boolean isSameColour (int x, int y, Piece p) {
        Piece attemptedMove = Board.getPiece(x, y);
        return attemptedMove.getColour() == p.getColour();
    } //Returns true if pieces are the same colour


    public Colour getColour() {
        return this.colour;
    }

    public int getX() {
        return this.x;
    }

    void setX(int newX) {
        this.x = newX;
    }

    public int getY() {
        return this.y;
    }

    void setY(int newY) {
        this.y = newY;
    }

    public boolean getisfirstmove() {
        return this.isfirstmove;
    }

    void setIsfirstmove (boolean isfirstmove) {
        this.isfirstmove = isfirstmove;
    }
}

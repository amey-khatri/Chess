import java.util.ArrayList;

/**
 * Amey Khatri
 * Board.java
 * Date Last Modified:
 * Class Description:
 **/


public class Board {
    public static ArrayList<Piece> black = new ArrayList<Piece>();
    public static ArrayList<Piece> white = new ArrayList<Piece>();
    public static ArrayList<Piece> blackCaptured = new ArrayList<Piece>();
    public static ArrayList<Piece> whiteCaptured = new ArrayList<Piece>();

    static Piece[][] board = new Piece[8][8];

    static void printBoard() {
        System.out.println("      1     2     3     4     5     6     7     8    ");
        System.out.println("   +-----+-----+-----+-----+-----+-----+-----+-----+");

        for (int i = 0; i < 8; i++) {
            System.out.print(i + 1 + "  ");
            System.out.print("|  ");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print("   |  ");
                } else {
                    System.out.print(board[i][j] + "  |  ");
                }
            }

            System.out.print(i + 1);
            System.out.println();
            System.out.println("   +-----+-----+-----+-----+-----+-----+-----+-----+");
        }
        System.out.println("      1     2     3     4     5     6     7     8    ");
        System.out.println();
    } //Prints out chess board

    public static void setPiece(int x, int y, Piece piece) {
        if (piece != null) {
            piece.setX(x);
            piece.setY(y);
        }
        board[y][x] = piece;
    } //Sends piece object to game array

    public static void setBoard() {
        //White pieces
        new King(Colour.WHITE, "king", 7, 4);
        new Queen(Colour.WHITE, "queen", 7, 3);
        new Bishop(Colour.WHITE, "bishopQ", 7, 2);
        new Bishop(Colour.WHITE, "bishopK", 7, 5);
        new Knight(Colour.WHITE, "knightQ", 7, 1);
        new Knight(Colour.WHITE, "knightK", 7, 6);
        new Rook(Colour.WHITE, "rookQ", 7, 0);
        new Rook(Colour.WHITE, "rookK", 7, 7);

        new Pawn(Colour.WHITE, "pawn1", 6, 0);
        new Pawn(Colour.WHITE, "pawn2", 6, 1);
        new Pawn(Colour.WHITE, "pawn3", 6, 2);
        new Pawn(Colour.WHITE, "pawn4", 6, 3);
        new Pawn(Colour.WHITE, "pawn5", 6, 4);
        new Pawn(Colour.WHITE, "pawn6", 6, 5);
        new Pawn(Colour.WHITE, "pawn7", 6, 6);
        new Pawn(Colour.WHITE, "pawn8", 6, 7);

        //Black Pieces
        new King(Colour.BLACK, "king", 0, 4);
        new Queen(Colour.BLACK, "queen", 0, 3);
        new Bishop(Colour.BLACK, "bishopQ", 0, 2);
        new Bishop(Colour.BLACK, "bishopK", 0, 5);
        new Knight(Colour.BLACK, "knightQ", 0, 1);
        new Knight(Colour.BLACK, "knightK", 0, 6);
        new Rook(Colour.BLACK, "rookQ", 0, 0);
        new Rook(Colour.BLACK, "rookK", 0, 7);

        new Pawn(Colour.BLACK, "pawn1", 1, 0);
        new Pawn(Colour.BLACK, "pawn2", 1, 1);
        new Pawn(Colour.BLACK, "pawn3", 1, 2);
        new Pawn(Colour.BLACK, "pawn4", 1, 3);
        new Pawn(Colour.BLACK, "pawn5", 1, 4);
        new Pawn(Colour.BLACK, "pawn6", 1, 5);
        new Pawn(Colour.BLACK, "pawn7", 1, 6);
        new Pawn(Colour.BLACK, "pawn8", 1, 7);
    } //Sets all pieces on board to start game

    public static Piece getKing(Colour colour) {
        String ID = "king";
        if (colour == Colour.WHITE) {
            for (int i = 0; i < white.size(); i++) {
                Piece p = white.get(i);
                if (p.getID().equals(ID)) {
                    return p;
                }
            }
        } else {
            for (int i = 0; i < black.size(); i++) {
                Piece p = black.get(i);
                if (p.getID().equals(ID)) {
                    return p;
                }
            }
        }
        return null;
    } //Locates location of each king

    public static Piece getPiece(String[] userInput) {
        String ID = userInput[1];
        Colour colour = (userInput[0].equalsIgnoreCase("BLACK"))? Colour.BLACK : Colour.WHITE;

        if (colour == Colour.WHITE) {
            for (int i = 0; i < white.size(); i++) {
                Piece p = white.get(i);
                if (p.getID().equals(ID)) {
                    return p;
                }
            }
        } else {
            for (int i = 0; i < black.size(); i++) {
                Piece p = black.get(i);
                if (p.getID().equals(ID)) {
                    return p;
                }
            }
        }
        return null;
    } //Gets piece object given colour and ID - Used to find correct piece user is referring to when making move

    public static Piece getPiece(int x, int y) {
        return board[y][x];
    } //Returns piece at given coordinate - Used to check if move is valid: taking piece of correct colour

    public static void decode (String[] userInput) {
        userInput[2]  = Integer.toString(Integer.parseInt(userInput[2]) - 1);
        userInput[3] = Integer.toString(Integer.parseInt(userInput[3]) - 1);
    } //Decodes user input into x and y coordinates that can be used for the 2D array

    public static void capturePiece (int x, int y) {
        Piece temp = getPiece(x, y);

        if (temp.getColour() == Colour.WHITE) {
            white.remove(temp);
            whiteCaptured.add(temp);
        } else {
            black.remove(temp);
            blackCaptured.add(temp);
        }
        System.out.println((temp.getColour()==Colour.WHITE? "White":"Black") + "'s " + temp.getID() + " was captured!");
    } //Displays captured msg and updates arraylist of pieces

    public static boolean isCheckmate () {
        Piece p = null;

        Piece whiteKing = Board.getKing(Colour.WHITE);
        for (int i = 0; i < Board.black.size(); i++) {
            Piece temp = Board.black.get(i);
            if (temp != whiteKing) {
                if (temp.possibleMove(whiteKing.getX(), whiteKing.getY())) {
                    p = temp;
                }
            }
        }
        Piece blackKing = Board.getKing(Colour.BLACK);
        for (int i = 0; i < Board.white.size(); i++) {
            Piece temp = Board.white.get(i);
            if (temp != blackKing) {
                if (temp.possibleMove(blackKing.getX(), blackKing.getY())) {
                    p = temp;
                }
            }
        }

        if (p != null) {
            Colour kingColour = p.getColour() == Colour.WHITE? Colour.BLACK : Colour.WHITE;

            if (!kingCanMove(kingColour)) {
                return true;
            }

            if (simulateCheckMove(p.getX(), p.getY(), getKing(kingColour))) {
                return true;
            }
        }
        return false;
    }

    public static boolean kingCanMove(Colour colour) {
         Piece king = getKing(colour);
         int x = king.getX();
         int y = king.getY();

        // Directions to check the 8 surrounding squares
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            // Check if the new position is within the bounds of the board
            if (newX <= 7 && newX >= 0 && newY <= 7 && newY >= 0) {
                // Check if the square is empty or can be captured
                if (getPiece(newX, newY) == null || getPiece(newX, newY).getColour() != king.getColour()) {
                    if (!simulateCheckMove(newX, newY, king)) {
                        return true;
                    };
                }
            }
        }
        return false;
    }



    public static boolean simulateCheckMove (int x, int y, Piece p) {
        int orgX = p.getX();
        int orgY = p.getY();
        boolean stillInCheck = false;
        Piece temp = getPiece(x, y);

        if (temp != null) {
            if (temp.getColour() == Colour.WHITE) {
                white.remove(temp);
            } else {
                black.remove(temp);
            }
        } //Removes piece at new location from list before simulating moves for inCheck -- Allows move simulation

        setPiece(x, y, p); //Simulates movement

        if (inCheck()) {
            stillInCheck = true;
        }

        if (temp != null) {
            if (temp.getColour() == Colour.WHITE) {
                white.add(temp);
            } else {
                black.add(temp);
            }
        } //Puts removed piece back in

            //Returns board to original state
        setPiece(x, y, temp);
        setPiece(orgX, orgY, p);

        return stillInCheck;
    } //Checks if king is still in check after attempted move from checked king
    public static boolean inCheck () {
        Piece whiteKing = Board.getKing(Colour.WHITE);
        for (int i = 0; i < Board.black.size(); i++) {
            Piece temp = Board.black.get(i);
            if (temp != whiteKing) {
                if (temp.possibleMove(whiteKing.getX(), whiteKing.getY())) {
                    return true;
                }
            }
        }
        Piece blackKing = Board.getKing(Colour.BLACK);
        for (int i = 0; i < Board.white.size(); i++) {
            Piece temp = Board.white.get(i);
            if (temp != blackKing) {
                if (temp.possibleMove(blackKing.getX(), blackKing.getY())) {
                    return true;
                }
            }
        }

        return false;
    } //Checks if king is in check by simulating every piece move to king location

    public static boolean checkValidMove(String move) {
        String[] userInput = move.split(" ");
        decode(userInput);
        Piece p = getPiece(userInput);
            //Assigns attempted move coordinates to x and y variables for ease of use
        int x = Integer.parseInt(userInput[3]);
        int y = Integer.parseInt(userInput[2]);

        if (x > 7 || y > 7 || x < 0 || y < 0) {
            return false;
        } //Ensures move is within board boundaries

        if (p.getX() == x && p.getY() == y) {
            return false;
        } //Ensures move is not to same square

        if (Board.getPiece(x, y) != null) {
            if (p.isSameColour(x, y, p)) {
                return false;
            } //Checks if attempted move is onto square with piece of same color
        } //Checks if attempted move even contains a piece, if not null, then checks colour



        if (inCheck()) {
            if (simulateCheckMove(x, y, p)) {
                System.out.println("KING IS IN CHECK");
                return false;
            }
        } //Checks if king is in check

        if (!p.possibleMove(Integer.parseInt(userInput[3]), Integer.parseInt(userInput[2]))) {
            return false;
        } //Checks if attempted move can be made by the piece: Knight can move in L shape, ensures attempted move is L shape

        return true;
    } //Checks if move can be made before executing it, returns boolean statement

    public static void executeMove (String move) {
            //Gets move data from user and seperates into components
        String[] userInput = move.split(" ");
        Piece p = getPiece(userInput);
        decode(userInput);
        int y = Integer.parseInt(userInput[2]);
        int x = Integer.parseInt(userInput[3]);


        if (getPiece(x,y) != null) {
            capturePiece(x, y); //Display's caputred msg and updates array lsit of pieces
        }

        if (p.getisfirstmove()) {
            p.setIsfirstmove(false); //
        } //Checks if first move — Sets false if so

        p.move(Integer.parseInt(userInput[3]), Integer.parseInt(userInput[2]), p); //Moves piece to new location

    }
}

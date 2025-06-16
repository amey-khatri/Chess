/**
 * Amey Khatri
 * Main.java
 * Date Last Modified:
 * Class Description:
 **/

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("WELCOME TO CHESS\n");

        System.out.println("This is a two player chess game with white pieces arranged on the bottom and black pieces on the top.\n");
        System.out.println("The pieces are case-sensitive and labeled as follows:");
        System.out.println("\tPawns are named “pawn(column)”. So a pawn in column 4 would be called: pawn4\n" +
                            "\tAll kings and queens are simply named: king queen\n" +
                            "\tAll other pieces are named as (piece name) + K/Q representing king’s side or queen’s side. A bishop on the queens side would be called: bishopQ\n");

        System.out.println("To move a piece, input the piece name, the row, and the column you wish to move it to: pawn1 5 1\n");
        System.out.println("The game will automatically end when no move can be made to escape check.\n");
        System.out.println("WHITE MOVES FIRST... BEGIN\n");


        Board.setBoard();

        int turns = 0;
        Colour colour;
        while (true) {
            Board.printBoard();

            colour = turns % 2 == 0 ? Colour.WHITE : Colour.BLACK; //Sets white's turn or black turn. Starts with white to move
            System.out.printf("%s's turn\n", colour == Colour.WHITE ? Colour.WHITE : Colour.BLACK);
            System.out.print("PIECE ROW COLUMN: ");
            String move = colour.toString() + " " + input.nextLine(); //Takes user input and colour, coalates into one string and sets to execute move

            try {
                //Checks if move is valid, if valid, then processes move through execeuteMove, otherwise returns invalid, does not increment turn counter
                if (Board.checkValidMove(move)) {
                    Board.executeMove(move);
                    if (Board.inCheck()) {
                        if (Board.isCheckmate()) {
                            Board.printBoard();
                            System.out.println("CHECKMATE - " + colour + " WINS");
                            break;
                        }
                        System.out.println("KING IS IN CHECK");
                    }
                    turns++;

                } else {
                    System.out.println("ILLEGAL MOVE");
                }
                System.out.println();
            } catch (NullPointerException e) {
                System.out.println("INVALID INPUT");
            }
        }
    }
}

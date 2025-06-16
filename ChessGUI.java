import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * ChessGUI.java
 * A Swing-based graphical interface for the console Chess game.
 * Uses the existing Board and Piece classes to render the game and handle moves.
 */
public class ChessGUI extends JFrame {
    private int selectedRow = -1, selectedCol = -1;
    private final int squareSize = 80;

    public ChessGUI() {
        // Initialize the game board
        Board.setBoard();

        setTitle("Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BoardPanel panel = new BoardPanel();
        panel.setPreferredSize(new Dimension(8 * squareSize, 8 * squareSize));
        add(panel);

        // Handle mouse clicks for selecting and moving pieces
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = e.getX() / squareSize;
                int row = e.getY() / squareSize;

                if (selectedRow == -1 && selectedCol == -1) {
                    // First click: select a piece if one exists
                    Piece p = Board.getPiece(col, row);
                    if (p != null) {
                        selectedRow = row;
                        selectedCol = col;
                        panel.repaint();
                    }
                } else {
                    // Second click: attempt move
                    Piece p = Board.getPiece(selectedCol, selectedRow);
                    if (p != null) {
                        String move = p.getColour().toString() + " "
                                    + p.getID() + " "
                                    + (row + 1) + " "
                                    + (col + 1);
                        if (Board.checkValidMove(move)) {
                            Board.executeMove(move);
                            if (Board.inCheck()) {
                                if (Board.isCheckmate()) {
                                    JOptionPane.showMessageDialog(panel,
                                        "Checkmate! " + (p.getColour() == Colour.WHITE ? "White" : "Black") + " wins.");
                                } else {
                                    JOptionPane.showMessageDialog(panel, "King is in check!");
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(panel, "Illegal move.");
                        }
                    }
                    // Reset selection and repaint
                    selectedRow = -1;
                    selectedCol = -1;
                    panel.repaint();
                }
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Panel that draws the chess board and pieces
    private class BoardPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw squares and pieces
            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    boolean light = (r + c) % 2 == 0;
                    g.setColor(light ? Color.LIGHT_GRAY : Color.DARK_GRAY);
                    g.fillRect(c * squareSize, r * squareSize, squareSize, squareSize);

                    Piece p = Board.getPiece(c, r);
                    if (p != null) {
                        String label = getUnicodeForPiece(p);
                        g.setColor(p.getColour() == Colour.WHITE ? Color.WHITE : Color.BLACK);
                        g.setFont(new Font("SansSerif", Font.PLAIN, squareSize - 20));
                        FontMetrics fm = g.getFontMetrics();
                        int textWidth = fm.stringWidth(label);
                        int textHeight = fm.getAscent();
                        int x = c * squareSize + (squareSize - textWidth) / 2;
                        int y = r * squareSize + (squareSize + textHeight) / 2;
                        g.drawString(label, x, y);
                    }
                }
            }
            // Highlight selected square
            if (selectedRow != -1) {
                g.setColor(Color.RED);
                g.drawRect(selectedCol * squareSize, selectedRow * squareSize, squareSize, squareSize);
                g.drawRect(selectedCol * squareSize + 1, selectedRow * squareSize + 1, squareSize - 2, squareSize - 2);
            }
        }

        // Maps Piece instances to Unicode chess characters
        private String getUnicodeForPiece(Piece p) {
            if (p instanceof King)   return p.getColour() == Colour.WHITE ? "\u2654" : "\u265A";
            if (p instanceof Queen)  return p.getColour() == Colour.WHITE ? "\u2655" : "\u265B";
            if (p instanceof Rook)   return p.getColour() == Colour.WHITE ? "\u2656" : "\u265C";
            if (p instanceof Bishop) return p.getColour() == Colour.WHITE ? "\u2657" : "\u265D";
            if (p instanceof Knight) return p.getColour() == Colour.WHITE ? "\u2658" : "\u265E";
            if (p instanceof Pawn)   return p.getColour() == Colour.WHITE ? "\u2659" : "\u265F";
            return "";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChessGUI::new);
    }
}

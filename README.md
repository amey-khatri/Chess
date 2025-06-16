# Chess
Simple Chess game with an integrated GUI

**Tech used:** Java, Swing, AWT

This project started as a simple console-based chess game written in Java to reinforce object-oriented programming concepts. The code was written with modularity in mind, seperate the code for each piece on the board into its own disticnt class. Inhertience was used on every piece to easily track and modify global properties whilst still maintaining the individual requirements of each piece. The game logic itself was implemented via multiple functions within the board class that check for validity on each move, raising appropriate flags when necessary. 

Coming back to this project, I implemented a ChessGUI file that uses the Java Swing and AWT libraries to render a GUI for the chess game. This used the same, pre-existing logic written for the console-based version of the game and simply updated the rendered board with the new piece locations each time. 



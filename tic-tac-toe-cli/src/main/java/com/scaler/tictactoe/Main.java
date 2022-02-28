package com.scaler.tictactoe;

public class Main {
    public static void main(String[] args) {
        /*
            TODO: Create the entire game; steps here
                1. construct game object with 2 player characters
                2. For every turn, pring whose turn it is and state of game (3x3 box)
                3. read input (between 1-9) as the box to be marked by next player
                4. Validate input and mark the box
                    4.1 If input invalid, make player enter box no again
                5. Repeat steps 2-4 until either;
                    5.1 checkVictory function shows any player has won
                    5.2 all boxes have been marked
         */
        Game g = new Game("X", "O");
        g.play();
    }
}

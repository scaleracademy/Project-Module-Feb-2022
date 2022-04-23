package com.scaler.tictactoe;

public class Main {

    public static void main(String[] args) {
        /*
        Create entire game. Steps:
            1. Construct game object with 2 players
            2. For every turn, print whose turn it is, with the state
            3. Read input (1-9) as the box to be marked
            4. Validate input and mark the box
                4.1. If the input is invalid, make the player input the box again
            5. Repeat steps 2-4 until
                5.1. checkVictory function shows if any player has won
                5.2. all boxes have been marked
         */

        String p1Char = IOHelper.getPlayerString(1);
        String p2Char = IOHelper.getPlayerStringNotEqualTo(2, p1Char);
        Game game = new Game(p1Char, p2Char);
        game.playGame();
        System.out.println("Thanks for playing! 🎊🎊🎊");
    }

}

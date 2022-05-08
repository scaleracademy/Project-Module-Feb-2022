package com.scaler.tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /*
        TODO: Create the entire game; steps are:
            1. Construct game object with 2 player characters
            2. For every turn, print whose turn it is, and state of game (3x3 box)
            3. Read input (between 1-9) as the box to be marked by next player
            4. Validate input and mark the box
                4.1 If input invalid, make player enter box no again
            5. Repeat steps 2-4 until either;
                5.1  checkVictory function shows any player has won
                5.2  all boxes have been marked
         */

        Game game = new Game("X", "O");

        Scanner scan = new Scanner(System.in);
        int plays = 0;
        while (game.checkVictory() == null && plays < 9) {
            try {
                System.out.println("Player - " + game.getNextTurn().getCharacter());
                System.out.println(game.printGameState());
                int num = scan.nextInt();
                game.nextAttempt(num);
                plays++;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
                continue;
            }
        }
        var result = game.checkVictory();
        if (result == null) {
            System.out.println("Its a Tie!");
        } else {
            System.out.println("Congratulation " + result.getCharacter());
        }
    }
}

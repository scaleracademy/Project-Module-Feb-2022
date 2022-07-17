package com.scaler.tictactoe;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.security.InvalidParameterException;
import java.util.Scanner;

@AllArgsConstructor
@Getter
public class GameController {

    Game game;

    public void start() {

        Scanner scanner = new Scanner(System.in);
        // TODO 2. For every turn, print whose turn it is, and state of game(3x3 box)
        while (game.checkVictory() == null && game.getTurns() < 9) {
            System.out.println("Please enter the box number you'd like to place your character in: ");
            int x = scanner.nextInt();
            try {
                game.nextAttempt(x);
            } catch (InvalidParameterException e) {
                System.out.println(e.getMessage());
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }


        Player victor = game.checkVictory();

        if (victor != null) {
            System.out.println(victor.getName() + " has won. Congratulations!");
        }
        else {
            System.out.println("The game was a draw! :(.");
        }

    }
}

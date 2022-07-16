package com.scaler.tictactoe;

import java.security.InvalidParameterException;
import java.util.Scanner;

public class Main {

    public static final String p1Name = "Parag";
    public static final String p1Char = "X";
    public static final String p2Char = "O";
    public static final String p2Name = "Sheetal";

    public static void main(String[] args) {

        // TODO: Create the entire game; steps are:
        // TODO 1. Construct game object with 2 player characters
        Game game = new Game(new Player(p1Char, p1Name), new Player(p2Char, p2Name));
        GameController controller = new GameController(game);

        controller.start();

        // TODO 3. Read input (between 1 - 9)as the box to be marked by next player
        // TODO 4. Validate input and mark the box
        // TODO 4.1 If input invalid, make player enter box no again
        // TODO 5. Repeat steps 2 - 4 until either;
        // TODO 5.1 checkVictory function shows any player has won
        // TODO 5.2 all boxes have been marked

        System.out.println("Break point sout!");
    }
}

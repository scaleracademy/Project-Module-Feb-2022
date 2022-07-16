package com.scaler.tictactoe;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOHelper {

    private static String readLine() {
        Console console = System.console();
        if (console != null) {
            return console.readLine();
        } else {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            try {
                return consoleReader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static void declareVictor(Player victor) {
        if (victor == null) {
            System.out.println("This game has ended in a draw");
        } else {
            System.out.println("Player " + victor.getCharacter() + " has won! 🎊 🍾");
        }
    }

    static int getBoxNumberToBeFilled() {
        System.out.println("Enter box number (1-9) to be filled.");
        int boxNumber;
        try {
            boxNumber = Integer.parseInt(readLine());
            System.out.println();
            if (boxNumber < 1 || boxNumber > 9) {
                System.out.println("Invalid Box number. Please try again.\n");
                return getBoxNumberToBeFilled();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Box number. Please try again.\n");
            return getBoxNumberToBeFilled();
        }
        return boxNumber;
    }

    static void printNextTurn(Player nextTurn) {
        System.out.println("Player " + nextTurn.getCharacter() + " to play.");
    }

    static void printGameState(final String[][] gameState) {
        System.out.println("Current Game State : \n");
        for (String[] row : gameState) {
            for (String col : row) {
                if (col == null)
                    col = "-";
                System.out.print(col + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static String getPlayerString(int playerNum) {
        System.out.println("Enter character for Player " + playerNum);
        String character = readLine();
        System.out.println();
        while (character.length() > 1) {
            System.out.println("Please enter a single character.");
            character = readLine();
            System.out.println();
        }
        return character;
    }

    static String getPlayerStringNotEqualTo(int playerNum, String character) {
        String newChar = getPlayerString(playerNum);
        while (newChar.equals(character)) {
            System.out.println("Character cannot be same as " + character);
            System.out.println();
            newChar = getPlayerString(playerNum);
        }
        return newChar;
    }
}

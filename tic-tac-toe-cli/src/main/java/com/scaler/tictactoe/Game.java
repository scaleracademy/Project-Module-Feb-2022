package com.scaler.tictactoe;

import lombok.Getter;

import java.util.Scanner;

public class Game {
    @Getter
    private Player p1;
    @Getter
    private Player p2;

    @Getter
    private Player nextTurn;

    private String[][] gameState = new String[3][3];

    public Game(String p1Char, String p2Char) {
        p1 = new Player(p1Char, 1);
        p2 = new Player(p2Char, 2);

        // init next turn for player 1
        nextTurn = p1;
    }

    public String getCharInBox(int box) {
        int row = (box - 1) / 3;
        int col = (box - 1) % 3;

        if (box < 1 || box > 9)
            throw new IllegalArgumentException("Box no. must be between 1 and 9");
        else
            return gameState[row][col];
    }

    /**
     * This method is to play the next turn
     *
     * @param box the box in which to be marked
     */
    public void nextAttempt(int box) {
        int row = (box - 1) / 3;
        int col = (box - 1) % 3;

        if (box < 1 || box > 9)
            throw new IllegalArgumentException("Box no. must be between 1 and 9");
        if (gameState[row][col] != null)
            throw new IllegalStateException("This box is not empty");

        gameState[row][col] = nextTurn.getCharacter();

        // switch turn of players
        if (nextTurn == p1)
            nextTurn = p2;
        else
            nextTurn = p1;
    }

    /**
     * Checks board state and tells if any winners
     *
     * @return p1 or p2 whoever has won; or null if no winner yet
     **/
    public Player checkVictory() {
        // win strategies
        for (int i = 0; i < 3; i++) {
            // ROW
            if (gameState[i][0] != null && gameState[i][0] == gameState[i][1] && gameState[i][0] == gameState[i][2]) {
                return getPlayerFromCharacter(gameState[i][0]);
            }
            // COL
            if (gameState[0][i] != null && gameState[0][i] == gameState[1][i] && gameState[0][i] == gameState[2][i]) {
                return getPlayerFromCharacter(gameState[i][0]);
            }
        }
        // TL to BR
        if (gameState[0][0] != null && gameState[0][0] == gameState[1][1] && gameState[0][0] == gameState[2][2]) {
            return getPlayerFromCharacter(gameState[0][0]);
        }
        // TR to BL
        if (gameState[0][2] != null && gameState[0][2] == gameState[1][1] && gameState[0][2] == gameState[2][0]) {
            return getPlayerFromCharacter(gameState[0][2]);
        }
        return null;
    }

    public Player getPlayerFromCharacter(String character) {
        if (p1.getCharacter() == character)
            return p1;
        else if (p2.getCharacter() == character)
            return p2;
        return null;
    }

    public void displayBoard() {
        for (int i = 0; i < 3; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < 3; j++) {
                row.append("_");
                if (gameState[i][j] == null) {
                    row.append("_");
                } else {
                    row.append(gameState[i][j]);
                }
                row.append("_");
                if (j == 2)
                    break;
                row.append("|");
            }
            System.out.println(row.toString());
        }
    }

    // Orchestrator
    public void play() {
        // 9 boxes - 9 rounds in total
        System.out.println("Below is the board.");
        displayBoard();
        System.out.printf("Player 1's character is %s and Player 2's character is %s.\n", p1.getCharacter(),
                p2.getCharacter());
        System.out.println("Boxes are named from 1 to 9, left to right and then from top to bottom. Let's start!");
        Scanner sc = new Scanner(System.in);
        int i = 0;
        while (i < 9) {
            System.out.printf("Player %d, please enter the box number \n", nextTurn.getId());
            int box = sc.nextInt();
            try {
                nextAttempt(box);
            } catch (IllegalArgumentException | IllegalStateException ex) {
                System.out.printf("%s, Try again!\n", ex.toString());
                continue;
            }
            displayBoard();
            Player winner = checkVictory();
            if (winner != null) {
                System.out.printf("Player %d has won the game, Congrats!\n", winner.getId());
                return;
            }
            ;
            i++;
        }

        System.out.println("There is no winner!");
    }

}

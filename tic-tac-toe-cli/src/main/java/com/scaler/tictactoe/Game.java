package com.scaler.tictactoe;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Game {
    @Getter
    private Player p1;
    @Getter
    private Player p2;

    @Getter
    private Player nextTurn;

    private String[][] gameState = new String[3][3];
    private int numTurns;

    public Game(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.numTurns = 0;

        // init next turn for player 1
        nextTurn = this.p1;

        // printing initial status
        printStatus();
    }

    private void printStatus() {
        printGameState();
        printNextTurn();
    }

    private void printNextTurn() {
        System.out.println("Next Turn: " + nextTurn.getName());
    }

    public String getCharInBox(int box) {
        int row = (box - 1) / 3;
        int col = (box - 1) % 3;

        if (box < 1 || box > 9) throw new IllegalArgumentException("Box no. must be between 1 and 9");
        else return gameState[row][col];
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
        numTurns++;

        // switch turn of players
        nextTurn = (nextTurn == p1) ? p2 : p1;

        // Print new state of board
        this.printStatus();
    }

    /**
     * Checks board state and tells if any winners
     *
     * @return p1 or p2 whoever has won; or null if no winner yet
     */
    public Player checkVictory() {
        // TODO
        Player rowWinner = checkRows();
        Player colWinner = checkCols();
        if (rowWinner != null) {
            return rowWinner;
        } else if (colWinner != null) {
            return colWinner;
        } else {
            return checkDiag();
        }
    }

    private Player checkDiag() {

        if (gameState[0][0] != null && gameState[0][0] == gameState[1][1] && gameState[1][1] == gameState[2][2]) {
            return getPlayer(gameState[0][0]);
        } else if (gameState[2][0] != null && gameState[2][0] == gameState[1][1] && gameState[1][1] == gameState[0][2]) {
            return getPlayer(gameState[2][0]);
        } else {
            return null;
        }

    }


    private Player checkCols() {
        for (int i = 0; i < 3; i++) {
            if (gameState[0][i] != null && gameState[0][i] == gameState[1][i] && gameState[1][i] == gameState[2][i])
                return getPlayer(gameState[0][i]);
        }

        return null;
    }

    private Player checkRows() {
        for (int i = 0; i < 3; i++) {
            if (gameState[i][0] != null && gameState[i][0] == gameState[i][1] && gameState[i][1] == gameState[i][2])
                return getPlayer(gameState[i][0]);
        }

        return null;
    }

    private Player getPlayer(String s) {
        return s.equals(p1.getCharacter()) ? p1 : p2;
    }

    public void printGameState() {
        System.out.println(
                "Current State of Board: \n" +
                        "  " + gameState[0][0] + " | " + gameState[0][1] + " | " + gameState[0][2] + "\n" +
                        "------------\n" +
                        "  " + gameState[1][0] + " | " + gameState[1][1] + " | " + gameState[1][2] + "\n" +
                        "------------\n" +
                        "  " + gameState[2][0] + " | " + gameState[2][1] + " | " + gameState[2][2] + "\n");
    }

    public int getTurns() {
        return numTurns;
    }
}

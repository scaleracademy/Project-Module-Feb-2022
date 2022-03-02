package com.scaler.tictactoe;


import lombok.Getter;

public class Game {
    @Getter
    private Player p1;
    @Getter
    private Player p2;

    @Getter
    private Player nextTurn;

    private String[][] gameState = new String[3][3];

    public Game(String p1Char, String p2Char) {
        p1 = new Player(p1Char);
        p2 = new Player(p2Char);

        // init next turn for player 1
        nextTurn = p1;
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

        if (box < 1 || box > 9) throw new IllegalArgumentException("Box no. must be between 1 and 9");
        if (gameState[row][col] != null) throw new IllegalStateException("This box is not empty");

        gameState[row][col] = nextTurn.getCharacter();

        // switch turn of players
        if (nextTurn == p1) nextTurn = p2;
        else nextTurn = p1;
    }

    /**
     * Checks board state and tells if any winners
     *
     * @return p1 or p2 whoever has won; or null if no winner yet
     */
    public Player checkVictory() {
        // TODO
        return null;
    }
}

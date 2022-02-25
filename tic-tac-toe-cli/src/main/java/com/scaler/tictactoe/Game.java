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

    public String getCharInBox(int boxNo) {
        switch (boxNo) {
            case 1:
                return gameState[0][0];
            case 2:
                return gameState[0][1];
            case 3:
                return gameState[0][2];
            case 4:
                return gameState[1][0];
            case 5:
                return gameState[1][1];
            case 6:
                return gameState[1][2];
            case 7:
                return gameState[2][0];
            case 8:
                return gameState[2][1];
            case 9:
                return gameState[2][2];
            default:
                return null;
        }
    }

    /**
     * This method is to play the next turn
     *
     * @param box the box in which to be marked
     */
    public void nextAttempt(int box) {

    }
}

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
        //Diagonal
        if(gameState[0][0] !=null && gameState[0][0] == gameState[1][1] && gameState[2][2]==gameState[1][1]){
            return gameState[0][0] == p1.getCharacter() ? p1:p2;
        }
        if(gameState[0][2] !=null && gameState[0][2] == gameState[1][1] && gameState[2][0]==gameState[1][1]){
            return gameState[0][0] == p1.getCharacter() ? p1:p2;
        }
        //Row
        if(gameState[0][0] !=null && gameState[0][0] == gameState[0][1] && gameState[0][1] == gameState[0][2])
            return gameState[0][0] == p1.getCharacter() ? p1:p2;

        if(gameState[1][0] !=null && gameState[1][0] == gameState[1][1] && gameState[1][1] == gameState[1][2])
            return gameState[1][0] == p1.getCharacter() ? p1:p2;

        if(gameState[2][0] !=null && gameState[2][0] == gameState[2][1] && gameState[2][1] == gameState[2][2])
            return gameState[2][0] == p1.getCharacter() ? p1:p2;

        //Column
        if(gameState[0][0] !=null && gameState[0][0] == gameState[1][0] && gameState[2][0]==gameState[1][0]){
            return gameState[0][0] == p1.getCharacter() ? p1:p2;
        }

        if(gameState[0][1] !=null && gameState[0][1] == gameState[1][1] && gameState[2][1]==gameState[1][1]){
            return gameState[0][1] == p1.getCharacter() ? p1:p2;
        }

        if(gameState[0][2] !=null && gameState[0][2] == gameState[1][2] && gameState[2][2]==gameState[1][2]){
            return gameState[0][2] == p1.getCharacter() ? p1:p2;
        }
        return null;
    }

    public String printGameState() {
        return "  " + gameState[0][0] + " | " + gameState[0][1] + " | " + gameState[0][2] + "\n" +
                "------------\n" +
                "  " + gameState[1][0] + " | " + gameState[1][1] + " | " + gameState[1][2] + "\n" +
                "------------\n" +
                "  " + gameState[2][0] + " | " + gameState[2][1] + " | " + gameState[2][2];
    }
}

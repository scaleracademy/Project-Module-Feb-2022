package com.scaler.tictactoe;


import lombok.Getter;

import java.util.List;
import java.util.Map;

public class Game {
    @Getter
    private final Player p1;
    @Getter
    private final Player p2;
    @Getter
    private Player nextTurn;
    @Getter
    private final String[][] gameState = new String[3][3];

    private int emptyBoxes;

    private static final List<Integer> topRowBoxes = List.of(1, 2, 3);
    private static final List<Integer> midRowBoxes = List.of(4, 5, 6);
    private static final List<Integer> bottomRowBoxes = List.of(7, 8, 9);
    private static final List<Integer> leftColBoxes = List.of(1, 4, 7);
    private static final List<Integer> midColBoxes = List.of(2, 5, 8);
    private static final List<Integer> rightColBoxes = List.of(3, 6, 9);
    private static final List<Integer> leftDiagonalBoxes = List.of(1, 5, 9);
    private static final List<Integer> rightDiagonalBoxes = List.of(3, 5, 7);

    private static final Map<Integer, List<List<Integer>>> boxToWinningLines = Map.ofEntries(
            Map.entry(1, List.of(topRowBoxes, leftColBoxes, leftDiagonalBoxes)),
            Map.entry(2, List.of(topRowBoxes, midColBoxes)),
            Map.entry(3, List.of(topRowBoxes, rightColBoxes, rightDiagonalBoxes)),
            Map.entry(4, List.of(midRowBoxes, leftColBoxes)),
            Map.entry(5, List.of(midRowBoxes, midColBoxes, leftDiagonalBoxes, rightDiagonalBoxes)),
            Map.entry(6, List.of(midRowBoxes, rightColBoxes)),
            Map.entry(7, List.of(bottomRowBoxes, leftColBoxes, rightDiagonalBoxes)),
            Map.entry(8, List.of(bottomRowBoxes, midColBoxes)),
            Map.entry(9, List.of(bottomRowBoxes, rightColBoxes, leftDiagonalBoxes))
    );

    public Game(String p1Char, String p2Char) {
        this.p1 = new Player(p1Char);
        this.p2 = new Player(p2Char);
        // init next turn for player 1
        this.nextTurn = p1;
        this.emptyBoxes = 9;
    }

    public void playGame() {
        Player victor = null;

        while (!isGameOverByExhaustionOfBoxes()) {
            IOHelper.printGameState(getGameState());
            Player nextTurnPlayer = getNextTurn();
            IOHelper.printNextTurn(nextTurnPlayer);
            int boxNumber = IOHelper.getBoxNumberToBeFilled();
            while (!fillBoxSuccess(boxNumber)) {
                System.out.println("Invalid Box number. Please try again.\n");
                IOHelper.printNextTurn(getNextTurn());
                boxNumber = IOHelper.getBoxNumberToBeFilled();
            }
            victor = checkVictory(boxNumber, nextTurnPlayer);
            if (victor != null) {
                break;
            }
        }

        IOHelper.printGameState(getGameState());
        IOHelper.declareVictor(victor);
    }

    public boolean fillBoxSuccess(int boxNumber) {
        try {
            nextAttempt(boxNumber);
            return true;
        } catch (IllegalArgumentException | IllegalStateException e) {
            return false;
        }
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
        if (isGameOverByExhaustionOfBoxes()) throw new IllegalStateException("All boxes have been filled. Game over.");

        gameState[row][col] = getNextTurn().getCharacter();
        emptyBoxes -= 1;

        // switch turn of players
        if (nextTurn == p1) nextTurn = p2;
        else nextTurn = p1;
    }

    /**
     * Checks if the game has been won by a player
     *
     * @param lastFilledBox       Box number that was last filled correctly
     * @param playerWhoLastPlayed The player who played the last move
     * @return The player who has won the game, null if no one has won yet
     */
    public Player checkVictory(int lastFilledBox, Player playerWhoLastPlayed) {

        if (!playerWhoLastPlayed.getCharacter().equals(getCharInBox(lastFilledBox))) {
            throw new IllegalStateException("Filled character does not match player's marking character");
        }
        String playerCharacter = playerWhoLastPlayed.getCharacter();

        for (List<Integer> possibleWinningLine : boxToWinningLines.get(lastFilledBox)) {

            // init number of character matches for this player to 1 -- for the currently filled box
            int lineMatches = 0;

            // check every line to see a possible complete line made for this box
            for (int boxNo : possibleWinningLine) {
                if (playerCharacter.equals(getCharInBox(boxNo))) {
                    lineMatches += 1;
                    continue;
                }
                break;
            }

            // check if this current line is complete with the character for this player
            if (lineMatches == 3) {
                return playerWhoLastPlayed;
            }
        }
        return null;
    }

    public boolean isGameOverByExhaustionOfBoxes() {
        return emptyBoxes == 0;
    }
}

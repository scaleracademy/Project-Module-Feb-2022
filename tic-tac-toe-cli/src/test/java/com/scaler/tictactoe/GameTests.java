package com.scaler.tictactoe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTests {

    @Test
    void canCreateNewGame() {
        Game g = new Game("❌", "⭕️");

        assertEquals("❌", g.getP1().getCharacter());
        assertEquals("⭕️", g.getP2().getCharacter());

        assertNull(g.getCharInBox(1));
        assertNull(g.getCharInBox(2));
        assertNull(g.getCharInBox(3));
        assertNull(g.getCharInBox(4));
        assertNull(g.getCharInBox(5));
        assertNull(g.getCharInBox(6));
        assertNull(g.getCharInBox(7));
        assertNull(g.getCharInBox(8));
        assertNull(g.getCharInBox(9));

        assertEquals(g.getP1(), g.getNextTurn());
    }

    @Test
    void canMarkBoxesViaAttempts() {
        Game g = new Game("❌", "⭕️");

        g.nextAttempt(1);

        assertEquals("❌", g.getCharInBox(1));
        assertEquals(g.getP2(), g.getNextTurn());

        // check that already marked boxes are not allowed to be marked

        assertThrowsExactly(IllegalStateException.class, () -> g.nextAttempt(1));

    }

    @Test
    void throwsExceptionForInvalidBoxAttempt() {
        Game g = new Game("❌", "⭕️");

        assertThrowsExactly(IllegalArgumentException.class, () -> g.nextAttempt(10));

    }

    @Test
    void checkVictoryTest() {
        Game game = new Game("X", "O");

        // Player 1 marks box 1
        game.nextAttempt(1);
        // Player 2 plays box 5
        Player p = game.getNextTurn();
        game.nextAttempt(5);
        assertEquals(game.getP2(), p); // Player 1 should have the next turn
        // Game has only been played by two moves. No winner yet
        assertNull(game.checkVictory(5, p));

        p = game.getNextTurn();
        assertEquals(game.getP1(), p); // Player 1 should have the next turn
        game.nextAttempt(2);
        // box number 4 has not been filled
        Player finalP = p;
        assertThrowsExactly(IllegalStateException.class, () -> game.checkVictory(4, finalP));
        // box number 5 was filled by player 2 and not player 1
        assertThrowsExactly(IllegalStateException.class, () -> game.checkVictory(5, finalP));
        // Game has only been played by three total moves. No winner yet
        assertNull(game.checkVictory(2, p));

        // Player 2
        game.nextAttempt(6);
        // Player 1 has played boxes 1,2,3
        p = game.getNextTurn();
        assertEquals(game.getP1(), p); // Player 1 should have the next turn
        game.nextAttempt(3);
        assertEquals(game.getP1(), game.checkVictory(3, p));
    }
}

package com.scaler.tictactoe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

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

        assertThrowsExactly(IllegalStateException.class, () -> {
            g.nextAttempt(1);
        });

    }

    @Test
    void throwsExceptionForInvalidBoxAttempt() {
        Game g = new Game("❌", "⭕️");

        assertThrowsExactly(IllegalArgumentException.class, () -> {
            g.nextAttempt(10);
        });

    }

    void checkVictory() {
        Game g = new Game("X", "O");
        g.nextAttempt(1);
        assertNull(g.checkVictory());
        g.nextAttempt(5);
        assertNull(g.checkVictory());
        g.nextAttempt(9);
        assertNull(g.checkVictory());
        g.nextAttempt(3);
        assertNull(g.checkVictory());
        g.nextAttempt(7);
        assertNull(g.checkVictory());
        g.nextAttempt(4);
        assertNull(g.checkVictory());
        g.nextAttempt(8);

        assertEquals(g.getP1(), g.checkVictory());
    }

}

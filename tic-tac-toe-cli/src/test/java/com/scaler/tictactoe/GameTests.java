package com.scaler.tictactoe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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

        g.nextAttempt(0);

        assertEquals("❌", g.getCharInBox(0));
        assertEquals(g.getP2(), g.getNextTurn());

    }
}

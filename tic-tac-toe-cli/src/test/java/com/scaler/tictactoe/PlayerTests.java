package com.scaler.tictactoe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTests {

    @Test
    void constructPlayerWithCharacter () {
        Player p1 = new Player("❌", 1);
        assertEquals("❌", p1.getCharacter());
        assertEquals(1, p1.getId());
    }
}

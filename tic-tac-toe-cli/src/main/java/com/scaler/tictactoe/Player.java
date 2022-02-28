package com.scaler.tictactoe;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Player {
    private String character;
    private int id;
    public String getCharacter(){
        return this.character;
    }

    public int getId() {
        return id;
    }
}

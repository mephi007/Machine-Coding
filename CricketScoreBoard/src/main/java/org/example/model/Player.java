package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {

    private String playerName;
    private int runs;
    private int numOF4s;
    private int numOF6s;
    private boolean isOut;
    public Player(String playerName){
        this.playerName = playerName;
    }
}

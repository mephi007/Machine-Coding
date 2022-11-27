package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Team {
    private String name;
    private int totalruns;
    private List<Player> players;
    private int wicketFallen;
    private float oversPlayed;
    private boolean haveWon;

    public Team( String name, List<Player> players){
        this.name = name;
    }
}

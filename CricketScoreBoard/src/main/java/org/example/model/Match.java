package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Match {
    private String matchName;
    private int numOfOvers;
    private int numOfPlayers;
    private Team team1;
    private Team team2;

    public Match(String matchName, int numOfOvers, int numOfPlayers, Team team1, Team team2){
        this.matchName = matchName;
        this.numOfOvers = numOfOvers;
        this.numOfPlayers = numOfPlayers;
        this.team1 = team1;
        this.team2 = team2;
    }
    
    @Override
    public String toString() {
        return "Match{" +
                "matchName='" + matchName + '\'' +
                ", numOfOvers=" + numOfOvers +
                ", numOfPlayers=" + numOfPlayers +
                ", team1=" + team1 +
                ", team2=" + team2 +
                '}';
    }
}

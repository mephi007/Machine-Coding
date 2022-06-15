package org.example;

import models.Ladder;
import models.Player;
import models.Snake;
import services.SnakeLadderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int noOfSnakes = sc.nextInt();
        List<Snake> snakes = new ArrayList<>();
        for(int i=0; i<noOfSnakes; i++){
            snakes.add(new Snake(sc.nextInt(), sc.nextInt()));
        }
        int noOfLadders = sc.nextInt();
        List<Ladder> ladders = new ArrayList<>();
        for(int i=0; i< noOfLadders; i++){
            ladders.add(new Ladder(sc.nextInt(), sc.nextInt()));
        }

        int noOfPlayers = sc.nextInt();
        List<Player> players = new ArrayList<>();
        for(int i=0; i<noOfPlayers; i++){
            players.add(new Player(sc.next()));
        }

        SnakeLadderService snakeLadderService = new SnakeLadderService();
        snakeLadderService.setLadders(ladders);
        snakeLadderService.setSnakes(snakes);
        snakeLadderService.setPlayers(players);

        snakeLadderService.startGame();
    }
}

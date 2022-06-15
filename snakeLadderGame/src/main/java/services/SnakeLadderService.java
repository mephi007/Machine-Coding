package services;

import models.Ladder;
import models.Player;
import models.Snake;
import models.SnakeLadderBoard;

import java.util.*;

public class SnakeLadderService {

    private SnakeLadderBoard snakeLadderBoard;
    private int initialNumOfPlayer;
    private Queue<Player> players;
    private boolean isGameCompleted;
    private int numOfDices;
    private boolean shouldAllowMultipleDiceRollOnSix;
    private static final int DEFAULT_BOARD_SIZE = 100;
    private static final int DEFAULT_DICE_NUM = 1;

    public SnakeLadderService(int boardSize) { //can take board size input later
        this.snakeLadderBoard = new SnakeLadderBoard(boardSize);
        this.players = new LinkedList<>();
        this.numOfDices = SnakeLadderService.DEFAULT_DICE_NUM;
    }

    public SnakeLadderService(){
        this(SnakeLadderService.DEFAULT_DICE_NUM);
    }

    public void setNumOfDices(int num){
        this.numOfDices = num;
    }

    public void setShouldAllowMultipleDiceRollOnSix(boolean allow){
        this.shouldAllowMultipleDiceRollOnSix = allow;
    }

    public void setPlayers(List<Player> players){
        this.players = new LinkedList<Player>();
        this.initialNumOfPlayer = players.size();
        Map<String, Integer> playerPieces = new HashMap<>();
        for(Player player: players){
            this.players.add(player);
            playerPieces.put(player.getId(), 0);
        }

        snakeLadderBoard.setPlayerPieces(playerPieces);
    }

    public void setSnakes(List<Snake> snakes){
        snakeLadderBoard.setSnakes(snakes);
    }

    public void setLadders(List<Ladder> ladders){
        snakeLadderBoard.setLadders(ladders);
    }

    private void movePlayer(Player player, int positions){
        int oldPosition = snakeLadderBoard.getPlayerPieces().get(player.getId());
        int boardSize = snakeLadderBoard.getSize();
         //positions = dice roll value
        int newPosition = oldPosition + positions;

        if(newPosition > boardSize){
            newPosition = oldPosition;
        }else{
            newPosition = getNewPositionAfterSnakeAndLadderEncounter(newPosition);
        }
        snakeLadderBoard.getPlayerPieces().put(player.getId(), newPosition);
        System.out.println(player.getName() + " rolled a " + positions + " and moved to " + newPosition);
    }

    private int getNewPositionAfterSnakeAndLadderEncounter(int newPosition) {
        int prevPosition;
        do{
            prevPosition = newPosition;
            for(Snake snake : snakeLadderBoard.getSnakes()){
                if(snake.getStart() == newPosition){
                    newPosition = snake.getEnd();
                }
            }
            for(Ladder ladder: snakeLadderBoard.getLadder()){
                if(ladder.getEnd() == newPosition){
                    newPosition = ladder.getStart();
                }
            }
        }while(newPosition != prevPosition);
        return newPosition;
    }

    private int getTotalValueAfterDiceRolls(){
        return DiceService.roll();
    }

    private boolean hasPlayerWon(Player player){
        int playerPosition = snakeLadderBoard.getPlayerPieces().get(player.getId());
        int winningPosition = snakeLadderBoard.getSize();
        return playerPosition == winningPosition;
    }

    private boolean isGameCompleted(){
        int currNumOfPlayer = players.size();
        return currNumOfPlayer < initialNumOfPlayer;
    }

    public void startGame(){
        while(!isGameCompleted){
            int totalDiceValue = getTotalValueAfterDiceRolls();
            Player currPlayer = players.poll();
            movePlayer(currPlayer, totalDiceValue);
            if(hasPlayerWon(currPlayer)){
                System.out.println(currPlayer.getName() + " wins the game");
                snakeLadderBoard.getPlayerPieces().remove(currPlayer.getId());
            }else{
                players.add(currPlayer);
            }
        }
    }


}

package org.example;

import org.example.model.Match;
import org.example.model.Player;
import org.example.model.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        System.out.println( "Cricket Dash Board!" );
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Team 1 name : ");
        String team1Name = input.nextLine();
        System.out.println(" Enter Batting order for Team1");
        /*
        TODO TAKE PLAYERS DETAILS FROM USER
         */
        List<Player> team1Players = new ArrayList();
        team1Players.add(new Player("Sumit"));
        team1Players.add(new Player("Shree"));
        team1Players.add(new Player("Vimal"));
        Team team1 = new Team(team1Name, team1Players);
        System.out.println("Enter Team 2 name : ");
        String team2Name = input.nextLine();
        System.out.println(" Enter Batting order for Team1");
        /*
        TODO TAKE PLAYERS DETAILS FROM USER
         */
        List<Player> team2Players = new ArrayList();
        team2Players.add(new Player("Subhendu"));
        team2Players.add(new Player("SomDev"));
        team2Players.add(new Player("Mandita"));
        Team team2 = new Team(team2Name, team2Players);
        int numOfOvers = 3;
        int numOfPlayers = 3;
        Match match = new Match("RoyV/sGhosh", 3, 3, team1, team2);
        System.out.println(match.toString());
    }
}

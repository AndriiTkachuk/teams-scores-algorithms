package com.andrew.tkachuk;

public class GameRecord {
    public String team;
    public String player;
    public int scores;
    public int passes;

    public GameRecord(String team, String player, int scores, int passes) {
        this.team = team;
        this.player = player;
        this.scores = scores;
        this.passes = passes;
    }
}


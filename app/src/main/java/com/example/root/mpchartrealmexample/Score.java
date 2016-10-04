package com.example.root.mpchartrealmexample;

import io.realm.RealmObject;

/**
 * Created by root on 4/10/16.
 */

public class Score extends RealmObject {
    private float totalScore;

    private float scoreNr;

    private String playerName;

    public Score() { } // no arguments constructor required for realm

    public Score(float totalScore, float scoreNr, String playerName) {
        this.scoreNr = scoreNr;
        this.playerName = playerName;
        this.totalScore = totalScore;
    }

    // all getters and setters
    public float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(float totalScore) {
        this.totalScore = totalScore;
    }

    public float getScoreNr() {
        return scoreNr;
    }

    public void setScoreNr(float scoreNr) {
        this.scoreNr = scoreNr;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}

package com.example.superstar;

public class OverallReward {
    private int id;
    private int totalScores;

    public OverallReward(int id, int totalScores) {
        this.id = id;
        this.totalScores = totalScores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalScores() {
        return totalScores;
    }

    public void setTotalScores(int totalScores) {
        this.totalScores = totalScores;
    }
}

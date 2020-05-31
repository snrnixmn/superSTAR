package com.example.superstar;

import java.io.Serializable;

public class Reward implements Serializable {
    private int id;
    private String name;
    private String date;
    private String chore;
    private int points;
    private int total;

    public Reward(int id, String name, String date, String chore, int points, int total) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.chore = chore;
        this.points = points;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getChore() {
        return chore;
    }

    public void setChore(String chore) {
        this.chore = chore;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

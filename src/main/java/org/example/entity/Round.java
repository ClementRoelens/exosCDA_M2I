package org.example.entity;

public class Round {

    private int score;
    private int rollNumber;

    public Round() {
        rollNumber = 1;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    @Override
    public String toString() {
        return "Round{" +
                "score=" + score +
                ", rollNumber=" + rollNumber +
                '}';
    }
}
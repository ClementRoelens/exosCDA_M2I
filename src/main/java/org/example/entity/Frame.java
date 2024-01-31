package org.example.entity;

import java.util.Arrays;

public class Frame {
    private RollResult rollResult;
    private int score;
    private int currentRoundNumber;
    private Round[] rounds;
    private boolean isEnded;


    public Frame(RollResult rollResult) {
        rounds = new Round[10];
        this.rollResult = rollResult;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCurrentRoundNumber() {
        return currentRoundNumber;
    }

    public void setCurrentRoundNumber(int currentRoundNumber) {
        this.currentRoundNumber = currentRoundNumber;
    }

    public Round[] getRounds() {
        return rounds;
    }

    public void setRounds(Round[] rounds) {
        this.rounds = rounds;
    }

    public boolean isEnded() {
        return isEnded;
    }

    public void setEnded(boolean ended) {
        isEnded = ended;
    }

    public Round getCurrentRound() {
        return rounds[currentRoundNumber - 1];
    }

    public void startNewRound() {
        System.out.println("Debut du round n° " + ++currentRoundNumber);
        rounds[currentRoundNumber - 1] = new Round();
    }

    public void roll() {
        int maxValue = (currentRoundNumber == 10) ?
                30 - getCurrentRound().getScore() :
                10 - getCurrentRound().getScore();
        int result = rollResult.getResult(maxValue);
        getCurrentRound().setScore(result + getCurrentRound().getScore());
        System.out.println("Bravo, tu fais un score de " + result);
        if (currentRoundNumber < 10) {
            if (result < 10 && getCurrentRound().getRollNumber() == 1) {
                if (getCurrentRound().getRollNumber() == 1) {
                    getCurrentRound().setRollNumber(2);
                }
                score = getScore() + getCurrentRound().getScore();
            } else {
                score = getScore() + getCurrentRound().getScore();
                setCurrentRoundNumber(getCurrentRoundNumber() + 1);
            }

        } else {
            if (result == 10 || (getCurrentRound().getScore() >= 10)) {
                if (getCurrentRound().getRollNumber() == 1) {
                    getCurrentRound().setRollNumber(2);
                } else {
                    getCurrentRound().setRollNumber(3);
                }
            }
        }
    }


    @Override
    public String toString() {
        return "Frame{" +
                "rollResult=" + rollResult +
                ", score=" + score +
                ", currentRoundNumber=" + currentRoundNumber +
                ", rounds=" + Arrays.toString(rounds) +
                '}';
    }
}

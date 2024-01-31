package org.example.entity;

import java.util.Random;

public class RollResultImpl implements RollResult {
    private Random random;

    public RollResultImpl() {
        this.random = new Random();
    }

    @Override
    public int getResult(int maxResult) {
        return random.nextInt(maxResult+1);
    }
}

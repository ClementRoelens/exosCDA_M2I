package org.example.entity;

import java.util.Random;

public class WordGeneratorImpl implements WordGenerator {
    private final String[] words;
    private final Random random;


    public WordGeneratorImpl() {
        words = new String[]{
                "HENDRIX",
                "PAGE",
                "GILMOUR",
                "LAIHO",
                "MUSTAINE",
                "VAUGHAN"
        };
        random = new Random();
    }

    @Override
    public String generateRandomWord() {
        return words[random.nextInt(0,words.length)];
    }
}

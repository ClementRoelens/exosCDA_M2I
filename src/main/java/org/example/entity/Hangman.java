package org.example.entity;

public class Hangman {

    private final char[] seekedWord;
    private final char[] foundLetters;
    private int remainingTries;



    public Hangman(WordGenerator wordGeneratorImpl) {
        remainingTries = 10;
        seekedWord = wordGeneratorImpl.generateRandomWord().toCharArray();
        foundLetters = new char[seekedWord.length];
    }


    public int getRemainingTries() {
        return remainingTries;
    }

    public void setRemainingTries(int remainingTries) {
        this.remainingTries = remainingTries;
    }



    public boolean canPlay(){
        return remainingTries != 0;
    }

    public boolean play(char playedCharacter){
        boolean result = false;
        if (canPlay()){
            for (int i = 0; i < seekedWord.length; i++) {
                if (seekedWord[i] == playedCharacter){
                    seekedWord[i] = '\u0000';
                    foundLetters[i] = playedCharacter;
                    result = true;
                }
            }
            if (!result){
                remainingTries--;
            }
        }
        return result;
    }

    public boolean isWon(){
        for (char c : foundLetters){
            if (c == '\u0000'){
                return false;
            }
        }
        return true;
    }

    public String generateMask(){
        StringBuilder mask = new StringBuilder();
        for (char c : foundLetters){
            if (c == '\u0000'){
                mask.append("_");
            } else {
                mask.append(c);
            }
        }
        return mask.toString();
    }
}

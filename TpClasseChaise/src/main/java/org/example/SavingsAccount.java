package org.example;

public class SavingsAccount extends Account {
    private int interestsRate;

    public void interestsCalcul(int yearsNumber){
        this.clearance = this.clearance*Math.pow((1+ (double) interestsRate/100),yearsNumber) ;
    }

    public SavingsAccount(int interestsRate) {
        this.interestsRate = interestsRate;
    }
}

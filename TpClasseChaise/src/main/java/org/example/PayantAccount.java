package org.example;

public class PayantAccount extends Account {
    private int taxRate = 5;

    public void credit(double amount){
        this.clearance += (amount - amount*this.taxRate/100);
    }

    public void debit(double amount){
        this.clearance -= (amount+amount*this.taxRate/100);
    }
}

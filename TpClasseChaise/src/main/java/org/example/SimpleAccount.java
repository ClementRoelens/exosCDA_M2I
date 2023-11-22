package org.example;

public class SimpleAccount extends Account {
    private double overdraft;

    public SimpleAccount(double overdraft) {
        this.overdraft = overdraft;
    }

    public void debit(double amount){
        double newClearance = this.clearance - amount;
        if (newClearance >= -this.overdraft){
            this.clearance -= amount;
        } else {
            System.out.printf("Opération refusée. Retirer %.2f€ à votre compte vous ferait passer à %.2f€, ce qui excède votre découvert autorisé de %.2f\n",amount,newClearance,this.overdraft);
        }


    }
}

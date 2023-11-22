package org.example;

public abstract class Account {
    protected int code;
    protected double clearance;
    private static int counter;

    public Account() {
        this.code = counter;
    }

    {
        counter++;
    }

    public void debit(double amount){
        this.clearance -= amount;
    }

    public void credit(double amount){
        this.clearance += amount;
    }

    @Override
    public String toString(){
        String formattedClearance = String.format("%.2f", this.clearance);
        return "Compte numéro " + this.code +"\nSolde : " + formattedClearance + "€";
    }


}

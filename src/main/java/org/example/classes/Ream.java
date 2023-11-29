package org.example.classes;

public class Ream extends UniqueArticle {
    private int weight;


    public Ream(String name, float price, int weight) {
        super(name, price);
        this.weight = weight;
    }


    @Override
    public String toString() {
        return String.format("%s : ramette de %dg", super.toString(),this.weight);
    }
}

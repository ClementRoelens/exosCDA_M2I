package org.example;

public class Chaise {
    private int feetNumber;
    private String color;
    private String material;

    public Chaise() {
    }

    public Chaise(int feetNumber, String color, String material) {
        this.feetNumber = feetNumber;
        this.color = color;
        this.material = material;
    }

    @Override
    public String toString() {
        return "Je suis une Chaise, avec " + feetNumber + " pied(s) en " + material + " et de couleur " +  color;
    }
}

package org.example.classes;

public class Pencil extends UniqueArticle {
    private String color;


    public Pencil(String name, float price, String color) {
        super(name, price);
        this.color = color;
    }


    @Override
    public String toString() {
        return String.format("%s : stylo de couleur %s",super.toString(),this.color);
    }
}

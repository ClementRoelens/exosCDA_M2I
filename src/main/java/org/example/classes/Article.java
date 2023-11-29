package org.example.classes;

public abstract class Article {
    private int id;
    private static int count;


    public Article() {
        this.id = count++;
    }


    public int getId() {
        return id;
    }

    public abstract String getName();
    public abstract float getPrice();

    @Override
    public String toString() {
        return String.format("Article numéro %d", id);
    }
}

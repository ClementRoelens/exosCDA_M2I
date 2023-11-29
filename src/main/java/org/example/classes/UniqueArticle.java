package org.example.classes;

public abstract class UniqueArticle extends Article {
    private String name;
    private float price;


    public UniqueArticle(String name, float price) {
        super();
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s : %s coûtant %.2f€", super.toString(),this.name, this.price);
    }
}

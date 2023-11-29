package org.example.classes;

public class Batch extends Article {
    private Article article;
    private short quantity;
    private byte discountRate;


    public Batch(Article article, short quantity, byte discountRate) {
        this.article = article;
        this.quantity = quantity;
        this.discountRate = discountRate;
    }

    @Override
    public String getName(){
        return String.format("Lot de %d %s",this.quantity,this.article.getName());
    }

    @Override
    public float getPrice() {
        return this.article.getPrice()*this.quantity*((100f-this.discountRate)/100f);
    }

    @Override
    public String toString() {
        return String.format("%s : lot de %d %s. Remise de %d%% pour un prix final total de %.2f€",super.toString(),this.quantity,this.article.getName(),this.discountRate,this.getPrice());
    }
}

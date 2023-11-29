package org.example.classes;

public class BuyLine {
    private Article article;
    private int quantity;


    public BuyLine(Article article, int quantity) {
        this.article = article;
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return String.format("Quantité : %d - Ref article : %d - %s - Prix unitaire : %.2f€ - Prix total : %.2f€ \n",
                this.quantity, this.article.getId(), this.article.getName(), this.article.getPrice(), this.getTotaltPrice());
    }

    public float getTotaltPrice(){
        return this.article.getPrice()*this.quantity;
    }
}

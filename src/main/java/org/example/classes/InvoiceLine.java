package org.example.classes;

public class InvoiceLine {
    private Article article;
    private byte quantity;


    public InvoiceLine(Article article, byte quantity) {
        this.article = article;
        this.quantity = quantity;
    }


    public float getTotalPrice() {
        return this.article.getPrice() * this.quantity;
    }
    @Override
    public String toString() {
        return String.format("Quantité : %d - Ref article : %d - %s - Prix unitaire : %.2f€ - Prix total : %.2f€ \n",
                this.quantity, this.article.getId(), this.article.getName(), this.article.getPrice(), this.getTotalPrice());
    }
}

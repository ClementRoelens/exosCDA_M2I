package org.example.classes;


import java.util.Arrays;

public class Invoice {
    private int id;
    private final int LINES_NUMBER_MAX = 10;
    private int emptyLines;
    private String client;
    private String date;
    private BuyLine[] lines;
    private static int count;


    public Invoice(String client, String date) {
        this.id = count++;
        this.client = client;
        this.date = date;
        this.lines = new BuyLine[this.LINES_NUMBER_MAX];
        this.emptyLines = this.LINES_NUMBER_MAX;
    }


    @Override
    public String toString() {
        String message = String.format("Facture numéro %d pour mr/me %s, éditée au %s.\n",this.id, this.client, this.date);
        for (BuyLine line : this.lines){
            if (line == null){
                break;
            }
            message += line;
        }
       message = String.format("%sPrix total : %.2f€",message ,this.getTotalPrice());
        return message;
    }

    public boolean addLine(int articleId, int quantity) {
        if (this.emptyLines > 0) {
            Article article = ArticleService.getOneArticle(articleId);
            if (article != null) {
                this.lines[this.LINES_NUMBER_MAX - this.emptyLines] = new BuyLine(article, quantity);
                this.emptyLines--;

                return true;
            }
            return false;
        }
        return false;
    }

    public float getTotalPrice() {
        float totalPrice = 0f;
        for (BuyLine line : this.lines) {
            if (line == null) {
                break;
            }
            totalPrice += line.getTotaltPrice();
        }
        return totalPrice;
    }
}

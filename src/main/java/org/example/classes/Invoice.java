package org.example.classes;


import java.util.Arrays;

public class Invoice {
    private int id;
    private short linesNumberMax;
    private short emptyLines;
    private String client;
    private String date;
    private InvoiceLine[] lines;
    private static int count;


    public Invoice(String client, String date) {
        this(client, date, (short) 10);
    }

    public Invoice(String client, String date, short linesNumberMax) {
        this.id = count++;
        this.client = client;
        this.date = date;
        this.linesNumberMax = linesNumberMax;
        this.lines = new InvoiceLine[linesNumberMax];
        this.emptyLines = linesNumberMax;
    }


    @Override
    public String toString() {
        String message = String.format("Facture numéro %d pour mr/me %s, éditée au %s.\n", this.id, this.client, this.date);
        for (InvoiceLine line : this.lines) {
            if (line == null) {
                break;
            }
            message += line;
        }
        message = String.format("%sPrix total : %.2f€", message, this.getTotalPrice());
        return message;
    }

    public boolean addLine(int articleId, byte quantity) {
        if (this.emptyLines > 0) {
            Article article = ArticleService.getOneArticle(articleId);
            if (article != null) {
                this.lines[this.linesNumberMax - this.emptyLines] = new InvoiceLine(article, quantity);
                this.emptyLines--;

                return true;
            }
            return false;
        }
        return false;
    }

    public float getTotalPrice() {
        float totalPrice = 0f;
        for (InvoiceLine line : this.lines) {
            if (line == null) {
                break;
            }
            totalPrice += line.getTotalPrice();
        }
        return totalPrice;
    }
}

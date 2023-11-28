package org.example;

public class PaperBook extends Book {
    private int pageNumber;
    private String publisher;


    public PaperBook(String title, String author, int pageNumber, String publisher){
        super(title,author);
        this.pageNumber = pageNumber;
        this.publisher = publisher;
    }


    @Override
    public String toString() {
        return String.format("%s de %d pages, écrit par %s et publié par %s, a l'id %d", this.title, this.pageNumber, this.author, this.publisher, this.id);
    }
}

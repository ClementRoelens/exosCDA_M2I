package org.example.TP_1;

import org.example.TP_2.Client;

public class Book {
    private int id;
    private String name;
    private Author[] authors;
    private Publisher publisher;
    private int publishingYear;
    private int amountOfPages;
    private double price;
    private CoverType coverType;
    private static int counter;



    public Book(String name, Author[] authors, Publisher publisher, int publishingYear, int amountOfPages, double price, CoverType coverType) {
        this.id = counter;
        this.name = name;
        this.authors = authors;
        this.publisher = publisher;
        this.publishingYear = publishingYear;
        this.amountOfPages = amountOfPages;
        this.price = price;
        this.coverType = coverType;
    }

    { counter++; }


    @Override
    public String toString(){
        String message = String.format("Je suis un livre, %s sorti en %d, coûtant %.2f pour %d pages et mon id est %d.\n " +
                        "Voici les infos sur mon éditeur : %s\nEt celles sur mes auteurs : ",
                this.name, this.publishingYear, this.price, this.amountOfPages, this.id, this.publisher.toString());
        for (Author author : this.authors){
            message += author.toString();
        }
        return message;
    }

    public String getName(){
        return name;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public int getPublishingYear() {
        return publishingYear;
    }
}

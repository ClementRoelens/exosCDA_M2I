package org.example;

public abstract class Book {
    protected int id;
    protected String title;
    protected String author;
    protected static int count;


    public Book(String title, String author) {
        this.id = count++;
        this.title = title;
        this.author = author;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return String.format("%s écrit par %s a l'id %d", this.title, this.author, this.id);
    }
}

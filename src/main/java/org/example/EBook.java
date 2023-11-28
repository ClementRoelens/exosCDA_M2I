package org.example;

public class EBook extends Book {
    private String extension;
    private int size;


    public EBook(String title, String author, String extension, int size){
        super(title,author);
        this.extension = extension;
        this.size = size;
    }


    @Override
    public String toString() {
        return String.format("%s écrit par %s, d'extension %s et de %d octets a l'id %d", this.title, this.author, this.extension, this.size, this.id);
    }
}

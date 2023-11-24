package org.example.TP_1;

public class Publisher {
    private int id;
    private String publisherName;

    private static int counter;

    public Publisher(String publisherName){
        this.id = counter;
        this.publisherName = publisherName;
    }

    { counter++; }


    @Override
    public String toString() {
        return String.format("Je suis un éditeur, %s d'id %d", this.publisherName, this.id);
    }
}
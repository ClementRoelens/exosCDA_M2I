package org.example.TP_2;

public class Room {
    private int id;
    private Status status;
    private double price;
    private int capacity;
    private static int counter;


    public Room(double price, int capacity) {
        this.id = counter;
        this.price = price;
        this.capacity = capacity;
        this.status = Status.VACANT;
    }

    { counter++; }


    public Status getStatus(){
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return this.id;
    }
}

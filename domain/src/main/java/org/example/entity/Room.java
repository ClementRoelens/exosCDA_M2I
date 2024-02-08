package org.example.entity;

public class Room {
    private int id;
    private int capacity;
    private boolean isFree;


    public Room(int capacity)  {
       this.capacity = capacity;
        isFree = false;
    }

    public Room(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        isFree = false;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}

package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonProperty
public class RoomDTO {
    private int id;
    private int capacity;
    private boolean isFree;


    public RoomDTO() {
    }

    public RoomDTO(int capacity)  {
        this.capacity = capacity;
        isFree = false;
    }

    public RoomDTO(int id, int capacity) {
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

package org.example.dto;

import org.example.entity.Room;

import java.time.LocalDateTime;

public class BookingDTO {
    private int id;
    private LocalDateTime dateTime;
    private int duration;
    private Room room;


    public BookingDTO(LocalDateTime dateTime,int duration, Room room) {
        this.dateTime = dateTime;
        this.duration = duration;
        this.room = room;
    }

    public BookingDTO(int id, LocalDateTime dateTime,int duration, Room room) {
        this.id = id;
        this.dateTime = dateTime;
        this.duration = duration;
        this.room = room;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}

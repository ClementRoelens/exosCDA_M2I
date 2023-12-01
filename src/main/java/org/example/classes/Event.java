package org.example.classes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Event extends Element {
    private String name;
    private LocalDate date;
    private LocalTime time;
    private Place place;
    private float price;
    private int soldTicketsNumber;



    public Event(String name, LocalDate date, LocalTime time, Place place, float price) {
        super();
        this.name = name;
        this.date = date;
        this.time = time;
        this.place = place;
        this.price = price;
    }


    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return String.format("Événement numéro %d. %s ayant lieu le %s à %s, au %s. %d billets vendus à %.2f€",
                this.id, this.name, this.getDate(),this.getTime(),this.place.getName(), this.soldTicketsNumber,this.price);
    }
    public boolean isTicketAvailable(){
        return this.soldTicketsNumber < this.place.getCapacity();
    }
    public void sellTicket(){
        this.soldTicketsNumber++;
    }
    public void returnTicket(){
        this.soldTicketsNumber--;
    }
}


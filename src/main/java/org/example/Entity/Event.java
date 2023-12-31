package org.example.Entity;

import org.example.Exception.CustomFormatException;
import org.example.Exception.TicketSoldException;

import java.time.LocalDateTime;

public class Event {
    private int id;
    private String name;
    private LocalDateTime dateTime;
    private EventLocation eventLocation;
    private int eventLocationId;
    private double price;
    private int ticketsSoldNumber;


    // Pour créer un Event depuis l'IHM
    public Event(String name, LocalDateTime dateTime, int eventLocationId, double price) throws CustomFormatException {
        setName(name);
        this.dateTime = dateTime;
        setEventLocationId(eventLocationId);
        setPrice(price);
    }

    // Récupéré depuis la DB
    public Event(int id, String name, LocalDateTime dateTime, int eventLocationId, double price, int ticketsSoldNumber) throws CustomFormatException {
        this(name,dateTime,eventLocationId,price);
        this.id = id;
        this.ticketsSoldNumber = ticketsSoldNumber;
    }




    @Override
    public String toString() {
        return String.format("%d - %s au %s le %s\n%d places disponibles",
                id, name, eventLocation.getName(), dateTime, eventLocation.getCapacity()-getTicketsSoldNumber());
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws CustomFormatException {
        if(name.length() > 2) {
            this.name = name;
        }else {
            throw new CustomFormatException("name should be gt 2 char");
        }
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) throws CustomFormatException {
        if(this.dateTime.isAfter(LocalDateTime.now())) {
            this.dateTime = dateTime;
        }else {
            throw new CustomFormatException("Datetime should be after then current datetime");
        }
    }

    public EventLocation getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(EventLocation eventLocation) {
        this.eventLocation = eventLocation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws CustomFormatException {
        if(price >= 0) {
            this.price = price;
        }
        else {
            throw new CustomFormatException("price should be positive");
        }
    }

    public int getTicketsSoldNumber() {
        return ticketsSoldNumber;
    }

    public int getEventLocationId() {
        return eventLocationId;
    }

    public void setEventLocationId(int eventLocationId) {
        this.eventLocationId = eventLocationId;
    }

    public boolean checkTicketSoldPossibility() {
        return ticketsSoldNumber < eventLocation.getCapacity();
    }

    public void cancelTicket() {
        if(ticketsSoldNumber == 0) {
            throw new TicketSoldException("No Ticket available to cancel");
        }
        ticketsSoldNumber--;
    }

    public void soldTicket() {
        if(!checkTicketSoldPossibility()){
            throw new TicketSoldException("Ticket sold out");
        }
        ticketsSoldNumber++;
    }


}

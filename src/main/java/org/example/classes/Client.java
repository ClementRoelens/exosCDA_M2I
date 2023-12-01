package org.example.classes;

import org.example.Exceptions.ElementDoesNotExistException;

import java.util.ArrayList;

public class Client extends Element {
    private String firstname;
    private String lastname;
    private String email;
    private ArrayList<Event> boughtTicketsEvents;


    public Client(String firstname, String lastname, String email) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.boughtTicketsEvents = new ArrayList<>();
    }


    @Override
    public String toString() {
        String message = String.format("Client numéro %d : %s %s, joignable à %s.\nIl a acheté des billets pour :",
                this.id, this.firstname, this.lastname, this.email);
        for (Event event : this.boughtTicketsEvents) {
            message = String.format("%s\n%s", message, event);
        }
        return message;
    }

    public boolean buyTicket(int eventId) {
        try {
            Event event = Service.getOneEvent(eventId);
            if (event.isTicketAvailable()) {
                event.sellTicket();
                this.boughtTicketsEvents.add(event);

                return true;
            }
            return false;
        } catch (ElementDoesNotExistException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean returnTicket(int eventId) {
        try {
            Event event = Service.getOneEvent(eventId);
            event.returnTicket();
            this.boughtTicketsEvents.remove(event);
            return true;
        } catch (ElementDoesNotExistException e) {
            System.out.println(e);
            return false;
        }
    }
}

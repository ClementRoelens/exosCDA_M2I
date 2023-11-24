package org.example.TP_2;

import java.util.Arrays;

public class Reservation {
    private int id;
    private Client client;
    private int occupantsNumber;
    private int[] roomsNumber;
    private static int counter;


    public Reservation(Client client, int occupantsNumber, int[] roomsNumber) {
        this.id = counter;
        this.client = client;
        this.occupantsNumber = occupantsNumber;
        this.roomsNumber = roomsNumber;
    }

    { counter++; }


    public Client getClient() {
        return this.client;
    }

    public int getId(){
        return this.id;
    }

    public int[] getRoomsNumber(){
        return this.roomsNumber;
    }

    @Override
    public String toString() {
        String message = String.format("Réservation numéro %d par le client numéro %d, pour %d occupants. Les chambres concernées sont ", this.id, this.client.getId(), this.occupantsNumber);
        for (int roomNumber : this.roomsNumber){
            message += roomNumber + ", ";
        }
        return message;
    }
}

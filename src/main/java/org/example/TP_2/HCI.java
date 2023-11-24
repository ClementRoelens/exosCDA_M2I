package org.example.TP_2;

import java.util.Arrays;

public class HCI {
    private Hostel hostel;


    public HCI(Hostel hostel) {
        this.hostel = hostel;
    }


    public void addClient(Client client){
        this.hostel.addClient(client);
    }

    public void printClients(){
        String message = "";
        for (Client client : this.hostel.getClients()){
            message += client.getFirstName() + " " + client.getLastName();
        }
        System.out.println(message);
    }

    public void printReservationsFromOneClient(int clientPhoneNumber){
        Reservation[] reservations = new Reservation[0];
        for (Reservation reservation : this.hostel.getReservations()){
            if (reservation.getClient().getPhoneNumber() == clientPhoneNumber){
                reservations = Arrays.copyOf(reservations,reservations.length+1);
                reservations[reservations.length-1] = reservation;
            }
        }
        for (Reservation reservation : reservations){
            System.out.println(reservation);
        }
    }


    public void addReservation(int clientId, int occupantsNumber){
        Reservation reservation = this.hostel.addReservation(clientId,occupantsNumber);
        if (reservation != null){
            System.out.println("Réservation effectuée !" + reservation);
        } else {
            System.out.println("La réservation n'a pas pu être effecutée. Soit il");
        }
    }
}

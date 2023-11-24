package org.example.TP_2;

import java.util.Arrays;

public class HCI {
    private Hostel hostel;


    public HCI(Hostel hostel) {
        this.hostel = hostel;
    }


    public void addClient(String firstName,String lastName,String phoneNumber){
        Client client = new Client(firstName,lastName,phoneNumber);
        this.hostel.addClient(client);
        System.out.printf("Félicitations, vous êtes le client %d\n", client.getId());
    }

    public void printClients(){
        String message = "";
        for (Client client : this.hostel.getClients()){
            message += client.getFirstName() + " " + client.getLastName() + "\n";
        }
        System.out.println(message);
    }

    public void printReservationsFromOneClient(String clientPhoneNumber){
        Reservation[] reservations = new Reservation[0];
        for (Reservation reservation : this.hostel.getReservations()){
            if (reservation.getClient().getPhoneNumber().equals(clientPhoneNumber)){
                reservations = Arrays.copyOf(reservations,reservations.length+1);
                reservations[reservations.length-1] = reservation;
            }
        }
        for (Reservation reservation : reservations){
            System.out.println(reservation);
        }
    }

    public void printAllReservations(){
        for (Reservation reservation : this.hostel.getReservations()){
            System.out.println(reservation);
        }
    }

    public void addReservation(int clientId, int occupantsNumber){
        Client client = this.hostel.findClient(clientId);
        if (client != null){
            if (this.hostel.getVacantRooms().length >= occupantsNumber){
                System.out.println("Réservation effectuée !\n" + this.hostel.addReservation(client,occupantsNumber));
            } else {
                System.out.printf("La réservation n'a pas pu être effecutée. Il n'y a pas %d chambres de libre", occupantsNumber);
            }
        } else {
            System.out.println("Ce client n'a pas été trouvé dans notre base de données");
        }
    }

    public void cancelReservation(int reservationId){
        this.hostel.cancelReservation(reservationId);
    }
}

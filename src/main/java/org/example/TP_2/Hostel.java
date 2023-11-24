package org.example.TP_2;

import java.util.Arrays;

public class Hostel {
    private String name;
    private Room[] rooms;
    private Reservation[] reservations;
    private Client[] clients;


    public Hostel() {
        this.name = "Java Hostel";
        this.rooms = new Room[]{
                new Room(50,2),
                new Room(50,2),
                new Room(50,2),
                new Room(50,2),
                new Room(100,2),
                new Room(100,2),
                new Room(100,2),
                new Room(100,2),
                new Room(80,4),
                new Room(80,4),
                new Room(80,4),
                new Room(80,4),
                new Room(80,4),
                new Room(80,4),
                new Room(200,4),
                new Room(200,4),
                new Room(200,4),
                new Room(200,4)
        };
        this.reservations = new Reservation[0];
        this.clients = new Client[0];
    }

    public void addClient(Client client){
        this.clients = Arrays.copyOf(this.clients, this.clients.length+1);
        this.clients[this.clients.length-1] = client;
    }

    public Client[] getClients() {
        return clients;
    }

    public Reservation[] getReservations() {
        return reservations;
    }

    public Room[] getVacantRooms(){
        Room[] vacantRooms = new Room[0];
        for (Room room : rooms){
            if (room.getStatus() == Status.VACANT){
                vacantRooms = Arrays.copyOf(vacantRooms, vacantRooms.length+1);
                vacantRooms[vacantRooms.length-1] = room;
            }
        }
        return vacantRooms;
    }

    public Reservation addReservation(int clientId, int occupantsNumber){
        if (this.getVacantRooms().length >= occupantsNumber){
            Client client = Arrays.stream(this.clients)
                    .filter(tempClient -> tempClient.getId() == clientId)
                    .findFirst()
                    .orElse(null);

            if (client == null) {
                return null;
            }

            Reservation newReservation = new Reservation(client,occupantsNumber);
            this.reservations = Arrays.copyOf(this.reservations, this.reservations.length+1);
            this.reservations[this.reservations.length-1] = newReservation;

            return newReservation;
        }
        return null;
    }
}

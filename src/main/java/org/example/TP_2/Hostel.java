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
                new Room(50, 2),
                new Room(50, 2),
                new Room(50, 2),
                new Room(50, 2),
                new Room(100, 2),
                new Room(100, 2),
                new Room(100, 2),
                new Room(100, 2),
                new Room(80, 4),
                new Room(80, 4),
                new Room(80, 4),
                new Room(80, 4),
                new Room(80, 4),
                new Room(80, 4),
                new Room(200, 4),
                new Room(200, 4),
                new Room(200, 4),
                new Room(200, 4)
        };
        this.reservations = new Reservation[0];
        this.clients = new Client[0];
    }

    public void addClient(Client client) {
        this.clients = Arrays.copyOf(this.clients, this.clients.length + 1);
        this.clients[this.clients.length - 1] = client;
    }

    public Client[] getClients() {
        return clients;
    }

    public Reservation[] getReservations() {
        return reservations;
    }

    public Room[] getVacantRooms() {
        Room[] vacantRooms = new Room[0];
        for (Room room : rooms) {
            if (room.getStatus() == Status.VACANT) {
                vacantRooms = Arrays.copyOf(vacantRooms, vacantRooms.length + 1);
                vacantRooms[vacantRooms.length - 1] = room;
            }
        }
        return vacantRooms;
    }

    public Reservation addReservation(Client client, int occupantsNumber) {
        int[] vacantRoomsNumbers = Arrays.stream(
                        Arrays.stream(this.rooms).filter(room -> room.getStatus() == Status.VACANT).toArray(Room[]::new))
                .mapToInt(Room::getId).toArray();
        int[] roomsNumber = new int[0];
        for (int i = 0; i < occupantsNumber; i++) {
            roomsNumber = Arrays.copyOf(roomsNumber,roomsNumber.length+1);
            roomsNumber[roomsNumber.length-1] = vacantRoomsNumbers[i];
            this.makeRoomTaken(roomsNumber[i]);
        }
        Reservation newReservation = new Reservation(client, occupantsNumber, roomsNumber);
        this.reservations = Arrays.copyOf(this.reservations, this.reservations.length + 1);
        this.reservations[this.reservations.length - 1] = newReservation;

        return newReservation;
    }

    public Client findClient(int clientId) {
        return Arrays.stream(this.clients)
                .filter(tempClient -> tempClient.getId() == clientId)
                .findFirst().orElse(null);
    }

    private void makeRoomVacant(int roomId) {
        Room room = Arrays.stream(this.rooms)
                .filter(tempRoom -> tempRoom.getId() == roomId)
                .findFirst().orElse(null);
        room.setStatus(Status.VACANT);
    }

    private void makeRoomTaken(int roomId){
        Room room = Arrays.stream(this.rooms)
                .filter(tempRoom -> tempRoom.getId() == roomId)
                .findFirst().orElse(null);
        room.setStatus(Status.TAKEN);
    }

    public boolean cancelReservation(int reservationId) {
        Reservation reservation = Arrays.stream(this.reservations)
                .filter(tempReservation -> tempReservation.getId() == reservationId)
                .findFirst().orElse(null);
        if (reservation == null) {
            return false;
        }

        for (int roomNumber : reservation.getRoomsNumber()) {
            this.makeRoomVacant(roomNumber);
        }
        this.reservations = Arrays.stream(this.reservations)
                .filter(tempReservation -> tempReservation.getId() != reservationId)
                .toArray(Reservation[]::new);
        return true;
    }
}

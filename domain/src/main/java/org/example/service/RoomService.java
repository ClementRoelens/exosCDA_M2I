package org.example.service;

import org.example.entity.Room;
import org.example.exception.NegativeOrNullException;
import org.example.spi.port.RoomRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RoomService {
    private final RoomRepository _roomRepository;

    public RoomService(RoomRepository roomRepository) {
        _roomRepository = roomRepository;
    }

    public Room createRoom(int capacity) throws NegativeOrNullException {
        if (capacity <= 0){
            throw new NegativeOrNullException();
        }
        Room room = new Room(capacity);
        return _roomRepository.createRoom(room);
    }

    public boolean updateRoom(Room updatedRoom){
        if (_roomRepository.read(updatedRoom.getId()) == null){
            return false;
        }
        return _roomRepository.updateRoom(updatedRoom);
    }

    public boolean deleteRoom(int id){
        Room room = _roomRepository.read(id);
        if (room == null){
            return false;
        }
        return _roomRepository.deleteRoom(room);
    }

    public List<Room> seekFreeRoomsUponDateTime(LocalDateTime dateTime){
        return _roomRepository.seekFreeRoomsUponDateTime(dateTime);
    }

    public List<Room> seekFreeRoomsUponCapacity(int capacity) throws NegativeOrNullException {
        if (capacity <= 0){
            throw new NegativeOrNullException();
        }
        return _roomRepository.seekFreeRoomsUponCapacity(capacity);
    }

    public List<Room> seekFreeRoomsUponCapacityAndDateTime(int capacity, LocalDateTime dateTime) throws NegativeOrNullException {
        List<Room> freeRoomsUponCapacity;
        List<Room> freeRoomsUponDateTime;
        List<Room> freeRooms = new ArrayList<>();

        if (capacity <= 0){
            throw new NegativeOrNullException();
        }

        freeRoomsUponCapacity = _roomRepository.seekFreeRoomsUponCapacity(capacity);
        freeRoomsUponDateTime = _roomRepository.seekFreeRoomsUponDateTime(dateTime);

        for (Room r : freeRoomsUponCapacity){
            if (freeRoomsUponDateTime.contains(r)){
                freeRooms.add(r);
            }
        }

        return freeRooms;
    }
}

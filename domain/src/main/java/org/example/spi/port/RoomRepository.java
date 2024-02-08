package org.example.spi.port;

import org.example.entity.Room;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomRepository {
    Room read(int id);
    Room createRoom(Room room);
    boolean updateRoom(Room room);
    boolean deleteRoom(Room room);
    List<Room> seekFreeRoomsUponCapacity(int capacity);
    List<Room> seekFreeRoomsUponDateTime(LocalDateTime dateTime);
}

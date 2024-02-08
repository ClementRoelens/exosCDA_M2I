package entity;

import jakarta.persistence.*;
import org.example.entity.Room;

import java.util.List;

@Entity
@Table(name = "room")
public class RoomHibernate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int capacity;
    private boolean isFree;
    @OneToMany(mappedBy = "room")
    private List<BookingHibernate> bookings;


    public RoomHibernate() {
    }

    public RoomHibernate(int capacity, boolean isFree) {
        this.capacity = capacity;
        this.isFree = isFree;
    }

    public RoomHibernate(int id, int capacity, boolean isFree) {
        this.id = id;
        this.capacity = capacity;
        this.isFree = isFree;
    }

    public RoomHibernate(Room room){
        this.id = room.getId();
        this.capacity = room.getCapacity();
        this.isFree = room.isFree();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public List<BookingHibernate> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingHibernate> bookings) {
        this.bookings = bookings;
    }

    public void addBooking(BookingHibernate booking){
        bookings.add(booking);
    }

    public Room toRoom(){
        return new Room(capacity);
    }
}

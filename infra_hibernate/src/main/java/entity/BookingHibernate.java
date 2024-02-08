package entity;

import jakarta.persistence.*;
import org.example.entity.Booking;

import java.time.LocalDateTime;

@Entity
@Table(name = "booking_hibernate")
public class BookingHibernate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    private int duration;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomHibernate room;


    public BookingHibernate() {
    }

    public BookingHibernate(LocalDateTime dateTime, int duration, RoomHibernate room) {
        this.dateTime = dateTime;
        this.duration = duration;
        this.room = room;
    }

    public BookingHibernate(int id, LocalDateTime dateTime, int duration, RoomHibernate room) {
        this.id = id;
        this.dateTime = dateTime;
        this.duration = duration;
        this.room = room;
    }

    public BookingHibernate(Booking booking){
        this.id = booking.getId();
        this.dateTime = booking.getDateTime();
        this.duration = booking.getDuration();
        this.room = new RoomHibernate(booking.getRoom());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public RoomHibernate getRoom() {
        return room;
    }

    public void setRoom(RoomHibernate room) {
        this.room = room;
    }

    public Booking toBooking(){
        return new Booking(dateTime,duration,room.toRoom());
    }
}

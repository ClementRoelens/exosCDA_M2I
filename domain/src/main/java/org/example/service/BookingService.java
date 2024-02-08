package org.example.service;

import org.example.entity.Booking;
import org.example.entity.Room;
import org.example.exception.NegativeOrNullException;
import org.example.spi.port.BookingRepository;

import java.time.LocalDateTime;
import java.util.List;

public class BookingService {
    private final BookingRepository _bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        _bookingRepository = bookingRepository;
    }


    public Booking createBooking(LocalDateTime dateTime, int duration, Room room) throws NegativeOrNullException{
        if (duration <= 0){
            throw new NegativeOrNullException();
        }
        Booking booking = new Booking(dateTime,duration,room);
        return _bookingRepository.createBooking(booking);
    }

    public List<Booking> read(){
        return _bookingRepository.read();
    }
}

package org.example.spi.port;

import org.example.entity.Booking;
import java.util.List;

public interface BookingRepository {
    Booking createBooking(Booking booking);
    List<Booking> read();

}

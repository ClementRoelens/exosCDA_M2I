package repository.impl;

import entity.BookingHibernate;
import org.example.entity.Booking;
import org.example.spi.port.BookingRepository;
import org.hibernate.Session;
import repository.BookingHibernateRepository;
import util.HibernateSession;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookingRepositoryImpl implements BookingRepository {

    private BookingHibernateRepository _bookingHibernateRepository;

    public BookingRepositoryImpl(BookingHibernateRepository bookingHibernateRepository){
        _bookingHibernateRepository = bookingHibernateRepository;
    }

    @Override
    public Booking createBooking(Booking booking) {
        Session session = HibernateSession.getSessionFactory().openSession();
        session.beginTransaction();

        try (session) {
            _bookingHibernateRepository.setSession(session);
            BookingHibernate bookingHibernate = new BookingHibernate(booking);
            _bookingHibernateRepository.create(bookingHibernate);
            session.getTransaction().commit();
            booking.setId(bookingHibernate.getId());
        } catch (Exception e){
            System.out.println(e);
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            booking = null;
        }

        return booking;
    }

    @Override
    public List<Booking> read() {
        List<Booking> bookings = new ArrayList<>();
        Session session = HibernateSession.getSessionFactory().openSession();
        session.beginTransaction();

        try (session) {
            _bookingHibernateRepository.setSession(session);
            List<BookingHibernate> bookingsHibernate = _bookingHibernateRepository.read();
            session.getTransaction().commit();
            bookings = bookingsHibernate.stream().map(BookingHibernate::toBooking).collect(Collectors.toList());
        } catch (Exception e){
            System.out.println(e);
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
        }

        return bookings;
    }
}

package repository.impl;

import entity.BookingHibernate;
import entity.RoomHibernate;
import org.example.entity.Booking;
import org.example.entity.Room;
import org.example.spi.port.RoomRepository;
import org.hibernate.Session;
import repository.BookingHibernateRepository;
import repository.RoomHibernateRepository;
import util.HibernateSession;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoomRepositoryImpl implements RoomRepository {

    private RoomHibernateRepository _roomHibernateRepository;
    private BookingHibernateRepository _bookingHibernateRepository;


    public RoomRepositoryImpl(
            RoomHibernateRepository roomHibernateRepository,
            BookingHibernateRepository bookingHibernateRepository
                              ){
        _roomHibernateRepository = roomHibernateRepository;
        _bookingHibernateRepository = bookingHibernateRepository;
    }

    @Override
    public Room read(int id) {
        Room room = null;
        Session session = HibernateSession.getSessionFactory().openSession();
        session.beginTransaction();

        try (session) {
             _roomHibernateRepository.setSession(session);
            RoomHibernate roomHibernate = _roomHibernateRepository.read(id);
            session.getTransaction().commit();
            room = roomHibernate.toRoom();
        } catch (Exception e){
            System.out.println(e);
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
        }

        return room;
    }

    @Override
    public Room createRoom(Room room) {
        Session session = HibernateSession.getSessionFactory().openSession();
        session.beginTransaction();

        try (session) {
            _roomHibernateRepository.setSession(session);
            RoomHibernate roomHibernate = new RoomHibernate(room);
            _roomHibernateRepository.create(roomHibernate);
            session.getTransaction().commit();
            room.setId(roomHibernate.getId());
        } catch (Exception e){
            System.out.println(e);
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
        }

        return room;
    }

    @Override
    public boolean updateRoom(Room room) {
        boolean result = false;
        Session session = HibernateSession.getSessionFactory().openSession();
        session.beginTransaction();

        try (session) {
            _roomHibernateRepository.setSession(session);
            RoomHibernate roomHibernate = new RoomHibernate(room);
            _roomHibernateRepository.update(roomHibernate);
            session.getTransaction().commit();
            result = true;
        } catch (Exception e){
            System.out.println(e);
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
        }

        return result;
    }

    @Override
    public boolean deleteRoom(Room room) {
        boolean result = false;
        Session session = HibernateSession.getSessionFactory().openSession();
        session.beginTransaction();

        try (session) {
            _roomHibernateRepository.setSession(session);
            RoomHibernate roomHibernate = new RoomHibernate(room);
            _roomHibernateRepository.delete(roomHibernate);
            session.getTransaction().commit();
            result = true;
        } catch (Exception e){
            System.out.println(e);
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
        }

        return result;
    }

    @Override
    public List<Room> seekFreeRoomsUponCapacity(int capacity) {
        List<Room> rooms = new ArrayList<>();
        Session session = HibernateSession.getSessionFactory().openSession();
        session.beginTransaction();

        try (session) {
            _roomHibernateRepository.setSession(session);
            List<RoomHibernate> roomsHibernate = _roomHibernateRepository.seekFreeRoomsUponCapacity(capacity);
            session.getTransaction().commit();
            rooms = roomsHibernate.stream().map(RoomHibernate::toRoom).collect(Collectors.toList());
        } catch (Exception e){
            System.out.println(e);
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
        }

        return rooms;
    }

    @Override
    public List<Room> seekFreeRoomsUponDateTime(LocalDateTime dateTime) {
        List<Room> rooms = new ArrayList<>();
        Session session = HibernateSession.getSessionFactory().openSession();
        session.beginTransaction();

        try (session) {
            _roomHibernateRepository.setSession(session);
            List<BookingHibernate> bookingsAtThisDateTime = _bookingHibernateRepository.getAllBookingsAtOneDateTime(dateTime);
            List<RoomHibernate> roomsHibernate = _roomHibernateRepository.read();
            session.getTransaction().commit();
            for (BookingHibernate bh : bookingsAtThisDateTime){
                if (!roomsHibernate.contains(bh.getRoom())){
                    roomsHibernate.remove(bh.getRoom());
                }
            }
            rooms = roomsHibernate.stream().map(RoomHibernate::toRoom).collect(Collectors.toList());
        } catch (Exception e){
            System.out.println(e);
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
        }

        return rooms;
    }
}

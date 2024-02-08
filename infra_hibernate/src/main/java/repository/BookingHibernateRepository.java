package repository;

import entity.BookingHibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.List;

public class BookingHibernateRepository {
    private Session _session;


    public BookingHibernateRepository(){
    }

    public Session getSession() {
        return _session;
    }

    public void setSession(Session session) {
        _session = session;
    }

    public void create(BookingHibernate booking){
        _session.persist(booking);
    }

    public List<BookingHibernate> read(){
        return _session.createQuery("FROM BookingHibernate ").list();
    }

    public List<BookingHibernate> getAllBookingsAtOneDateTime(LocalDateTime dateTime){
        Query<BookingHibernate> query = _session.createQuery(
                "FROM BookingHibernate " +
                        "WHERE dateTime = :dateTime"
        );
        query.setParameter("dateTime", dateTime);
        return query.list();
    }
}

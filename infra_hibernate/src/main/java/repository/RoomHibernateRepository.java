package repository;

import entity.RoomHibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.List;

public class RoomHibernateRepository {
    private Session _session;


    public RoomHibernateRepository(){
    }

    public Session getSession() {
        return _session;
    }

    public void setSession(Session session) {
        _session = session;
    }

    public void create(RoomHibernate booking){
        _session.persist(booking);
    }

    public RoomHibernate read(int id){
        return _session.get(RoomHibernate.class,id);
    }

    public List<RoomHibernate> read(){ return _session.createQuery("FROM RoomHibernate ").list(); }

    public void update(RoomHibernate roomHibernate){
        _session.merge(roomHibernate);
    }

    public void delete(RoomHibernate roomHibernate){
        _session.remove(roomHibernate);
    }

    public List<RoomHibernate> seekFreeRoomsUponCapacity(int capacity){
        Query<RoomHibernate> query = _session.createQuery(
                "FROM RoomHibernate " +
                        "WHERE isFree = true " +
                        "AND capacity >= :capacity");
        query.setParameter("capacity", capacity);
        return query.list();
    }
}

package DAO;

import Entities.Command;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CommandDAOImpl implements IDAO<Command> {
    private StandardServiceRegistry standardServiceRegistry;
    private SessionFactory sessionFactory;


    public CommandDAOImpl(){
        standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory();
    }


    @Override
    public Command create(Command command) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            session.merge(command.getAddress());
            session.save(command);
            session.getTransaction().commit();
            return command;
        } catch (Exception e){
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            System.out.println(e);
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Command> read() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Command> commands = new ArrayList<>();
        Query<Command> query;

        try {
            query = session.createQuery("FROM Command");
            commands = query.list();
            session.getTransaction().commit();
        } catch (Exception e){
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            System.out.println(e);
        } finally {
            session.close();
        }

        return commands;
    }

    @Override
    public Command read(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            return session.get(Command.class, id);
        } catch (Exception e){
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            System.out.println(e);
            return null;
        } finally {
            session.close();
        }
    }

    public List<Command> readCommandUponDay(Date date){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Command> commands = new ArrayList<>();
        Query<Command> query;

        try {
            query = session.createQuery("FROM Command WHERE date = :date");
            query.setParameter("date",date);
            commands = query.list();
        }catch (Exception e){
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            System.out.println(e);
        } finally {
            session.close();
        }

        return commands;
    }

    @Override
    public boolean update(Command command) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            session.update(command);
            session.getTransaction().commit();
            return true;
        } catch (Exception e){
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            System.out.println(e);
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(Command command) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            session.delete(command);
            session.getTransaction().commit();
            return true;
        } catch (Exception e){
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            System.out.println(e);
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public void close() {
sessionFactory.close();
    }
}

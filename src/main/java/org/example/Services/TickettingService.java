package org.example.Services;

import org.example.DAO.CustomerDAO;
import org.example.DAO.EventCustomerDAO;
import org.example.DAO.EventDAO;
import org.example.DAO.EventLocationDAO;
import org.example.Entity.Customer;
import org.example.Entity.Event;
import org.example.Entity.EventCustomer;
import org.example.Entity.EventLocation;
import org.example.Exception.CustomFormatException;

import java.time.LocalDateTime;
import java.sql.SQLException;
import java.util.List;

public class TickettingService {
    private CustomerDAO customerDAO;
    private EventDAO eventDAO;
    private EventLocationDAO eventLocationDAO;
    private EventCustomerDAO eventCustomerDAO;


    public TickettingService() {
        try {
            customerDAO = new CustomerDAO();
            eventDAO = new EventDAO();
            eventLocationDAO = new EventLocationDAO();
            eventCustomerDAO = new EventCustomerDAO();
            System.out.println("Connexion à la base de données effectuée");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public Customer getCustomer(int id) {
        try {
            return customerDAO.read(id);
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public Customer createCustomer(String firstname, String lastname, String email) throws CustomFormatException {
        try {
            Customer customer = customerDAO.create(new Customer(firstname, lastname, email));
            System.out.println("Votre compte a bien été créé. Votre id est " + customer.getId());
            return customer;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean editCustomer(int id, String firstname, String lastname, String email) throws CustomFormatException {
        try {
            return customerDAO.update(new Customer(id, firstname, lastname, email));
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteCustomer(int id) {
        try {
            return customerDAO.delete(id);
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public List<EventLocation> getEventLocations() {
        try {
            return eventLocationDAO.read();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public EventLocation getEventLocation(int id) {
        try {
            return eventLocationDAO.read(id);
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public EventLocation createEventLocation(String name, String address, int capacity) throws CustomFormatException {
        try {
            EventLocation eventLocation = eventLocationDAO.create(new EventLocation(name, address, capacity));
            System.out.println("Le lieu a bien été créé. Son id est " + eventLocation.getId());
            return eventLocation;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean editEventLocation(int id, String name, String address, int capacity) throws CustomFormatException {
        try {
            return eventLocationDAO.update(new EventLocation(id, name, address, capacity));
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteEventLocation(int id) {
        try {
            return eventLocationDAO.delete(id);
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public List<Event> getEvents() {
        List<Event> events;
        try {
            events = eventDAO.read();
            for (Event event : events) {
                event.setEventLocation(eventLocationDAO.read(event.getEventLocationId()));
            }
            return events;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public Event getEvent(int id) {
        try {
            return eventDAO.read(id);
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public Event createEvent(String name, LocalDateTime dateTime, int eventLocationId, double price) throws CustomFormatException {
        try {
            Event event = eventDAO.create(new Event(name, dateTime, eventLocationId, price));
            System.out.println("L'événement a bien été créé. Son id est " + event.getId());
            return event;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean editEvent(int id, String name, LocalDateTime dateTime, int eventLocationId, double price, int ticketsSoldNumber) throws CustomFormatException {
        try {
            return eventDAO.update(new Event(id, name, dateTime, eventLocationId, price, ticketsSoldNumber));
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteEvent(int id) {
        try {
            return eventDAO.delete(id);
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean buyTickets(Customer customer, Event event) {
        try {
            if (event.checkTicketSoldPossibility()) {
                event.soldTicket();

                if (eventDAO.update(event)) {

                    List<Event> tickets = customer.getEventsTicket();
                    tickets.add(event);
                    customer.setEventsTicket(tickets);


                    if (eventCustomerDAO.create(new EventCustomer(customer.getId(), event.getId())) != null) {
                        return true;
                    }

                }

                System.out.println("Aïe, quelque chose s'est mal passé...");
                return false;
            }
            System.out.println("Il n'y a plus de billets disponibles pour cet événement");
            return false;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean cancelTickets(Customer customer, Event event) {
        try {
            event.cancelTicket();

            if (eventDAO.update(event)) {

                List<Event> tickets = customer.getEventsTicket();
                tickets.remove(event);
                customer.setEventsTicket(tickets);

                if (eventCustomerDAO.delete(new EventCustomer(customer.getId(), event.getId()))) {
                    return true;
                }

            }

            System.out.println("Aïe, quelque chose s'est mal passé...");
            return false;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public List<Event> getAvailableEvents() {
        try {
            List<Event> events = eventDAO.readAvailableEvents();
            for (Event event : events){
                event.setEventLocation(getEventLocation(event.getEventLocationId()));
            }
            return events;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Event> getTicketsByClient(int clientId) {
        try {
            List<Event> events = eventDAO.readClientTickets(clientId);
            for (Event event : events){
                event.setEventLocation(getEventLocation(event.getEventLocationId()));
            }
            return events;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
}

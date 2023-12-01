package org.example.classes;

import org.example.Exceptions.ElementDoesNotExistException;
import org.example.Exceptions.IncorrecteCapacityException;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private static List<Event> events = new ArrayList<>();
    private static List<Place> places = new ArrayList<>();
    private static List<Client> clients = new ArrayList<>();


    public static List<Event> getEvents() {
        return events;
    }

    public static List<Place> getPlaces() {
        return places;
    }

    public static List<Client> getClients() {
        return clients;
    }

    public static Event getOneEvent(int eventId) throws ElementDoesNotExistException {
        return getElement(events, eventId);
    }

    public static Client getOneClient(int clientId) throws ElementDoesNotExistException {
        return getElement(clients, clientId);
    }

    public static Place getOnePlace(int placeId) throws ElementDoesNotExistException {
        return getElement(places, placeId);
    }

    public static List<Event> addEvent(Event event) {
        events.add(event);
        return events;
    }

    public static List<Event> removeEvent(int eventId) throws ElementDoesNotExistException {
        Event foundEvent = getElement(events,eventId);
        events.remove(foundEvent);
        return events;
    }

    public static List<Place> addPlace(Place place) {
        if (place.getCapacity() <= 0){
            throw new IncorrecteCapacityException();
        }
        places.add(place);
        return places;
    }

    public static List<Place> removePlace(int placeId) throws ElementDoesNotExistException {
        Place foundPlace = getElement(places, placeId);
        places.remove(foundPlace);
        return places;
    }

    public static List<Client> addClient(Client cLient) {
        clients.add(cLient);
        return clients;
    }

    public static List<Client> removeClient(int clientId) throws ElementDoesNotExistException {
        Client foundClient = getElement(clients, clientId);
        clients.remove(foundClient);
        return clients;
    }


    private static <T extends Element> T getElement(List<T> elements, int id) throws ElementDoesNotExistException {
        Element foundElement = elements.stream().filter(element -> element.getId() == id)
                .findFirst().orElse(null);
        if (foundElement == null) {
            throw new ElementDoesNotExistException();
        }
        return (T) foundElement;
    }
}

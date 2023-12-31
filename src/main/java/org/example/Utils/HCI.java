package org.example.Utils;

import org.example.Entity.Customer;
import org.example.Entity.Event;
import org.example.Entity.EventLocation;
import org.example.Exception.CustomFormatException;
import org.example.Services.TickettingService;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class HCI {
    private Scanner scanner = new Scanner(System.in);
    private TickettingService tickettingService = new TickettingService();

    // l'objet event passer à buyTickets DOIT contenir un eventLocation

    private int scanInt() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entrez un nombre");
            return -1;
        } finally {
            scanner.nextLine();
        }
    }

    private double scanDouble() {
        try {
            return scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("Entrez un nombre");
            return -1;
        } finally {
            scanner.nextLine();
        }
    }

    public void mainMenu() {
        int choice = -1;

        System.out.println("Bonjour. Que voulez-vous faire ?");

        while (choice == -1) {
            System.out.println(
                    "1 - Ajouter/modifier/supprimer client\n" +
                            "2 - Ajouter/modifier/supprimer lieu\n" +
                            "3 - Ajouter/modifier/supprimer événement\n" +
                            "4 - Acheter des places\n" +
                            "5 - Rendre des places\n" +
                            "6 - Afficher tous les événéments encore disponbibles\n" +
                            "7 - Afficher vos billets achetés\n" +
                            "0 - Quitter");

            choice = scanInt();

            switch (choice) {
                case 1 -> {
                    customerMenu();
                    mainMenu();
                }
                case 2 -> {
                    eventLocationMenu();
                    mainMenu();
                }
                case 3 -> {
                    eventMenu();
                    mainMenu();
                }
                case 4 -> {
                    buyTickets();
                    mainMenu();
                }
                case 5 -> {
                    cancelTickets();
                    mainMenu();
                }
                case 6 -> {
                    showAvailableEvents();
                    mainMenu();
                }
                case 7 -> {
                    showBoughtTickets();
                    mainMenu();
                }
                case 0 -> stopApp();
            }
        }
    }

    private int choseEntity(String entity) {
        int choice = -1;

        while (choice != 0 && choice != 1 && choice != 2 && choice != 3) {
            System.out.printf(
                    "Voulez-vous :\n" +
                            "1 - Ajouter un %s\n" +
                            "2 - Modifier un %s\n" +
                            "3 - Supprimer un %s\n" +
                            "0 - Retourner au menu principal", entity, entity, entity);
            choice = scanInt();
        }

        return choice;
    }

    private void customerMenu() {
        switch (choseEntity("client")) {
            case 1 -> createCustomer();
            case 2 -> editCustomer();
            case 3 -> deleteCustomer();
        }
    }

    private void createCustomer() {
        String firstname;
        String lastname;
        String email;
        Customer customer = null;

        System.out.println("Entrez votre prénom");
        firstname = scanner.nextLine();
        System.out.println("Entrez votre nom");
        lastname = scanner.nextLine();

        while (customer == null) {
            System.out.println("Entrez votre adresse e-mail");
            email = scanner.nextLine();

            try {
                customer = tickettingService.createCustomer(firstname, lastname, email);
            } catch (CustomFormatException e) {
                System.out.println("Votre adresse n'est pas au bon format");
            }
        }
    }

    private void editCustomer() {
        int id;
        String firstname;
        String lastname;
        String email;
        Customer customer = null;
        boolean success = false;

        System.out.println("Entrez votre id");

        id = scanInt();
        customer = tickettingService.getCustomer(id);

        if (customer != null) {

            System.out.printf("Votre prénom enregistré est %s\n" +
                    "Entrez une valeur pour le modifier. N'entrez rien pour le laisser tel quel\n", customer.getFirstname());
            firstname = scanner.nextLine();
            if (firstname.isEmpty()) {
                firstname = customer.getFirstname();
            }

            System.out.printf("Votre nom enregistré est %s\n" +
                    "Entrez une valeur pour le modifier. N'entrez rien pour le laisser tel quel\n", customer.getLastname());
            lastname = scanner.nextLine();
            if (lastname.isEmpty()) {
                lastname = customer.getLastname();
            }

            System.out.printf("Votre adresse mail enregistrée est %s\n" +
                    "Entrez une valeur pour le modifier. N'entrez rien pour le laisser tel quel\n", customer.getEmail());
            email = scanner.nextLine();
            if (email.isEmpty()) {
                email = customer.getEmail();
            }

            try {
                success = tickettingService.editCustomer(id, firstname, lastname, email);
            } catch (CustomFormatException e) {
                System.out.println(e);
            }

            if (success) {
                System.out.println("Client corectement modifié");
            } else {
                System.out.println("Client non modifié");
            }

        } else {
            System.out.println("Cet id semble ne correspondre à aucun client");
        }


    }

    private void deleteCustomer() {
        int id;
        boolean success;

        System.out.println("Entrez votre id (appuyez sur 0 pour annuler)");
        id = scanInt();

        if (id != 0) {
            success = tickettingService.deleteCustomer(id);

            if (success) {
                System.out.println("Compte client supprimé");
            } else {
                System.out.println("Compte non-supprimé. Votre id doit être erronée");
            }
        }
    }

    private void eventLocationMenu() {
        switch (choseEntity("lieu")) {
            case 1 -> createEventLocation();
            case 2 -> editEventLocation();
            case 3 -> deleteEventLocation();
        }
    }

    private void createEventLocation() {
        String name;
        String address;
        int capacity = 0;
        EventLocation eventLocation = null;

        while (eventLocation == null) {
            System.out.println("Entrez le nom du lieu");
            name = scanner.nextLine();
            System.out.println("Entrez l'addresse du lieu");
            address = scanner.nextLine();

            while (capacity <= 0) {
                System.out.println("Entrez la capacité d'accueil du lieu");
                capacity = scanInt();
            }

            try {
                eventLocation = tickettingService.createEventLocation(name, address, capacity);
            } catch (CustomFormatException e) {
                System.out.println(e);
            }
        }

    }

    private void editEventLocation() {
        int id;
        String name;
        String address;
        int capacity = -1;
        EventLocation eventLocation = null;
        boolean success = false;

        System.out.println("Entrez votre id");

        id = scanInt();
        eventLocation = tickettingService.getEventLocation(id);

        if (eventLocation != null) {

            System.out.printf("Le nom enregistré est %s\n" +
                    "Entrez une valeur pour le modifier. N'entrez rien pour le laisser tel quel\n", eventLocation.getName());
            name = scanner.nextLine();
            if (name.isEmpty()) {
                name = eventLocation.getName();
            }

            System.out.printf("L'adresse enregistrée est %s\n" +
                    "Entrez une valeur pour le modifier. N'entrez rien pour le laisser tel quel\n", eventLocation.getAddress());
            address = scanner.nextLine();
            if (address.isEmpty()) {
                address = eventLocation.getAddress();
            }

            System.out.printf("La capacité d'accueil enregistrée est %s\n" +
                    "Entrez une valeur pour le modifier. Entrez 0 pour le laisser tel quel\n", eventLocation.getCapacity());
            while (capacity == -1) {
                capacity = scanInt();
            }
            if (capacity == 0) {
                capacity = eventLocation.getCapacity();
            }

            try {
                success = tickettingService.editEventLocation(id, name, address, capacity);
            } catch (CustomFormatException e) {
                System.out.println(e);
            }


            if (success) {
                System.out.println("Lieu correctement modifié");
            } else {
                System.out.println("Le lieu n'a pas pu être modifié");
            }

        } else {
            System.out.println("Cet id semble ne correspondre à aucun lieu");
        }
    }

    private void deleteEventLocation() {
        int id = 0;
        boolean success;

        while (id == 0) {
            System.out.println("Entrez votre id");
            id = scanInt();
        }

        success = tickettingService.deleteEventLocation(id);

        if (success) {
            System.out.println("Lieu supprimé");
        } else {
            System.out.println("Lieu non-supprimé. L'id est peut-être incorrectee");
        }
    }

    private void eventMenu() {
        switch (choseEntity("événement")) {
            case 1 -> createEvent();
            case 2 -> editEvent();
            case 3 -> deleteEvent();
        }
    }

    private void createEvent() {
        String name;
        String stringDate;
        boolean isDateCorrect = false;
        LocalDateTime formattedDateTime = null;
        int eventLocationId = -1;
        List<EventLocation> eventLocations;
        double price = -1D;
        Event event = null;

        while (event == null) {
            System.out.println("Entrez le nom de l'événement");
            name = scanner.nextLine();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            while (!isDateCorrect) {
                System.out.println("Entrez la date et l'heure au format 2023-25-12: 13:45:00 pour le 25/12/2023 à 13h45");
                stringDate = scanner.nextLine();
                try {
                    formattedDateTime = LocalDateTime.parse(stringDate, formatter);
                    isDateCorrect = true;
                } catch (DateTimeParseException e) {
                    System.out.println("Format invalide");
                }
            }

            while (price <= 0D) {
                System.out.println("Entrez le prix (mettez une virgule et pas un point si vous voulez indiquer les centimes)");
                price = scanDouble();
            }

            eventLocations = tickettingService.getEventLocations();
            System.out.println("Indiquez le numéro de l'endroit où l'événement aura lieu");
            for (EventLocation tempEventLocation : eventLocations) {
                System.out.printf("%d - %s\n", tempEventLocation.getId(), tempEventLocation.getName());
            }
            while (eventLocationId == -1) {
                eventLocationId = scanInt();
            }

            try {
                event = tickettingService.createEvent(name, formattedDateTime, eventLocationId, price);
            } catch (CustomFormatException e) {
                System.out.println(e);
            }
        }
    }

    private void editEvent() {
        int id;
        String name;
        String stringDate;
        boolean isDateCorrect = false;
        LocalDateTime formattedDateTime = null;
        double price = -1D;
        int changeEventLocationid = -1;
        int eventLocationId = -1;
        List<EventLocation> eventLocations;
        Event event = null;
        boolean success = false;

        System.out.println("Entrez l'id de l'événement à modifier");
        id = scanInt();
        event = tickettingService.getEvent(id);

        if (event != null){

            event.setEventLocation(tickettingService.getEventLocation(event.getEventLocationId()));

            System.out.printf("Le nom de l'événement enregistré est %s.\n" +
                    "Entrez une valeur pour le modifier. N'entrez rien pour le laisser tel quel\n", event.getName());
            name = scanner.nextLine();
            if (name.isEmpty()) {
                name = event.getName();
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            while (!isDateCorrect) {
                System.out.printf("La date et l'heure enregistrés sont %s.\n" +
                        "Entrez une valeur pour le modifier. N'entrez rien pour le laisser tel quel\n" +
                        "(entrez la date et l'heure au format 2023-25-12:13:45 pour le 25/12/2023 à 13h45)\n", event.getDateTime());
                stringDate = scanner.nextLine();
                if (stringDate.isEmpty()) {
                    formattedDateTime = event.getDateTime();
                    isDateCorrect = true;
                } else {
                    try {
                        formattedDateTime = LocalDateTime.parse(stringDate, formatter);
                        isDateCorrect = true;
                    } catch (DateTimeParseException e) {
                        System.out.println("Format invalide");
                    }
                }
            }

            System.out.printf("Le prix enregistré est %.2f€\n" +
                    "Entrez une valeur pour le modifier. Entrez une valeur négative ou nulle pour le laiser tel quel\n", event.getPrice());
            price = scanDouble();
            if (price >= 0) {
                price = event.getPrice();
            }

            eventLocations = tickettingService.getEventLocations();
            while (changeEventLocationid != 0 && changeEventLocationid != 1) {
                System.out.printf("Le lieu actuel est %s\n" +
                        "Entrez 1 pour le modifier, 0 pour laisser tel quel\n", event.getEventLocation().getName());
                changeEventLocationid = scanInt();
            }
            if (changeEventLocationid == 0) {
                eventLocationId = event.getEventLocation().getId();
            } else {
                for (EventLocation tempEventLocation : eventLocations) {
                    System.out.printf("%d - %s\n", tempEventLocation.getId(), tempEventLocation.getName());
                }
                while (eventLocationId == -1) {
                    eventLocationId = scanInt();
                }
            }

            try {
                success = tickettingService.editEvent(id, name, formattedDateTime, eventLocationId, price, event.getTicketsSoldNumber());
            } catch (CustomFormatException e) {
                System.out.println(e);
            }

            if (success) {
                System.out.println("Evénement correction modifié");
            } else {
                System.out.println("Evenement non-modifié");
            }
        } else {
            System.out.println("Aucun événément trouvé à cet id");
        }
    }

    private void deleteEvent() {
        int id = 0;
        boolean success;

        while (id == 0) {
            System.out.println("Entrez votre id");
            id = scanInt();
        }

        success = tickettingService.deleteEvent(id);

        if (success) {
            System.out.println("Evenement supprimé");
        } else {
            System.out.println("Evenement non-supprimé. L'id est peut-être incorrectee");
        }
    }

    private void buyTickets() {
        List<Event> events = tickettingService.getEvents();
        Customer customer = null;
        Event event = null;
        boolean success;

        for (Event tempEvent : events){
            System.out.println(tempEvent);
        }
        while (event == null){
            System.out.println("Entre l'id de l'événement pour lequel vous voulez acheter une place");
            int id = scanInt();
            event = tickettingService.getEvent(id);
            if (event == null){
                System.out.println("Mauvais id");
            } else {
                event.setEventLocation(tickettingService.getEventLocation(event.getEventLocationId()));
            }
        }

        while (customer == null){
            System.out.println("Entrez l'id du client");
            int id = scanInt();
            customer = tickettingService.getCustomer(id);
            if (customer == null){
                System.out.println("Mauvais id");
            }
        }


        success = tickettingService.buyTickets(customer,event);
        System.out.println(success ? "Billet bien réservé" : "Billet non réservé");
    }

    private void cancelTickets() {
        List<Event> events = tickettingService.getEvents();
        Customer customer = null;
        Event event = null;
        boolean success;

        for (Event tempEvent : events){
            System.out.println(tempEvent);
        }
        while (event == null){
            System.out.println("Entre l'id de l'événement pour lequel vous voulez acheter une place");
            int id = scanInt();
            event = tickettingService.getEvent(id);
            if (event == null){
                System.out.println("Mauvais id");
            }
        }

        while (customer == null){
            System.out.println("Entrez l'id du client");
            int id = scanInt();
            customer = tickettingService.getCustomer(id);
            if (customer == null){
                System.out.println("Mauvais id");
            }
        }

        success = tickettingService.cancelTickets(customer, event);
        System.out.println(success ? "Billet bien annulé" : "Billet non annulé");
    }

    private void showAvailableEvents() {
        System.out.println("Voici les événéments dont il reste des places achetables : ");
        for (Event event : tickettingService.getAvailableEvents()){
            System.out.println(event);
        }
    }

    private void showBoughtTickets() {
        int id = -1;
        Customer customer;

            while (id == -1){
                System.out.println("Entrez votre id de client");
                id = scanInt();
            }

            customer = tickettingService.getCustomer(id);
            if (customer != null){
                System.out.println("Voici les billets que vous avez réservés");
                for (Event event : tickettingService.getTicketsByClient(customer.getId())){
                    System.out.println(event);
                }
            } else {
                System.out.println("Cet id semble ne correspondre à aucun client");
            }
    }

    private void stopApp() {
        System.out.println("Merci au revoir !");
    }


}

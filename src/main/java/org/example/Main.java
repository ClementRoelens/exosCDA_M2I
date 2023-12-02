package org.example;

import org.example.Exceptions.ElementDoesNotExistException;
import org.example.Exceptions.IncorrectPriceException;
import org.example.Exceptions.IncorrecteCapacityException;
import org.example.classes.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static void displaySecondMenu(String element) {
        System.out.println("Voulez-vous : "
                        + "\n1 - Afficher la liste"
                        + "\n2 - Ajouter un " + element
//                + "\n3 - Supprimer un " + element
//                + "\n4 - Modifier un " + element
                        + "\n0 - Retourner au menu principal"
        );
    }

    public static void main(String[] args) {

        String choice;
        String secondChoice;
        Scanner petScan = new Scanner(System.in);


        do {
            System.out.println("Bonjour cher utilisateur. Avec quel type de données veux-tu interagir ?"
                    + "\n1 - Afficher/ajouter/modifier/supprimer un lieu"
                    + "\n2 - Afficher/ajouter/modifier/supprimer un événement"
                    + "\n3 - Afficher/ajouter/modifier/supprimer un client"
                    + "\n4 - Acheter/annuler des billet"
                    + "\n0 - Quitter le programme"
            );
            choice = petScan.nextLine();
            switch (choice) {
                case "1" -> {
                    do {
                        displaySecondMenu("lieu");
                        secondChoice = petScan.nextLine();
                        switch (secondChoice) {
                            case "1" -> {
                                for (Place place : Service.getPlaces()){
                                    System.out.println(place);
                                }
                            }
                            case "2" -> {
                                System.out.println("Nom de l'endroit :");
                                String name = petScan.nextLine();
                                System.out.println("Adresse de l'endroit : ");
                                String adress = petScan.nextLine();
                                int capacity = 0;

                                boolean incorrectInput;
                                do {
                                    System.out.println("Nombre de sièges :");
                                    try {
                                        capacity = petScan.nextInt();
                                        incorrectInput = false;
                                    } catch (InputMismatchException e) {
                                        System.out.println("Un nombre de siège est forcément un nombre entier");
                                        incorrectInput = true;
                                    }
                                } while (incorrectInput);

                                try {
                                    Service.addPlace(new Place(name, adress, capacity));
                                } catch (IncorrecteCapacityException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        }
                    } while (!secondChoice.equals("0"));
                }
                case "2" -> {
                    do {
                        displaySecondMenu("événement");
                        secondChoice = petScan.nextLine();
                        switch (secondChoice) {
                            case "1" -> {
                                for (Event event : Service.getEvents()){
                                    System.out.println(event);
                                }
                            }
                            case "2" -> {
                                System.out.println("Nom de l'événement :");
                                String name = petScan.nextLine();
                                System.out.println("Date de l'événement (au format \"2023-12-02\"): ");
                                LocalDate date = LocalDate.parse(petScan.nextLine());
                                System.out.println("Heure de l'événément (au format \"15:30:00\") : ");
                                LocalTime time = LocalTime.parse(petScan.nextLine());
                                Place place = null;

                                boolean incorrectInput;
                                do {
                                    System.out.println("Id du lieu accueillant l'événement");
                                    try {
                                        int id = petScan.nextInt();
                                        incorrectInput = false;
                                        place = Service.getOnePlace(id);
                                    } catch (InputMismatchException e) {
                                        System.out.println("Un id est forcément un nombre entier");
                                        incorrectInput = true;
                                    } catch (ElementDoesNotExistException e) {
                                        System.out.println(e.getMessage());
                                        incorrectInput = true;
                                    }
                                } while (incorrectInput);

                                float price = 0f;
                                do {
                                    System.out.println("Prix du billet");
                                    try {
                                        price = petScan.nextFloat();
                                        incorrectInput = false;
                                    } catch (InputMismatchException e) {
                                        System.out.println("Entrez un nombre (sans la devise)");
                                        incorrectInput = true;
                                    }
                                } while (incorrectInput);

                                try {
                                    Service.addEvent(new Event(name, date, time, place, price));
                                } catch (IncorrectPriceException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        }
                    } while (!secondChoice.equals("0"));
                }
                case "3" -> {
                    do {
                        displaySecondMenu("client");
                        secondChoice = petScan.nextLine();
                        switch (secondChoice) {
                            case "1" -> {
                                for (Client client : Service.getClients()){
                                    System.out.println(client);
                                }
                            }
                            case "2" -> {
                                System.out.println("Prénom :");
                                String firstname = petScan.nextLine();
                                System.out.println("Nom :");
                                String lastname = petScan.nextLine();
                                System.out.println("Adresse e-mail : ");
                                String email = petScan.nextLine();

                                Service.addClient(new Client(firstname, lastname, email));
                            }
                        }
                    } while (!secondChoice.equals("0"));
                }
                case "4" -> {
                    do {
                        System.out.println("1 - Acheter\n0 - Annuler");
                        secondChoice = petScan.nextLine();
                    } while (!secondChoice.equals("0") && !secondChoice.equals("1"));

                    boolean incorrectInput;
                    do {
                        try {
                            System.out.println("Id de l'événement");
                            int eventId = petScan.nextInt();
                            incorrectInput = false;
                            do {
                                System.out.println("Id du client");
                                try {
                                    int clientId = petScan.nextInt();
                                    Client client = Service.getOneClient(clientId);
                                    if (secondChoice.equals("1")){
                                        if (client.buyTicket(eventId)){
                                            System.out.println("Félicitations, place réservée");
                                        } else {
                                            System.out.println("Il semble que cet id ne corresponde à aucun événement");
                                            incorrectInput = true;
                                        }
                                    } else {
                                        if (client.returnTicket(eventId)){
                                            System.out.println("Félicitations, réservaiton annulée");
                                        } else {
                                            System.out.println("Il semble que cet id ne corresponde à aucun événement");
                                            incorrectInput = true;
                                        }
                                    }
                                } catch (InputMismatchException e){
                                    System.out.println("Un id est forcément un nombre entier");
                                    incorrectInput = true;
                                } catch (ElementDoesNotExistException e){
                                    System.out.println("Il n'y a aucun client de cet id");
                                    incorrectInput = true;
                                }
                            } while (incorrectInput);
                        } catch (InputMismatchException e){
                            System.out.println("Un id est forcément un nombre entier");
                            incorrectInput = true;
                        }
                    } while (incorrectInput);
                }

            }
        } while (!choice.equals("0"));
    }
}
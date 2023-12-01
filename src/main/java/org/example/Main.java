package org.example;

import org.example.Exceptions.ElementDoesNotExistException;
import org.example.Exceptions.IncorrectPriceException;
import org.example.Exceptions.IncorrecteCapacityException;
import org.example.classes.Client;
import org.example.classes.Event;
import org.example.classes.Place;
import org.example.classes.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.List;
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
                            case "1" -> System.out.println(Service.getPlaces());
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
                    } while (secondChoice != "0");
                }
                case "2" -> {
                    do {
                        displaySecondMenu("événement");
                        secondChoice = petScan.nextLine();
                        switch (secondChoice) {
                            case "1" -> System.out.println(Service.getEvents());
                            case "2" -> {
                                System.out.println("Nom de l'événement :");
                                String name = petScan.nextLine();
                                System.out.println("Date de l'événement : ");
                                LocalDate date = LocalDate.parse(petScan.nextLine());
                                System.out.println("Heure de l'événément");
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
                    } while (secondChoice != "0");
                }
                case "3" -> {
                    do {
                        displaySecondMenu("client");
                        secondChoice = petScan.nextLine();
                        switch (secondChoice) {
                            case "1" -> System.out.println(Service.getClients());
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
                    } while (secondChoice != "0");
                }
                case "4" -> {
                    do {
                        System.out.println("1 - Acheter\n0 - Annuler");
                        secondChoice = petScan.nextLine();
                    } while (secondChoice != "0" && secondChoice != "1");

                    boolean incorrectInput;
                    do {
                        try {
                            System.out.println("Id de l'événement : ");
                            int eventId = petScan.nextInt();
                            Event event = Service.getOneEvent(eventId);
                            do {
                                try {
                                    System.out.println("Id du client");
                                    int clientId = petScan.nextInt();
                                    Client client = Service.getOneClient(clientId);
                                    incorrectInput = false;

                                    if (secondChoice == "0") {
                                        client.returnTicket(eventId);
                                    } else {
                                        client.buyTicket(eventId);
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Un id est forcément un nombre entier");
                                    incorrectInput = true;
                                } catch (ElementDoesNotExistException e) {
                                    System.out.println(e.getMessage());
                                    incorrectInput = true;
                                }
                            } while (incorrectInput);
                        } catch (InputMismatchException e) {
                            System.out.println("Un id est forcément un nombre entier");
                            incorrectInput = true;
                        } catch (ElementDoesNotExistException e) {
                            System.out.println(e.getMessage());
                            incorrectInput = true;
                        }
                    } while (incorrectInput);
                }

            }
        } while (choice != "0");
    }
}
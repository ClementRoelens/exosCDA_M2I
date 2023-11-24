package org.example;

import org.example.TP_1.*;
import org.example.TP_2.Client;
import org.example.TP_2.HCI;
import org.example.TP_2.Hostel;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scintigraphy = new Scanner(System.in);

//        // Exo 1
//
//        Author clementRoelens = new Author("Clément", "Roelens");
//        Author nassimSakhri = new Author("Nassim", "Sakhri");
//        Author oliviaPigani = new Author("Olivia","Pigani");
//
//        Publisher petitEditions = new Publisher("PetitEditions");
//        Publisher jospinBooks = new Publisher("JospinBooks");
//        Publisher violentPublisher = new Publisher("ViolencePublishing");
//
//        Book bookHair = new Book("Comment entretenir ses cheveux", new Author[]{clementRoelens, nassimSakhri},petitEditions,2005,20,19.99D, CoverType.SOFT);
//        Book bookSpeedrun = new Book("Les meilleures techniques de Speedrun pour Twitter", new Author[]{nassimSakhri},petitEditions,2023,5,5D,CoverType.SOFT);
//        Book bookHipster = new Book("Les hipster : ces nouveaux envahisseurs", new Author[]{oliviaPigani},violentPublisher,2017,354,24.45D,CoverType.BOUND);
//        Book bookSubway = new Book("Les meilleurs Subway de la métropole lilloise", new Author[]{clementRoelens},jospinBooks,2012,25,10,CoverType.BOUND);
//        Book bookKFC = new Book("Les meilleurs KFC du coin", new Author[]{oliviaPigani, nassimSakhri}, jospinBooks, 2020,10,20,CoverType.BOUND);
//        Book bookAssassin = new Book("Assassiner discrètement", new Author[]{nassimSakhri},violentPublisher,2018,666,66.6D,CoverType.BOUND);
//        Book bookSardou = new Book("Les meilleures chansons de Michel Sardou", new Author[]{clementRoelens, nassimSakhri, oliviaPigani},jospinBooks,2022,2,0.99D,CoverType.SOFT);
//
//        System.out.println("On a créé plusieurs livres : ");
//        System.out.println(bookHair);
//        System.out.println(bookSpeedrun);
//        System.out.println(bookHipster);
//        System.out.println(bookSubway);
//        System.out.println(bookKFC);
//        System.out.println(bookAssassin);
//        System.out.println(bookSardou);
//        System.out.println("Et maintenant, on va filtrer");
//
//        Book[] allBooks = {bookAssassin,bookHipster,bookHair,bookKFC,bookSardou,bookSubway,bookSpeedrun};
//        Book[] booksFrom2020 = BookService.filterBooksAfterSpecifiesyear(2020,allBooks);
//        Book[] booksFromNassim = BookService.filterBooksByAuthor(nassimSakhri,allBooks);
//        Book[] booksFromClement = BookService.filterBooksByAuthor(clementRoelens,allBooks);
//        Book[] booksFromOlivia = BookService.filterBooksByAuthor(oliviaPigani, allBooks);
//        Book[] booksFromPetit = BookService.filterBooksByPublisher(petitEditions,allBooks);
//        Book[] booksFromJospin = BookService.filterBooksByPublisher(jospinBooks, allBooks);
//        Book[] booksFromPauline = BookService.filterBooksByPublisher(violentPublisher, allBooks);
//
//        System.out.println("Voici les livres sortis depuis 2020 : ");
//        for (Book book : booksFrom2020){
//            System.out.println(book.getName());
//        }System.out.println("Voici les livres de Nassim : ");
//        for (Book book : booksFromNassim){
//            System.out.println(book.getName());
//        }System.out.println("Voici les livres de Clément : ");
//        for (Book book : booksFromClement){
//            System.out.println(book.getName());
//        }System.out.println("Voici les livres publiés par PetitEditions : ");
//        for (Book book : booksFromPetit) {
//            System.out.println(book.getName());
//        }

        // Exo 2

        String input;
        Hostel hostel = new Hostel();
        HCI hci = new HCI(hostel);

        do {
            System.out.println("\n1 - Ajouter un client\n" +
                    "2 - Afficher la liste des clients\n" +
                    "3 - Afficher les réservations d'un client\n" +
                    "4 - Ajouter une réservation\n" +
                    "5 - Annuler une réservation\n" +
                    "6 - Afficher la liste des réservations\n" +
                    "0 - Quitter\n"
            );
            input = scintigraphy.nextLine();
            switch (input) {
                case "1" ->  {
                    String firstName;
                    String lastName;
                    String phoneNumber;

                    System.out.print("Nom de famille : ");
                    firstName = scintigraphy.nextLine();
                    System.out.print("Prénom : ");
                    lastName = scintigraphy.nextLine();
                    System.out.print("Numéro de téléphone : ");
                    phoneNumber = scintigraphy.nextLine();

                    hci.addClient(firstName,lastName,phoneNumber);
                }
                case "2" -> hci.printClients();
                case "3" -> {
                    String phoneNumber;

                    System.out.println("Quel est le numéro de téléphone de ce client ?");
                    phoneNumber = scintigraphy.nextLine();

                    hci.printReservationsFromOneClient(phoneNumber);
                }
                case "4" -> {
                    int clientId;
                    int occupantsNumber;

                    System.out.println("Quel est votre numéro de client ?");
                    clientId = scintigraphy.nextInt();
                    System.out.println("Pour combien de personnes voulez-vous réserver ?");
                    occupantsNumber = scintigraphy.nextInt();
                    scintigraphy.nextLine();

                    hci.addReservation(clientId,occupantsNumber);
                }
                case "5" -> {
                    int reservartionId;

                    System.out.println("Quel est le numéro de la réservation à annuler ?");
                    reservartionId = scintigraphy.nextInt();
                    scintigraphy.nextLine();

                    hci.cancelReservation(reservartionId);
                }
                case "6" -> hci.printAllReservations();
            }
        } while (input != "0");
    }
}
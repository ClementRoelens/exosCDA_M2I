package org.example.Utils;

import org.example.Services.TickettingService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HCI {
    private Scanner scanner = new Scanner(System.in);
    private TickettingService tickettingService = new TickettingService();

    // l'objet event passer à buyTickets DOIT contenir un eventLocation

    private int scanInt(){
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entrez un nombre");
            return -1;
        }
        finally {
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
                            "7 - Afficher vos billets achetés" +
                            "0 - Quitter");

            choice = scanInt();

            switch (choice) {
                case 1 -> customerMenu();
                case 2 -> eventLocationMenu();
                case 3 -> eventMenu();
                case 4 -> buyTickets();
                case 5 -> cancelTickets();
                case 6 -> showAvailableEvents();
                case 7 -> showBoughtTickets();
                case 0 -> stopApp();
            }
        }
    }

    private int choseEntity(String entity) {
        int choice = -1;

        while (choice == -1) {
            System.out.printf(
                            "Voulez-vous :\n" +
                            "1 - Ajouter un %s\n" +
                            "2 - Modifier un %s\n" +
                            "3 - Supprimer un %s\n" +
                            "0 - Retourner au menu principal", entity, entity, entity);
         choice = scanInt();

            return choice;
        }

    }

    private void customerMenu() {
        switch (choseEntity("client")){
            case 1 -> tickettingService.
        }
    }

    private String[] getCustomerInputs(){
        String[] inputs = new String[3];

        System.out.println("Entrez votre prénom");
        inputs[0] = scanner.nextLine();
    }

    private void eventLocationMenu() {
        switch (choseEntity("lieu")){

        }
    }

    private void eventMenu() {
        switch (choseEntity("événement")){

        }
    }

    private void buyTickets() {

    }

    private void cancelTickets() {

    }

    private void showAvailableEvents() {

    }

    private void showBoughtTickets() {

    }

    private void stopApp() {

    }


}

package org.example;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        // Exo 2.2

        Scanner scanner = new Scanner(System.in);

//        System.out.println("Entrez un nombre (avec une virgule et pas un point si vous voulez mettre des décimales...)");
//        float n1 = scanner.nextFloat();
//        scanner.nextLine();
//        float nSquare = n1 * n1;
//        System.out.println("Voici son carré : " + nSquare);
//
//        // Exo 2.3
//
//        System.out.println("\nQuel est votre nom chère personne ?");
//        String name = scanner.nextLine();
//        System.out.println("Bonjour, " + name + " !");
//
//        // Exo 2.4
//
//        System.out.println("\nDonnez-moi le prix HT de l'article que vous voulez");
//        float tfPrice = scanner.nextFloat();
//        System.out.println("Donnez-moi la quantité que vous voulez");
//        int nb = scanner.nextInt();
//        System.out.println("Donnez-moi le taux de TVA (en nombre entier, pas en pourcentage hein)");
//        float taxRate = scanner.nextFloat();
//        System.out.println("Très bien monsieur/madame, vous me devez " + nb * tfPrice * (1 + (taxRate / 100)) + "€");
//
//        // Exo 3.1
//
//        System.out.println("\nDonnez-moi le nombre que vous voulez analyser");
//        float analizedNb = scanner.nextFloat();
//        String message = (analizedNb < 0) ? "Votre nombre est négatif" : "Votre nombre est positif";
//        System.out.println(message + "\n(quand même c'est un peu la honte d'avoir besoin d'un programme pour savoir ça...)");
//
//        // Exo 3.2
//
//        System.out.println("\nDonne-moi un nombre");
//        float nb1 = scanner.nextFloat();
//        System.out.println("Donne-m'en un autre");
//        float nb2 = scanner.nextFloat();
//        scanner.nextLine();
//        message = ((nb1 < 0) ^ (nb2 < 0)) ? "Le produit des deux nombres est négatif" : "Le produit des deux nombres est positif";
//        System.out.println(message + "\n(encore une fois...¯\\_(ツ)_/¯)");
//
//        // Exo 3.3
//
//        System.out.println("\nDonne-moi un nom");
//        String nameOne = scanner.nextLine();
//        System.out.println("Un autre !");
//        String nameTwo = scanner.nextLine();
//        System.out.println("Un autre (promis c'est la dernière fois)");
//        String nameThree = scanner.nextLine();
//
//        if (((nameOne.compareTo(nameTwo) > 0) && (nameTwo.compareTo(nameThree)) > 0)) {
//            System.out.println("Ces trois noms sont dans l'ordre alphabétique");
//        } else {
//            System.out.println("Ces trois noms ne sont pas dans l'ordre alphabétique");
//        }

        // Exo 3.4

        System.out.println("\nDonnez-moi le nombre que vous voulez analyser");
        float analizedNb;
        String message;

        analizedNb = scanner.nextFloat();

        if (analizedNb < 0){
            message = "Votre nombre est négatif";
        } else if (analizedNb == 0){
            message = "Votre nombre est nul";
        } else {
            message = "Votre nombre est positif";
        }

        System.out.println(message + "\n(quand même c'est un peu la honte d'avoir besoin d'un programme pour savoir ça...)");

        // Exo 3.5

        int age;
        int poussinBreakpoint = 6;
        int pupilleBreakpoint = 8;
        int minimeBreakpoint = 10;
        int cadetBreakpoint = 12;
        int adulteBreakpoint = 18;

        System.out.println("\nQuel est l'âge de votre gamin ?");
        age = scanner.nextInt();
        scanner.nextLine();

        if (age < poussinBreakpoint){
            message = "Votre gamin est trop jeune pour être admissible";
        } else if (age < pupilleBreakpoint){
            message = "Votre gamin est un poussin";
        }else if (age < minimeBreakpoint){
            message = "Votre gamin est une pupille";
        }else if (age < cadetBreakpoint){
            message = "Votre gamin est un minime";
        }else if (age < adulteBreakpoint){
            message = "Votre gamin est un cadet";
        } else {
            message = "Votre gamin n'est pas un gamin";
        }

        System.out.println(message);

        // Exo 3.6

        int intNb;

        System.out.println("\nEntrez un nombre entier");
        intNb = scanner.nextInt();

        if (intNb%3 == 0){
            System.out.println(intNb + " est divisible par 3");
        } else {
            System.out.println(intNb + " n'est pas divisible par 3");
        }

        // Exo 3.7

        int firstBreakpoint = 10;
        int secondBreakpoint = 20;
        int wantedCopies;
        float copyUnitPrice = 0.15f;
        float totalPrice;

        System.out.println("\nCombien de photocopies voulez-vous ?");
        wantedCopies = scanner.nextInt();

        if (wantedCopies > secondBreakpoint){
            copyUnitPrice = 0.05f;
        } else if (wantedCopies > firstBreakpoint){
            copyUnitPrice = 0.10f;
        }
        totalPrice = copyUnitPrice*wantedCopies;

        System.out.println("Vous devez " + copyUnitPrice + "€ pour chacune des " + wantedCopies +" photocopies.\nSoit un total de " + totalPrice+"€");
    }
}
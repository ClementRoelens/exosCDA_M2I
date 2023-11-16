package org.example;

import java.util.Scanner;

public class Main {



    public static void main(String[] args) {

        // Exo 2.2

        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrez un nombre (avec une virgule et pas un point si vous voulez mettre des décimales...)");
        float n1 = scanner.nextFloat();
        scanner.nextLine();
        float nSquare = n1*n1;
        System.out.println("Voici son carré : " + nSquare);

        // Exo 2.3

        System.out.println("\nQuel est votre nom chère personne ?");
        String name = scanner.nextLine();
        System.out.println("Bonjour, " + name +" !");

        // Exo 2.4

        System.out.println("\nDonnez-moi le prix HT de l'article que vous voulez");
        float tfPrice = scanner.nextFloat();
        System.out.println("Donnez-moi la quantité que vous voulez");
        int nb = scanner.nextInt();
        System.out.println("Donnez-moi le taux de TVA (en nombre entier, pas en pourcentage hein)");
        float taxRate = scanner.nextFloat();
        System.out.println("Très bien monsieur/madame, vous me devez " + nb*tfPrice*(1+(taxRate/100)) + "€");

        // Exo 3.1

        System.out.println("\nDonnez-moi le nombre que vous voulez analyser");
        float analizedNb = scanner.nextFloat();
        String message =  (analizedNb < 0) ? "Votre nombre est négatif" : "Votre nombre est positif";
        System.out.println(message + "\n(quand même c'est un peu la honte d'avoir besoin d'un programme pour savoir ça...)");

        // Exo 3.2

        System.out.println("\nDonne-moi un nombre");
        float nb1 = scanner.nextFloat();
        System.out.println("Donne-m'en un autre");
        float nb2 = scanner.nextFloat();
        scanner.nextLine();
        message = ((nb1 < 0) ^ (nb2 < 0)) ? "Le produit des deux nombres est négatif" : "Le produit des deux nombres est positif";
        System.out.println(message + "\n(encore une fois...¯\\_(ツ)_/¯)");

        // Exo 3.3

        System.out.println("\nDonne-moi un nom");
        String nameOne = scanner.nextLine();
        System.out.println("Un autre !");
        String nameTwo = scanner.nextLine();
        System.out.println("Un autre (promis c'est la dernière fois)");
        String nameThree = scanner.nextLine();

        if ((nameOne.compareTo(nameTwo) * nameTwo.compareTo(nameThree)) > 0){
            System.out.println("Ces trois noms sont dans l'ordre alphabétique");
        } else {
            System.out.println("Ces trois noms ne sont pas dans l'ordre alphabétique");
        }
    }
}
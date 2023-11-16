package org.example;

import java.util.ArrayList;
import java.util.List;
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


//        // Exo 3.4
//
//        System.out.println("\nDonnez-moi le nombre que vous voulez analyser");
//        float analizedNb;
//        String message;
//
//        analizedNb = scanner.nextFloat();
//
//        if (analizedNb < 0){
//            message = "Votre nombre est négatif";
//        } else if (analizedNb == 0){
//            message = "Votre nombre est nul";
//        } else {
//            message = "Votre nombre est positif";
//        }
//
//        System.out.println(message + "\n(quand même c'est un peu la honte d'avoir besoin d'un programme pour savoir ça...)");
//

//        // Exo 3.5
//
//        int age;
//        int poussinBreakpoint = 6;
//        int pupilleBreakpoint = 8;
//        int minimeBreakpoint = 10;
//        int cadetBreakpoint = 12;
//        int adulteBreakpoint = 18;
//
//        System.out.println("\nQuel est l'âge de votre gamin ?");
//        age = scanner.nextInt();
//        scanner.nextLine();
//
//        if (age < poussinBreakpoint){
//            message = "Votre gamin est trop jeune pour être admissible";
//        } else if (age < pupilleBreakpoint){
//            message = "Votre gamin est un poussin";
//        }else if (age < minimeBreakpoint){
//            message = "Votre gamin est une pupille";
//        }else if (age < cadetBreakpoint){
//            message = "Votre gamin est un minime";
//        }else if (age < adulteBreakpoint){
//            message = "Votre gamin est un cadet";
//        } else {
//            message = "Votre gamin n'est plus un gamin";
//        }
//
//        System.out.println(message);
//

//        // Exo 3.6
//
//        int intNb;
//
//        System.out.println("\nEntrez un nombre entier");
//        intNb = scanner.nextInt();
//
//        if (intNb%3 == 0){
//            System.out.println(intNb + " est divisible par 3");
//        } else {
//            System.out.println(intNb + " n'est pas divisible par 3");
//        }
//

//        // Exo 3.7
//
//        int firstBreakpoint = 10;
//        int secondBreakpoint = 20;
//        int wantedCopies;
//        float copyUnitPrice = 0.15f;
//        float totalPrice;
//
//        System.out.println("\nCombien de photocopies voulez-vous ?");
//        wantedCopies = scanner.nextInt();
//
//        if (wantedCopies > secondBreakpoint){
//            copyUnitPrice = 0.05f;
//        } else if (wantedCopies > firstBreakpoint){
//            copyUnitPrice = 0.10f;
//        }
//        totalPrice = copyUnitPrice*wantedCopies;
//
//        System.out.println("Vous devez " + copyUnitPrice + "€ pour chacune des " + wantedCopies +" photocopies.\nSoit un total de " + totalPrice+"€");
//

        // Exo 5.1

        int inputNb = 0;

        while (inputNb < 1 || inputNb > 3){
            System.out.println("\nDonnez-moi un nombre entier entre 1 et 3");
            inputNb = scanner.nextInt();
        }
        System.out.println("Merci");


        // Exo 5.2

        inputNb = 0;

        while (inputNb < 10 || inputNb > 20){
            System.out.println("\nDonnez-moi un nombre entier entre 10 et 20");
            inputNb = scanner.nextInt();

            if (inputNb < 10){
                System.out.println("Plus grand !");
            } else if (inputNb >20){
                System.out.println("Plus petit !");
            }
        }
        System.out.println("Merci");


        // Exo 5.3

        int beginningNb;
        System.out.print("\nOn va compter jusqu'à +10 à partir de ...");
        beginningNb = scanner.nextInt();
        System.out.println(++beginningNb);
        System.out.println(++beginningNb);
        System.out.println(++beginningNb);
        System.out.println(++beginningNb);
        System.out.println(++beginningNb);
        System.out.println(++beginningNb);
        System.out.println(++beginningNb);
        System.out.println(++beginningNb);
        System.out.println(++beginningNb);
        System.out.println(++beginningNb);


        // Exo 5.4

        System.out.print("\nOn va compter jusqu'à +10 à partir de ...");
        beginningNb = scanner.nextInt();

        for (int i = 0; i < 10 ; i++) {
            System.out.println(++beginningNb);
        }


        // Exo 5.5

        System.out.println("\nÉcrivons la table de...");
        beginningNb = scanner.nextInt();

        for (int i =1 ; i <= 10; i++) {
            System.out.println(beginningNb + " x " + i + " = " + i*beginningNb);
        }


        // Exo 5.6

        int sum = 0;

        System.out.println("\nÉcrivons la somme des entiers jusqu'à...");
        beginningNb = scanner.nextInt();

        for (int i = 1; i < beginningNb; i++) {
            sum += i;
        }

        System.out.println("Ca fait " + sum + "\n");


        // Exo 5.7

        int biggestNumber = Integer.MIN_VALUE;
        int[] array = new int[20];

        for (int i = 0 ; i < array.length ; i++ ) {
            System.out.println("\nEntrez le nombre numéro " + (i+1));
            array[i] = scanner.nextInt();
            biggestNumber = (array[i] > biggestNumber) ? array[i] : biggestNumber;
        }

        System.out.println("Le plus grand de ces nombres est " + biggestNumber);


        // Exo 5.7.2

        int position = 0;
        int[] arrayBis = new int[20];

        for (int i = 0 ; i < arrayBis.length ; i++ ) {
            System.out.println("\nEntrez le nombre numéro " + (i+1));
            arrayBis[i] = scanner.nextInt();
            position = (arrayBis[i] > arrayBis[position]) ? i : position;
        }

        System.out.println("Le plus grand de ces nombres est " + arrayBis[position] +" et c'était le numéro " + (position+1));


        // Exo 5.7.3 (officiellement 5.9)

        int positionBis = 0;
        int maxNumber = Integer.MIN_VALUE;
        int i = 0;
        int currentInputNb = 1;

        while (currentInputNb != 0){
            System.out.println("\nEntrez un nombre (entrez 0 pour arrêter)");
            currentInputNb = scanner.nextInt();

            if (currentInputNb != 0){
                if (currentInputNb > maxNumber){
                    positionBis = i;
                    maxNumber = currentInputNb;
                }
                i++;
            }
        }

        System.out.println("Le plus grand de ces nombres est " + maxNumber +" et c'était le numéro " + positionBis);


        // 5.8

        int fact = 1;
        System.out.println("\nEntrez le nombre dont on va calculer la factorielle");
        beginningNb = scanner.nextInt();

        for (int j = beginningNb; j > 0 ; j--) {
            fact *= j;
        }

        System.out.println(beginningNb + "! = " + fact);


        // 5.10

        int currentPrice = 1;
        int totalPrice = 0;
        int paidAmount;
        int debt;
        int tenNotesQuantity;
        int fiveNotesQuantity;

        while (currentPrice != 0){
            System.out.println("\nEntrez le prix d'un article (entrez 0 pour arrêter)");
            currentPrice = scanner.nextInt();

            if (currentPrice != 0){
                totalPrice += currentPrice;
            }
        }

        System.out.println("Vous nous devez " + totalPrice+"€. Payez-moi maintenant (je rends la monnaie)");
        paidAmount = scanner.nextInt();
        debt = paidAmount-totalPrice;
        System.out.println("Ok, je vous dois donc " + debt + "€");

        tenNotesQuantity = debt/10;
        debt -= (tenNotesQuantity*10);
        fiveNotesQuantity = debt/5;
        debt -= (fiveNotesQuantity*5);

        System.out.println("Très bien, je vais vous payer avec \n- "+tenNotesQuantity+" billets de 10€\n -" + fiveNotesQuantity + " billets de 5€\n- " + debt + " pièces de 1€\n");


        // Exo 5.11

        int i11 = 1;
        boolean success = false;
        int sum11;

        while (!success){
            sum11 = 0;
            for (int j = 0; j <= i11; j++) {
                sum11 += j;
            }
            if (sum11 > 100){
                success = true;
            } else {
                i11++;
            }
        }

        System.out.println("Le premier nombre entier dont la somme de 1 à N dépasse 100 est " + i11);


        scanner.close();
    }
}
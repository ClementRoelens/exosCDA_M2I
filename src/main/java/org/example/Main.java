package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static void personnalizedParse(String value) {
        try {
            System.out.println(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            System.out.println("Ce ne sont pas des nombres petit coquinou");
        }
    }

    public static void main(String[] args) {

        String wrongMessage = "iuerhnfguerhfuimehfuiqehfuilqerhiguhqseuighsghusrtg";
        String rightMessage = "64684";

        personnalizedParse(wrongMessage);
        personnalizedParse(rightMessage);

        String[] ameDesCartes = {
                "Dragon ailé, gardien de la forteresse",
                "Magicien des ténèbres",
                "Force mirroir",
                "Gaïa, le chevalier de la terre",
                "Squelette malfaisant"
        };

        try {
            System.out.println(ameDesCartes[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\nMais enfin Yugi, tu n'as pas encore pioché ta 6ème carte !");
        }

        int arraySize;
        String[] stringArray = new String[0];
        Scanner IRM = new Scanner(System.in);
        boolean isAllowedToContinue = false;
        int sum = 0;

        System.out.println("\nTu vas m'entrer un tableau d'entier.");

        while (!isAllowedToContinue) {
            System.out.println("D'abord donne-moi le nombre d'entiers que tu veux me donner");
            try {
                arraySize = IRM.nextInt();
                IRM.nextLine();
                stringArray = new String[arraySize];
                isAllowedToContinue = true;
            } catch (InputMismatchException e) {
                System.out.println("Ha ha ha, très drôle de ne pas saisir un nombre.\nRecommence\n");
                IRM.nextLine();
            }
        }

        for (int i = 0; i < stringArray.length; i++) {
            System.out.printf("Entre le %de entier\n",i+1);
            stringArray[i] = IRM.nextLine();
            try {
                int parsedValue = Integer.parseInt(stringArray[i]);
                sum += parsedValue;
            } catch (NumberFormatException e){
                System.out.println("Tu te crois drôle à ne pas rentrer de nombre entier ?");
            }
        }

        System.out.printf("\nLa somme des entiers de ton tableau fait %d",sum);
    }
}
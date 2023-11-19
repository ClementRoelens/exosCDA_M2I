package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static String readArray(int[] array){
        String message = "";
        for (int i = 0; i < array.length; i++) {
            message += i + " : " + array[i] + "\n";
        }
        return message;
    }
    
    static void fillArraySequentially(int[] array){
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
    }

    static void fillArrayRandomly(int[] array){
        Random rand = new Random();

        List<Integer> sequentialArray = new ArrayList<Integer>();

        for (int i = 0; i < array.length; i++) {
            sequentialArray.add(i);
        }

        for (int i = 0; i < array.length; i++) {
            int index = rand.nextInt(sequentialArray.size());
            array[i] = sequentialArray.get(index);
            sequentialArray.remove(index);
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Exo 5.5

        int array55Size;

        System.out.println("Combien d'éléments voulez-vous dans votre tableau ?");
        array55Size = scanner.nextInt();

        int[] array55 = new int[array55Size];

        fillArraySequentially(array55);

        System.out.println("Le tableau est : \n" + readArray(array55));


        System.out.println("Et maintenant, on inverse l'ordre");

        for (int i = 0; i < array55Size/2; i++) {
            int temp = array55[array55Size-i-1];
            array55[array55Size-i-1] = array55[i];
            array55[i] = temp;
        }

        System.out.println("Et voilà : \n" + readArray(array55));


//       Exo 5.6

        int [] array56 = new int[array55Size];


        fillArrayRandomly(array56);
        System.out.println("\nVoici un tableau de même taille rempli aléatoirement : \n" + readArray(array56));

        for (int i = 0; i < array56.length-1; i++) {
            for (int j = i+1; j < array56.length ; j++) {
                if (array56[j] < array56[i]){
                    int temp = array56[j];
                    array56[j] = array56[i];
                    array56[i] = temp;
                }
            }
        }

        System.out.println("Et maintenant, il est trié par sélection : \n" + readArray(array56));


        // Exo 5.7

        int [] array57 = new int[array55Size];


        fillArrayRandomly(array57);
        System.out.println("\nVoici un tableau de même taille rempli aléatoirement : \n" + readArray(array57));

        boolean isSwitched = false;

        do {
            isSwitched = false;
            for (int i = 0; i < array57.length-1; i++) {
                if (array57[i] > array57[i+1]){
                    isSwitched = true;
                    int temp = array57[i];
                    array57[i] = array57[i+1];
                    array57[i+1] = temp;
                }
            }
        } while (isSwitched);

        System.out.println("Et maintenant, il est trié à bulles : \n" + readArray(array57));

    }
}
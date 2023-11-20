package org.example;

import java.util.Arrays;
import java.util.Scanner;


public class Main {

    static boolean isAnagram(String wordOne, String wordTwo) {
        if (wordOne.length() != wordTwo.length()) {
            return false;
        }

        char[] arrayOne = wordOne.toLowerCase().toCharArray();
        char[] arrayTwo = wordTwo.toLowerCase().toCharArray();

        Arrays.sort(arrayOne);
        Arrays.sort(arrayTwo);

        return Arrays.compare(arrayOne,arrayTwo) == 0;
    }

    public static void main(String[] args) {

        Scanner IRM = new Scanner(System.in);


        // Exo 1 (comptage de mot)

        String sentence = "Mon petit oiseau a pris sa volée";
        int wordsNumber = sentence.split(" ").length;

        System.out.printf("\nIl y a %d mots dans la phrase \"%s\"", wordsNumber, sentence);


        // Exo 2 (occurrence)

        String word = "anticonstitutionnellement";
        int eNumber = word.split("e").length - 1;
        int aNumber = word.split("a").length - 1;
        int tNumber = word.split("t").length - 1;

        System.out.printf("\n\nIl y a %d e, %d a et %d t dans le mot %s", eNumber, aNumber, tNumber, word);


        // Exo 3 (anagramme)

        String wordOne;
        String wordTwo;
        int continueAnagrams;

        do {
            System.out.println("\n\nSaisir les deux mots dont vous voulez savoir s'ils sont des anagrammes");
            System.out.println("Mot 1 : ");
            wordOne = IRM.next();
            System.out.println("Mot 2 : ");
            wordTwo = IRM.next();

            String response = isAnagram(wordOne, wordTwo) ? " est un anagramme de " : " n'est pas un anagramme de ";

            System.out.println(wordOne+response+wordTwo);

            System.out.println("Appuyez sur 0 pour arrêter");
            continueAnagrams = IRM.nextInt();
        } while (continueAnagrams != 0);


        // Exo 4 (palindrome)

        String palindromWord;
        char[] charArrayWord;
        String invertedWord;
        String message;
        int continuePalindrom;

        do {
            System.out.println("\nEntrez le mot dont vous voulez savoir s'il est un palindrome");
            palindromWord = IRM.next();
            charArrayWord = palindromWord.toCharArray();

            for (int i = 0, j = charArrayWord.length - 1; i < charArrayWord.length / 2; i++, j--) {
                char temp = charArrayWord[i];
                charArrayWord[i] = charArrayWord[j];
                charArrayWord[j] = temp;
            }
            invertedWord = new String(charArrayWord);

            if (palindromWord.equals(invertedWord)){
                message = " est un palindrome";
            } else {
                message = " n'est pas un palindrome";
            }

            System.out.println(palindromWord + message);
            System.out.println("Appuyez sur 0 pour arrêter");
            continuePalindrom = IRM.nextInt();
        } while (continuePalindrom != 0);


        // Exo 5 (pyramide)

        int pyramidHeight;
        String line;

        System.out.println("\n Quelle hauteur pour la pyramide ? (fais pas le con, entre un nombre)");
        pyramidHeight = IRM.nextInt();

        for (int i = 1; i <= pyramidHeight; i++) {
            line = "";
            for (int j = 1; j <= i; j++) {
                line += "*";
            }
            System.out.println(line);
        }
        for (int i = pyramidHeight-1; i > 0 ; i--) {
            line = "";
            for (int j = 1; j <= i; j++) {
                line += "*";
            }
            System.out.println(line);
        }

        IRM.close();
    }
}
package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

//.................................'''........................ ..............
//.................................'''.......................................
//..........................''''','''....   .................................
//........................'''''''.....            .............''............
//.......................'''''........              ...........'''''''''.....
//.......................''','...cddol:;,,;;'..      ..''......',,,'''''.....
//.......................''''..,d0KOkxdddooolc:,.    ..'''.....',,,,,''''....
//.......................''''.'l0XX0xolllllccccc,.   ..'''.....',,,,,,''''...
//.......................''''.'dKK0Oxolcccccccc:,.   .''''.....',,,,,,''''...
//........................'',:cc:,,,;:::::;;,,;;,.   ..''''....'',,,''''''...
//........................'',oo;.    .,:,.     ........''''....'',,,'''''....
//.......................''':dO0o;...:oo;.     ..'.','.'''.....'''''''''.....
//.....................''''',d0K0kook0xoc,,'''',;,;;,'.''......'',,''''''''''
//.....................'''''';d0KOdoxxl;;,;:;;,,,',;'..........'',,,,,,,,,,''
//........................''''lKK0kxdl,..',;;,',''..............,,;;,,,,,,,,,
//............................:k0kxdc,''''',,,'''...............,,;,,,,,,,,',
//.............................:xkxdc'....'''''''...............',,,,,,,,,'''
//...............................lkxc;,,,,''.....'..............',,,,,,,,,'',
//.............................. .,:;''''...... .'..............',,,,,,,,,,''
//...........................            .....  ................',,,,,'''''''
//.........................                     ................',,,,,,''''''
//.....................                           ..............,;;;;,,,,,,,'
//...................                                  .  ......,::;;;,;;;,,'
//.................                                          ...,::;;,,;;;,,'
//..............                                               .,::;;,,;;;,,'

        Scanner IRM = new Scanner(System.in);


        // Exo 1

        int[][] matrixOne = {
                {45, 23, 34, 56, 30},
                {67, 75, 21, 52, 59},
                {89, 34, 19, 19, 15},
                {1, 78, 90, 48, 42}
        };
        int highestValue = Integer.MIN_VALUE;

        for (int[] tempMatrix : matrixOne) {
            for (int tempValue : tempMatrix) {
                if (tempValue > highestValue) {
                    highestValue = tempValue;
                }
            }
        }

        System.out.printf("La valeur la plus élevée est %d\n", highestValue);


        // Exo 2

        int linesNumber;
        int iExoTwo = 1;
        int sum = 0;
        long product = 1L;
        String matrixDesign;

        System.out.println("\nCombien de lignes voulez-vous dans votre matrice ?");
        linesNumber = IRM.nextInt();

        int [][] matrixTwo = new int[linesNumber][linesNumber+1];

        for (int i = 0; i < matrixTwo.length; i++) {
            for (int j = 0; j < matrixTwo[i].length; j++) {
                sum += iExoTwo;
                product *= iExoTwo;
                matrixTwo[i][j] = iExoTwo++;
            }
        }

        System.out.println("Votre matrice est : ");
        for (int[] tempMatrix : matrixTwo){
            System.out.println(Arrays.toString(tempMatrix));
        }
        System.out.printf("Sa somme est %d et son produit est %d\n",sum,product);


        // Exo 3

        String[][] matrixThree = new String[1][1];
        int nSellers;
        int nMarks;

        System.out.println("\nCombien de vendeurs ?");
        nSellers = IRM.nextInt();
        System.out.println("Combien de marques ?");
        nMarks = IRM.nextInt();
        IRM.nextLine();
        matrixThree = new String[nMarks+1][nSellers+1];
        matrixThree[0][0] = "X";

        for (int i = 1; i <= nMarks ; i++) {
            System.out.printf("Nom de la %de marque\n",i);
            matrixThree[i][0] = IRM.nextLine();
        }

        for (int i = 1; i <= nSellers; i++) {
            System.out.printf("Quel est le nom du %de vendeur ?\n",i);
            matrixThree[0][i] = IRM.nextLine();
            for (int j = 1; j <= nMarks; j++) {
                System.out.printf("Combien %s a-t-il vendu de %s (avec un 0 si moins de 10)?\n",matrixThree[0][i],matrixThree[j][0]);
                matrixThree[i][j] = IRM.nextLine();
            }
        }

        matrixDesign = "      ";
        for (int i = 1; i < matrixThree[0].length; i++) {
            matrixDesign += "| " + matrixThree[0][i] + " ";
        }
        for (int i = 1; i < matrixThree.length; i++) {
            matrixDesign += "\n______|______|______|______\n";
            matrixDesign += matrixThree[0][i];
            for (int j = 1; j < matrixThree[i].length; j++) {
                matrixDesign += "  |  " + matrixThree[i][j];
            }
        }

        System.out.println("Du coup on a  : \n" + matrixDesign);
    }
}
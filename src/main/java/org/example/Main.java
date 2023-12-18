package org.example;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void menu(Connection connection) {
        Scanner MRI = new Scanner(System.in);
        int choice = -1;

        System.out.println("\nQue voulez-vous faire ?\n" +
                "1 - Ajouter un étudiant\n" +
                "2 - Afficher la totalité des étudiants\n" +
                "3 - Afficher les étudiants d'une classe\n" +
                "4 - Supprimer un étudiant\n" +
                "0 - Arrêter\n");

        while (choice == -1) {
            try {
                choice = MRI.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Vous devez entrer un nombre entier entre 0 et 4");
                MRI.nextLine();
            }
        }

        switch (choice) {
            case 1 -> StudentUtils.addStudent(connection);
            case 2 -> StudentUtils.displayStudents(connection);
            case 3 -> StudentUtils.displayStudentsFromOneClasse(connection);
            case 4 -> StudentUtils.eraseStudent(connection);
        }
        if (choice != 0) {
            menu(connection);
        }
    }

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, "root", "1234");
            menu(connection);
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Malheureusement, la connexion à la base de données a échoué");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
}
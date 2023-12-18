package org.example;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentUtils {
    public static void addStudent(Connection connection) {
        Scanner MRI = new Scanner(System.in);
        String firstName;
        String lastName;
        int classNumber = -1;
        String gradeDateString;
        Date gradeDate = null;


        System.out.println("Saissiez le prénom");
        firstName = MRI.nextLine();
        System.out.println("Saissiez le nom de famille");
        lastName = MRI.nextLine();
        System.out.println("Saissiez le numéro de classe");

        while (classNumber == -1) {
            try {
                classNumber = MRI.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Vous devez entrer un nombre entier");
                MRI.nextLine();
            }
        }
        MRI.nextLine();

        System.out.println("Saissiez la date d'obtention du diplôme (au format 'YYYY-mm-DD')");
        while (gradeDate == null){
            gradeDateString = MRI.nextLine();
            try {
                gradeDate = Date.valueOf(gradeDateString);
            } catch (IllegalArgumentException e){
                System.out.println("Entrez une date au format '1991-09-02'");
            }
        }

        String query = "INSERT INTO students (first_name, last_name, class_number, grade_date) VALUES (? , ? , ? , ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setInt(3, classNumber);
            statement.setDate(4, gradeDate);

            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();

            while (result.next()) {
                System.out.printf("Id de %s %s : %d\n", firstName, lastName, result.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static void selectAndRead(String query, Connection connection) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.printf("Id %d : %s %s - Classe numéro %d - Diplomé le : %s\n",
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("class_number"),
                        resultSet.getDate("grade_date").toString());
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static void displayStudents(Connection connection) {
        String query = "SELECT * FROM students";
        selectAndRead(query, connection);
    }
    public static void displayStudentsFromOneClasse(Connection connection) {
        Scanner MRI = new Scanner(System.in);
        int classNumber = -1;

        System.out.println("De quelle classe voulez-vous les étudiants ?");

        while (classNumber == -1) {
            try {
                classNumber = MRI.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Vous devez entrer un nombre entier");
                MRI.nextLine();
            }
        }

        String query = "SELECT * FROM students WHERE class_number = " + classNumber;
        selectAndRead(query, connection);
    }
    public static void eraseStudent(Connection connection) {
        Scanner MRI = new Scanner(System.in);
        int id = -1;

        System.out.println("Entrez l'id de l'étudiant à supprimer");

        while (id == -1) {
            try {
                id = MRI.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Vous devez entrer un nombre entier");
                MRI.nextLine();
            }
        }

        String query = "DELETE FROM students WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            int nbResults = statement.executeUpdate(query);
            if (nbResults == 1) {
                System.out.println("L'étudiant a bien été supprimé");
            } else {
                System.out.println("Il semble que l'étudiant n'ait pas pu être supprimé. Vérifiez que l'ID que vous avez entré correspond bien à un étudiant !");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}

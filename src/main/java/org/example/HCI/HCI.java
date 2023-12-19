package org.example.HCI;

import org.example.DAO.DAO;
import org.example.Models.Student;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class HCI {

    private DAO dao;

    public HCI(){
        this.dao = new DAO();
    }

    public void menu() {
        Scanner MRI = new Scanner(System.in);
        int choice = -1;

        System.out.println("\nQue voulez-vous faire ?\n" +
                "1 - Ajouter un étudiant\n" +
                "2 - Afficher la totalité des étudiants\n" +
                "3 - Afficher les étudiants d'une classe\n" +
                "4 - Chercher un étudiant par prénom\n" +
                "5 - Chercher un étudiant par nom\n" +
                "6 - Supprimer un étudiant\n" +
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
            case 1 -> this.addStudent();
            case 2 -> {
                List<Student> students = this.dao.searchAllStudents();
                selectAndRead(students.toArray(Student[]::new));
            }
            case 3 -> {
                List<Student> students = this.displayStudentsFromOneClass();
                selectAndRead(students.toArray(Student[]::new));
            }
            case 4 -> {
                List<Student> students = this.searchStudentsByFirstname();
                selectAndRead(students.toArray(Student[]::new));
            }
            case 5 -> {
                List<Student> students = this.searchStudentsByLastname();
                selectAndRead(students.toArray(Student[]::new));
            }
            case 6 -> this.eraseStudent();
        }
        if (choice != 0) {
            menu();
        }
    }

    public void selectAndRead(Student ...students) {
         for (Student student : students){
             System.out.printf("Id : %d - %s %s - Classe numéro %d - Diplômé le %s\n",
                     student.getId(),
                     student.getFirstName(),
                     student.getLastName(),
                     student.getClassNumber(),
                     student.getGradeDate().toString());
         }
    }

    public void addStudent(){
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

        Student newStudent = new Student(0,firstName,lastName,classNumber,gradeDate);
        newStudent = this.dao.addStudent(newStudent);

        this.selectAndRead(newStudent);
    }

    public List<Student> displayStudentsFromOneClass(){
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

        return this.dao.searchStudentsFromOneClass(classNumber);
    }

    public List<Student> searchStudentsByFirstname(){
        Scanner MRI = new Scanner(System.in);
        String searchedName;

        System.out.println("Entrez le prénom à chercher");
        searchedName = MRI.nextLine();

        return this.dao.searchByFirstname(searchedName);
    }

    public List<Student> searchStudentsByLastname(){
        Scanner MRI = new Scanner(System.in);
        String searchedName;

        System.out.println("Entrez le prénom à chercher");
        searchedName = MRI.nextLine();

        return this.dao.searchByLastname(searchedName);
    }

    public void eraseStudent(){
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

        this.dao.eraseStudent(id);
    }
}

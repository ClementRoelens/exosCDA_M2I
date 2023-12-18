package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DAO {

    private Connection connection = null;

    private void openConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        this.connection = DriverManager.getConnection(url, "root", "1234");
    }


    public Student addStudent(Student student) {
        String query = "INSERT INTO students (first_name, last_name, class_number, grade_date) VALUES (? , ? , ? , ?)";
        Student newStudent = student;

        try {
            this.openConnection();
            PreparedStatement statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setInt(3, student.getClassNumber());
            statement.setDate(4, student.getGradeDate());

            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();


            while (result.next()) {
                newStudent.setId(result.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            try {
                this.connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

        return newStudent;
    }

    public List<Student> searchAllStudents() {
        return fetchStudents("SELECT * FROM students");
    }

    public List<Student> searchStudentsFromOneClass(int classNumber) {
        return fetchStudents("SELECT * FROM students WHERE class_number = " + classNumber);
    }

    public List<Student> searchByFirstname(String firstname){
        return this.fetchStudents(String.format("SELECT * FROM students WHERE first_name = '%s'",firstname));
    }

    public List<Student> searchByLastname(String lastname){
        return this.fetchStudents(String.format("SELECT * FROM students WHERE last_name = '%s'",lastname));
    }

    public List<Student> fetchStudents(String query) {
        List<Student> students = new ArrayList<>();
        try {
            this.openConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                students.add(new Student(resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("class_number"),
                        resultSet.getDate("grade_date")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                this.connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

        return students;
    }




    public void eraseStudent(int id) {
        try {
            this.openConnection();
            Statement statement = connection.createStatement();

            int nbResults = statement.executeUpdate("DELETE FROM students WHERE id = " + id);
            if (nbResults == 1) {
                System.out.println("L'étudiant a bien été supprimé");
            } else {
                System.out.println("Il semble que l'étudiant n'ait pas pu être supprimé. Vérifiez que l'ID que vous avez entré correspond bien à un étudiant !");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                this.connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
}

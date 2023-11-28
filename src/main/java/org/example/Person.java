package org.example;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private static int count;

    public Person(String firstName, String lastName) {
        this.id = count++;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return String.format("%s %s a l'id %d", this.firstName, this.lastName, this.id);
    }
}

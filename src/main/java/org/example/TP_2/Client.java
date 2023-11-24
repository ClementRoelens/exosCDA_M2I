package org.example.TP_2;

public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private static int counter;


    public Client(String firstName, String lastName, int phoneNumber) {
        this.id = counter;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    { counter++; }


    public int getId(){
        return this.id;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public int getPhoneNumber(){
        return this.phoneNumber;
    }
}

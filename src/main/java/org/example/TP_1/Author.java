package org.example.TP_1;

public class Author {
    private int id;
    private String firstName;
    private String lastName;
    private static int counter;


    public Author(String firstName, String lastName){
        this.id = counter;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    { counter++; }


    @Override
    public String toString(){
        return String.format("Je suis un auteur, %s %s d'id %d\n",this.firstName, this.lastName, this.id);
    }
}

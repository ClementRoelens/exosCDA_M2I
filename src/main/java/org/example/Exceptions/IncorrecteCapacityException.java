package org.example.Exceptions;

public class IncorrecteCapacityException extends RuntimeException{
    public IncorrecteCapacityException(){
        super("Vous tentez de créer un lieu avec une capacité négative");
    }
}

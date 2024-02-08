package org.example.exception;

public class NegativeOrNullException extends Exception {
    public NegativeOrNullException(){
        System.out.println("This value cannot be negative or null");
    }
}

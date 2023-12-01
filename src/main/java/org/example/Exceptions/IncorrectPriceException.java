package org.example.Exceptions;

public class IncorrectPriceException extends RuntimeException{
    public IncorrectPriceException(){
        super("Le prix du billet ne peut pas être inférieur ou égale à 0");
    }
}

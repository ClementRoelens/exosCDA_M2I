package org.example.Exceptions;

public class ElementDoesNotExistException extends Exception{
    public ElementDoesNotExistException(){
        super("L'id passé ne correspond à aucun élément existant");
    }
}

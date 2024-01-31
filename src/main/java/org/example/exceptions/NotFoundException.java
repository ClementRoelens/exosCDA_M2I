package org.example.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(){
        System.out.println("Aucune ville trouvée");
    }
}

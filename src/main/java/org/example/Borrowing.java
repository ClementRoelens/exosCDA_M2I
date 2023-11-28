package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Borrowing {
    private int id;
    private Person person;
    private Book book;
    private LocalDate beginningDate;
    private LocalDate endDate;
    private static int count;


    public Borrowing(Person person, Book book) {
        this.id = count++;
        this.person = person;
        this.book = book;
        this.beginningDate = LocalDate.now();
    }


    public Person getPerson() {
        return person;
    }
    public Book getBook() {
        return book;
    }
    public void closeBorrowing() {
        this.endDate = LocalDate.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String message = String.format("%s %s a emprunté %s le %s", this.person.getFirstName(), this.person.getLastName(), this.book.getTitle(), this.beginningDate.format(formatter));
        if (endDate != null) {
            message = String.format(message + " et l'a rendu le %s", this.endDate.format(formatter));
        }
        message = String.format(message + ". Cet emprunt est le numéro %d", this.id);
        return message;
    }
}

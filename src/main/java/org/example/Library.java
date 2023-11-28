package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books;
    private List<Borrowing> borrowings;
    private List<Borrowing> endedBorrowings;


    public Library() {
        this.books = new ArrayList<Book>();
        this.borrowings = new ArrayList<Borrowing>();
        this.endedBorrowings = new ArrayList<Borrowing>();
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public boolean removeBook(int bookId) {
        Book foundBook = this.books.stream().filter(book -> book.getId() == bookId).findFirst().orElse(null);
        if (foundBook != null) {
            this.books.remove(foundBook);
            return true;
        }
        return false;
    }

    public List<Book> findByTitle(String title) {
        return this.books.stream().filter(book -> book.getTitle().contains(title)).toList();
    }

    public List<Book> findByAuthor(String author) {
        return this.books.stream().filter(book -> book.getAuthor().equals(author)).toList();
    }

    public boolean borrow(int bookId, Person person) {
        // On vérifie si l'id donné correspond bien à un livre existant. Sinon on retourne false
        Book foundBook = this.findBookById(bookId);
        if (foundBook == null) {
            return false;
        }
        // Si le livre est un livre physique, il ne doit pas être présent dans la liste des emprunts pour être empruntable
        if (foundBook instanceof EBook || this.findBorrowingFromBookId(bookId) == null) {
            this.borrowings.add(new Borrowing(person, foundBook));
            return true;
        }
        return false;
    }

    public boolean returnBook(int bookId) {
        Book foundBook = this.findBookById(bookId);
        if (foundBook != null) {
            // L'emprunt contenant le livre doit exister
            Borrowing borrowing = this.findBorrowingFromBookId(bookId);
            if (borrowing != null) {
                this.borrowings.remove(borrowing);
                borrowing.closeBorrowing();
                this.endedBorrowings.add(borrowing);
                return true;
            }
            return false;
        }
        return false;
    }

    public List<Book> findAllBorrowingsFromOnePerson(int personId) {
        List<Borrowing> borrowings;
        borrowings = this.borrowings.stream().filter(borrowing -> borrowing.getPerson().getId() == personId)
                .collect(Collectors.toList());
        borrowings.addAll(this.borrowings.stream()
                .filter(borrowing -> borrowing.getPerson().getId() == personId)
                .collect(Collectors.toList()));
        return borrowings.stream()
                .map(Borrowing::getBook).collect(Collectors.toList());
    }

    private Book findBookById(int bookId) {
        return this.books.stream().filter(book -> book.getId() == bookId).findFirst().orElse(null);
    }

    private Borrowing findBorrowingFromBookId(int bookId) {
        return this.borrowings.stream()
                .filter(borrowing -> borrowing.getBook().getId() == bookId)
                .findFirst().orElse(null);
    }
}

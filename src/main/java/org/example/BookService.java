package org.example;

import java.util.Arrays;

public class BookService {

    private static void addBookToArray(Book book, Book[] booksArray) {
        booksArray = Arrays.copyOf(booksArray, booksArray.length + 1);
        booksArray[booksArray.length - 1] = book;
    }

    public static Book[] filterBooksByAuthor(Author seekedAuthor, Book[] books) {
        Book[] filteredBooks = new Book[0];
        for (Book book : books) {
            for (Author author : book.getAuthors()) {
                if (author == seekedAuthor) {
                    BookService.addBookToArray(book, filteredBooks);
                }
                break;
            }
        }
        return filteredBooks;
    }

    public static Book[] filterBooksByPublisher(Publisher publisher, Book[] books) {
        Book[] filteredBooks = new Book[0];
        for (Book book : books) {
            if (book.getPublisher() == publisher) {
                BookService.addBookToArray(book, filteredBooks);
            }
        }
        return filteredBooks;
    }

    public static Book[] filterBooksAfterSpecifiesyear(int yearFromInclusively, Book[] books) {
        Book[] filteredBooks = new Book[0];
        for (Book book : books) {
            if (book.getPublishingYear() >= yearFromInclusively) {
                BookService.addBookToArray(book, filteredBooks);
            }
        }
        return filteredBooks;
    }
}

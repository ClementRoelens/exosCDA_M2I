package org.example.TP_1;

import org.example.TP_2.Client;

import java.util.Arrays;

public class BookService {

    private static Book[] addBookToArray(Book book, Book[] booksArray) {
        Book[] newBooksArray = Arrays.copyOf(booksArray, booksArray.length + 1);
        newBooksArray[newBooksArray.length - 1] = book;
        return newBooksArray;
    }

    public static Book[] filterBooksByAuthor(Author seekedAuthor, Book[] books) {
        Book[] filteredBooks = new Book[0];
        for (Book book : books) {
            for (Author author : book.getAuthors()) {
                if (author == seekedAuthor) {
                    filteredBooks = BookService.addBookToArray(book, filteredBooks);
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
                filteredBooks = BookService.addBookToArray(book, filteredBooks);
            }
        }
        return filteredBooks;
    }

    public static Book[] filterBooksAfterSpecifiesyear(int yearFromInclusively, Book[] books) {
        Book[] filteredBooks = new Book[0];
        for (Book book : books) {
            if (book.getPublishingYear() >= yearFromInclusively) {
                filteredBooks = BookService.addBookToArray(book, filteredBooks);
            }
        }
        return filteredBooks;
    }
}

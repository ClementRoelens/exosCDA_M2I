package org.example;


import java.util.List;

public class Main {

    private static void tryBorrow(boolean trying){
        if (trying){
            System.out.println("Tout s'est bien passé");
        } else {
            System.out.println("Le livre n'a pas pu être emprunté");
        }
    }

    private static void displayBooks(List<Book> books){
        for (Book book : books){
            System.out.println(book);
        }
    }

    public static void main(String[] args) {
        Library library = new Library();

        Person clement = new Person("Clément", "Roelens");
        Person nassim = new Person("Nassim","Sakhri");
        Person olivia = new Person("Olivia","Pigani");

        // Merci ChatGPT pour la section suivante

        library.addBook(new PaperBook("To Kill a Mockingbird", "Harper Lee", 281, "J.B. Lippincott & Co."));
        library.addBook(new EBook("1984", "George Orwell", "PDF", 1024));
        library.addBook(new PaperBook("The Great Gatsby", "F. Scott Fitzgerald", 180, "Charles Scribner's Sons"));
        library.addBook(new EBook("Brave New World", "Aldous Huxley", "EPUB", 768));
        library.addBook(new PaperBook("The Catcher in the Rye", "J.D. Salinger", 224, "Little, Brown and Company"));
        library.addBook(new EBook("Animal Farm", "George Orwell", "MOBI", 512));
        library.addBook(new PaperBook("Lord of the Flies", "William Golding", 224, "Faber and Faber"));
        library.addBook(new EBook("Fahrenheit 451", "Ray Bradbury", "AZW", 720));
        library.addBook(new PaperBook("One Hundred Years of Solitude", "Gabriel García Márquez", 417, "Harper & Row"));
        library.addBook(new EBook("The Hobbit", "J.R.R. Tolkien", "PDF", 819));
        library.addBook(new PaperBook("The Lord of the Rings", "J.R.R. Tolkien", 1178, "Allen & Unwin"));
        library.addBook(new EBook("Dune", "Frank Herbert", "EPUB", 896));
        library.addBook(new PaperBook("Crime and Punishment", "Fyodor Dostoevsky", 671, "The Russian Messenger"));
        library.addBook(new EBook("The Shining", "Stephen King", "MOBI", 640));
        library.addBook(new PaperBook("Pride and Prejudice", "Jane Austen", 279, "T. Egerton, Whitehall"));
        library.addBook(new EBook("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", "PDF", 432));
        library.addBook(new PaperBook("Wuthering Heights", "Emily Brontë", 342, "Thomas Cautley Newby"));
        library.addBook(new EBook("The Martian", "Andy Weir", "AZW", 698));
        library.addBook(new PaperBook("Gone with the Wind", "Margaret Mitchell", 1037, "Macmillan Publishers"));
        library.addBook(new EBook("The Da Vinci Code", "Dan Brown", "EPUB", 1123));
        library.addBook(new PaperBook("The Odyssey", "Homer", 442, "Homerica"));
        library.addBook(new EBook("The Road", "Cormac McCarthy", "MOBI", 589));
        library.addBook(new PaperBook("Moby-Dick", "Herman Melville", 544, "Harper & Brothers"));
        library.addBook(new EBook("The Picture of Dorian Gray", "Oscar Wilde", "EPUB", 374));
        library.addBook(new PaperBook("The Adventures of Sherlock Holmes", "Arthur Conan Doyle", 307, "George Newnes Ltd"));
        library.addBook(new EBook("The Hunger Games", "Suzanne Collins", "PDF", 416));
        library.addBook(new PaperBook("The Count of Monte Cristo", "Alexandre Dumas", 1184, "Le Journal des Débats"));
        library.addBook(new EBook("The Girl with the Dragon Tattoo", "Stieg Larsson", "AZW", 672));
        library.addBook(new PaperBook("The War of the Worlds", "H.G. Wells", 209, "Pearson's Magazine"));
        library.addBook(new EBook("The Catch-22", "Joseph Heller", "MOBI", 453));

        // Maintenant qu'on a nos personnes, notre bibliothèque avec des livres, on va tester nos opérations

        System.out.println("Nassim veut emprunter un livre de J.R.R. Tolkien. Voici les livres disponibles : ");
        displayBooks(library.findByAuthor("J.R.R. Tolkien"));
        System.out.println("Nassim choisit The Lord of the Rings");
        tryBorrow(library.borrow(10,nassim));
        System.out.println("Mais Olivia aussi veut emprunter The Lord of the Rings");
        tryBorrow(library.borrow(10,olivia));
        System.out.println("Elle se rabat donc sur The Hobbit");
        tryBorrow(library.borrow(9,olivia));
        System.out.println("Clément veut aussi emprunter The Hobbit");
        tryBorrow(library.borrow(9,clement));
        System.out.println("Clément a fini de lire The Hobbit");
        library.returnBook(9);
        System.out.println("Il va maintenant emprunter The War of the Worlds. Mais il faut connaître son id");
        displayBooks(library.findByTitle("The War of the Worlds"));
        System.out.println("Clément emprunte The War of the Worlds");
        tryBorrow(library.borrow(28, clement));
        System.out.println("Mais il veut aussi emprunter The Da Vinci Code");
        displayBooks(library.findByTitle("The Da Vinci Code"));
        tryBorrow(library.borrow(19,clement));
        System.out.println("Nassim a fini de lire The lord of the rings");
        library.returnBook(10);
        System.out.println("Olivia se précipite dessus");
        tryBorrow(library.borrow(10, olivia));
        System.out.println("\nFaisons la liste des emprunts de Clément");
        displayBooks(library.findAllBorrowingsFromOnePerson(clement.getId()));
    }
}
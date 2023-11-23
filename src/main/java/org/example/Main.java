package org.example;

public class Main {
    public static void main(String[] args) {
        Author clementRoelens = new Author("Clément", "Roelens");
        Author nassimSakhri = new Author("Nassim", "Sakhri");
        Author oliviaPigani = new Author("Olivia","Pigani");

        Publisher petitEditions = new Publisher("PetitEditions");
        Publisher jospinBooks = new Publisher("JospinBooks");
        Publisher violentPublisher = new Publisher("ViolencePublishing");

        Book bookHair = new Book("Comment entretenir ses cheveux", new Author[]{clementRoelens, nassimSakhri},petitEditions,2005,20,19.99D,CoverType.SOFT);
        Book bookSpeedrun = new Book("Les meilleures techniques de Speedrun pour Twitter", new Author[]{nassimSakhri},petitEditions,2023,5,5D,CoverType.SOFT);
        Book bookHipster = new Book("Les hipster : ces nouveaux envahisseurs", new Author[]{oliviaPigani},violentPublisher,2017,354,24.45D,CoverType.BOUND);
        Book bookSubway = new Book("Les meilleurs Subway de la métropole lilloise", new Author[]{clementRoelens},jospinBooks,2012,25,10,CoverType.BOUND);
        Book bookKFC = new Book("Les meilleurs KFC du coin", new Author[]{oliviaPigani, nassimSakhri}, jospinBooks, 2020,10,20,CoverType.BOUND);
        Book bookAssassin = new Book("Assassiner discrètement", new Author[]{nassimSakhri},violentPublisher,2018,666,66.6D,CoverType.BOUND);
        Book bookSardou = new Book("Les meilleures chansons de Michel Sardou", new Author[]{clementRoelens, nassimSakhri, oliviaPigani},jospinBooks,2022,2,0.99D,CoverType.SOFT);

        System.out.println("On a créé plusieurs livres : ");
        System.out.println(bookHair);
        System.out.println(bookSpeedrun);
        System.out.println(bookHipster);
        System.out.println(bookSubway);
        System.out.println(bookKFC);
        System.out.println(bookAssassin);
        System.out.println(bookSardou);
        System.out.println("Et maintenant, on va filtrer");

        Book[] allBooks = {bookAssassin,bookHipster,bookHair,bookKFC,bookSardou,bookSubway,bookSpeedrun};
        Book[] booksFrom2020 = BookService.filterBooksAfterSpecifiesyear(2020,allBooks);
        Book[] booksFromNassim = BookService.filterBooksByAuthor(nassimSakhri,allBooks);
        Book[] booksFromClement = BookService.filterBooksByAuthor(clementRoelens,allBooks);
        Book[] booksFromOlivia = BookService.filterBooksByAuthor(oliviaPigani, allBooks);
        Book[] booksFromPetit = BookService.filterBooksByPublisher(petitEditions,allBooks);
        Book[] booksFromJospin = BookService.filterBooksByPublisher(jospinBooks, allBooks);
        Book[] booksFromPauline = BookService.filterBooksByPublisher(violentPublisher, allBooks);

        System.out.println("Voici les livres sortis depuis 2020 : ");
        for (Book book : booksFrom2020){
            System.out.println(book.getName());
        }System.out.println("Voici les livres de Nassim : ");
        for (Book book : booksFromNassim){
            System.out.println(book.getName());
        }System.out.println("Voici les livres de Clément : ");
        for (Book book : booksFromClement){
            System.out.println(book.getName());
        }System.out.println("Voici les livres publiés par PetitEditions : ");
        for (Book book : booksFromPetit) {
            System.out.println(book.getName());
        }
    }
}
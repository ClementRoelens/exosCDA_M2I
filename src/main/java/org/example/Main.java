package org.example;

import org.example.classes.*;

public class Main {
    private static void tryAddLine(boolean trying){
        if (trying){
            System.out.println("Article ajouté à la facture");
        } else {
            System.out.println("Article non ajouté : vous avez sûrement dépassé la limite d'articles autorisés");
        }
    }
    public static void main(String[] args) {
        ArticleService.addArticle(new Ream("Papier A4", 5.99f, 500));
        ArticleService.addArticle(new Ream("Papier Lettre", 4.99f, 500));
        ArticleService.addArticle(new Pencil("Crayon HB", 0.99f, "Jaune"));
        ArticleService.addArticle(new Pencil("Crayon 2B", 1.29f, "Noir"));
        Ream legalPaper = new Ream("Papier juridique", 7.49f, 500);
        ArticleService.addArticle(legalPaper);
        ArticleService.addArticle(new Batch(legalPaper, 2,10));
        Pencil redPencil = new Pencil("Crayon Rouge", 1.49f, "Rouge");
        ArticleService.addArticle(redPencil);
        ArticleService.addArticle(new Batch(redPencil, 5, 5));
        ArticleService.addArticle(new Ream("Papier à Dessin", 9.99f, 250));
        ArticleService.addArticle(new Pencil("Crayon Bleu", 1.29f, "Bleu"));
        Ream drawingPaper = new Ream("Papier à Dessin Artistique", 11.99f, 300);
        ArticleService.addArticle(drawingPaper);
        ArticleService.addArticle(new Batch(drawingPaper,5,15));
        Ream watercolorPaper = new Ream("Papier Aquarelle", 14.99f, 200);
        ArticleService.addArticle(watercolorPaper);
        ArticleService.addArticle(new Pencil("Crayon Vert", 1.19f, "Vert"));
        ArticleService.addArticle(new Batch(watercolorPaper, 3, 15));

        System.out.println("Voici tous les articles disponibles dans ma papeterie : \n");
        for (Article article : ArticleService.getAllArticles().values()){
            System.out.println(article);
        }

        System.out.println("\nClément achète deux lots de 2 papiers juridique et un crayon 2B");
        Invoice invoiceOne = new Invoice("Clément","29/11/2023");
        tryAddLine(invoiceOne.addLine(5,2));
        tryAddLine(invoiceOne.addLine(3,1));
        System.out.println(invoiceOne);

        System.out.println("\nOlivia achète un crayon rouge, 2 crayon bleu et 4 paquets de papier lettre");
        Invoice invoiceTwo = new Invoice("Olivia","30/11/2023");
        tryAddLine(invoiceTwo.addLine(6,1));
        tryAddLine(invoiceTwo.addLine(9,2));
        tryAddLine(invoiceTwo.addLine(1,4));
        System.out.println(invoiceTwo);

        System.out.println("\nChristophe va acheter TOUT ce qu'il y a de disponible");
        Invoice invoiceThree = new Invoice("Christophe","01/12/2023");
        for (Article article : ArticleService.getAllArticles().values()){
            tryAddLine(invoiceThree.addLine(article.getId(),1));
        }
        System.out.println(invoiceThree);
    }
}
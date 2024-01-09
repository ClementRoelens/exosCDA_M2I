package Controller;

import Entities.Product;
import Service.ProductService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class HCI {
    private Scanner scanner = new Scanner(System.in);

    private ProductService productService = ProductService.getInstance();





    private int scanInt(String message) {
        int returnedValue = 0;
        while (returnedValue == 0) {
            try {
                System.out.println(message);
                returnedValue = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrez un nombre entier positif");
            } finally {
                scanner.nextLine();
            }
        }
        return returnedValue;
    }
    private int scanInt(String message, int defaultValue) {
        int returnedValue = 0;

        System.out.println(message);

        try {
            returnedValue = scanner.nextInt();
        } catch (InputMismatchException e) {
            returnedValue = defaultValue;
        } finally {
            scanner.nextLine();
        }

        return (returnedValue == 0 ? defaultValue : returnedValue);
    }
    private double scanDouble(String message) {
        double returnedValue = 0D;
        while (returnedValue == 0D) {
            try {
                System.out.println(message);
                returnedValue = scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Entrez un nombre, utilisez la virgule et pas le point");
            } finally {
                scanner.nextLine();
            }
        }
        return returnedValue;
    }
    private double scanDouble(String message, double defaultValue) {
        double returnedValue;

        System.out.println(message);

        try {
            returnedValue = scanner.nextDouble();
        } catch (InputMismatchException e) {
            returnedValue = defaultValue;
        } finally {
            scanner.nextLine();
        }

        return (returnedValue == 0D ? defaultValue : returnedValue);
    }
    private String scanString(String message) {
        String returnedValue = "";
        while (returnedValue.isEmpty()) {
            System.out.println(message);
            returnedValue = scanner.nextLine();
        }
        return returnedValue;
    }
    private String scanString(String message, String defaultValue) {
        String returnedValue;

        System.out.println(message);
        returnedValue = scanner.nextLine();

        return (returnedValue.isEmpty() ? defaultValue : returnedValue);
    }
    private Date scanDate(String message) {
        Date returnedValue = null;

        while (returnedValue == null) {
            System.out.println(message);
            try {
                returnedValue = Date.valueOf(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Format invalide");
            }
        }
        return returnedValue;
    }
    private Date scanDate(String message, Date defaultValue) {
        System.out.println(message);
        try {
            return Date.valueOf(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            return defaultValue;
        }

    }






    public void menu() {
        int choice;

        choice = scanInt("\n" +
                "Que voulez-vous faire ?\n" +
                "1 - Afficher tous les produits\n" +
                "2 - Afficher les produits d'une marque\n" +
                "3 - Afficher les produits commandés entre deux dates\n" +
                "4 - Afficher les produits au stock inférieur à...\n" +
                "5 - Afficher la valeur totale cumulée d'une marque\n" +
                "6 - Afficher le prix moyen de tous les produits\n" +
                "7 - Créer un produit\n" +
                "8 - Modifier un produit\n" +
                "9 - Supprimer un produit\n" +
                "10 - Supprimer tous les produits d'une marque\n" +
                "0 - Au revoir");

        switch (choice) {
            case 0 -> close();
            case 1 -> getProducts();
            case 2 -> getProductsFromOneMark();
            case 3 -> getProductsAccordingToDates();
            case 4 -> getProductsAccordingToStock();
            case 5 -> showTotalStockFromOneMark();
            case 6 -> showAveragePrice();
            case 7 -> createProduct();
            case 8 -> editProduct();
            case 9 -> removeProduct();
            case 10 -> removeAllProductFromOneMark();
        }

        if (choice != 0) {
            menu();
        }
    }

    private void getProducts() {
        for (Product p : productService.getProducts()) {
            System.out.println(p);
        }
    }

    private void getProductsFromOneMark() {
       String mark;
       List<Product> products;

       mark = scanString("Entrez la marque");
       products = productService.getProductsFromMark(mark);

       if (!products.isEmpty()){
           for (Product p : products){
               System.out.println(p);
           }
       } else {
           System.out.println("Il n'y a aucun produit de cette marque");
       }
    }

    public void showAveragePrice(){
        System.out.printf("Prix moyen : %.2f€\n", productService.getAveragePrice());
    }

    private void showTotalStockFromOneMark(){
        String mark;
        double totalValue;

        mark = scanString("Entrez la marque");
        totalValue = productService.getTotalValueFromMark(mark);

        if (totalValue > -1){
            System.out.printf("%s a une valeur de produits cumulés à %.2f€\n", mark, totalValue);
        } else {
            System.out.println("Il n'y a apparemment aucun produit de " + mark);
        }
    }

    private void getProductsAccordingToDates() {
        Date dateOne;
        Date dateTwo;
        List<Product> products;

        dateOne = scanDate("Entrez la date inférieure (au format 2023-12-31)");
        dateTwo = scanDate("Entrez la date supérieure (au format 2023-12-31)");
        products = productService.getProductsBetweenTwoDates(dateOne, dateTwo);

        if (!products.isEmpty()){
            for (Product p : products) {
                System.out.println(p);
            }
        } else {
            System.out.println("Il semble qu'aucun produit ne corresponde à votre critère");
        }
    }

    private void getProductsAccordingToStock() {
        int stock;
        List<Product> products;

        stock = scanInt("Quelle est la limite de stock pour les produits à afficher ?");
        products = productService.getProductsWhereStockLowerThan(stock);

        if (!products.isEmpty()){
            for (Product p : products){
                System.out.println(p);
            }
        } else {
            System.out.println("Il semble qu'aucun produit ne corresponde à votre critère");
        }

    }

    private void createProduct() {
        String reference;
        String mark;
        Date buyDate;
        double price;
        int stock;
        Product product;

        reference = scanString("Entrez le nom de votre produit");
        mark = scanString("Entrez la marque de votre produit");


        price = scanDouble("Entrez un prix (avec une virgule et pas un point)");
        stock = scanInt("Entrez le stock du produit");
        buyDate = scanDate("Entrez la date d'achat (au format 2023-12-31");
        product = new Product(reference, mark, buyDate, price, stock);
        product = productService.postProduct(product);

        if (product != null) {
            System.out.println("Produit créé, son id est " + product.getId());
        } else {
            System.out.println("Produit non-créé, une erreur est arrivée");
        }
    }

    private void editProduct() {
        int productId;
        Product product;
        String reference;
        String mark;
        String strDate;
        Date buyDate = null;
        double price;
        int stock;
        boolean success;

        productId = scanInt("Entrez l'id du produit à modifier");
        product = productService.getProduct(productId);

        if (product != null) {
            reference = scanString("Le nom du produit actuel est " + product.getReference() +
                    "\nEntrez le nouveau nom ou rien pour le laisser tel quel", product.getReference());
            product.setReference(reference);
            mark = scanString("Le nom de la marque actuelle est " + product.getMark() +
                    "\nEntrez la nouvelle marque ou rien pour le laisser tel quel", product.getMark());
            product.setMark(mark);
            buyDate = scanDate("La date d'achat actuelle est " + product.getBuyDate() +
                    "\nEntrez la nouvelle date d'achat (format 2023-12-31) ou rien pour laisser telle quelle", product.getBuyDate());
            product.setBuyDate(buyDate);
            price = scanDouble("Le prix actuel est " + product.getPrice() +
                    "\nEntrez un nouveau prix (avec une virgule et pas un point) ou 0 pour laisser tel quel", product.getPrice());
            product.setPrice(price);
            stock = scanInt("Le stock actuel est " + product.getStock() +
                    "\nEntrez le nouveau stock du produit ou 0 pour laisser tel quel", product.getStock());
            product.setStock(stock);

            success = productService.updateProduct(product);

            System.out.println(success ? "Produit bien mis à jour" : "Produit non mis à jour");
        } else {
            System.out.println("Cet id ne correspond à aucun produit");
        }

    }

    private void removeProduct() {
        int id;
        boolean success;

        id = scanInt("Entrez l'id du produit à supprimer");

        success = productService.deleteProduct(id);

        System.out.println(success ? "Produit supprimé" : "Produit non supprimé (sûrement un mauvais id)");
    }

    private void removeAllProductFromOneMark(){
        String mark = scanString("Entrez la marque");
        int result = productService.deleteAllFromOneMark(mark);

        System.out.println(result + " produits ont été supprimés");
    }

    private void close() {
        productService.close();
        System.out.println("Très bien, au revoir");
    }

}

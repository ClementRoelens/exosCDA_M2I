package Controller;

import Entities.Product;
import Service.ProductService;

import java.sql.Date;
import java.util.InputMismatchException;
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



    public void menu(){
        int choice;

        choice = scanInt("Que voulez-vous faire ?\n" +
                "1 - Afficher tous les produits\n" +
                "2 - Afficher un produit par id\n" +
                "3 - Créer un produit\n" +
                "4 - Modifier un produit\n" +
                "5 - Supprimer un produit\n" +
                "6 - Au revoir");
        
        switch (choice){
            case 1 -> getProducts();
            case 2 -> getOneProduct();
            case 3 -> createProduct();
            case 4 -> editProduct();
            case 5 -> removeProduct();
            case 6 -> close();
        }

        if (choice != 6){
            menu();
        }
    }

    private void getProducts() {
        for (Product p : productService.getProducts()){
            System.out.println(p);
        }
    }

    private void getOneProduct() {
        int id;
        Product product;

        id = scanInt("Entrez l'id du produit que vous voulez afficher");
        product = productService.getProduct(id);

        if (product != null){
            System.out.println(product);
        } else {
            System.out.println("Cet id ne correspond à aucun produit");
        }
    }

    private void createProduct() {
        String reference;
        String mark;
        String strDate;
        Date buyDate = null;
        double price;
        int stock;
        Product product;

        reference = scanString("Entrez le nom de votre produit");
        mark = scanString("Entrez la marque de votre produit");

        while (buyDate == null){
            strDate = scanString("Entrez la date d'achat (format 2023-12-31)");
            try {
                buyDate = Date.valueOf(strDate);
            } catch (IllegalArgumentException e){
                System.out.println("Format invalide");
            }
        }
        price = scanDouble("Entrez un prix (avec une virgule et pas un point)");
        stock = scanInt("Entrez le stock du produit");

        product = new Product(reference, mark, buyDate, price, stock);
        product = productService.postProduct(product);

        if (product != null){
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

        if (product != null){
            reference = scanString("Le nom du produit actuel est " + product.getReference() +
                    "\nEntrez le nouveau nom ou rien pour le laisser tel quel", product.getReference());
            product.setReference(reference);
            mark = scanString("Le nom de la marque actuelle est " + product.getMark() +
                    "\nEntrez la nouvelle marque ou rien pour le laisser tel quel", product.getMark());
            product.setMark(mark);
            while (buyDate == null){
                strDate = scanString("La date d'achat actuelle est " + product.getBuyDate() +
                        "\nEntrez la nouvelle date d'achat (format 2023-12-31) ou rien pour laisser telle quelle", product.getBuyDate().toString());
                try {
                    buyDate = Date.valueOf(strDate);
                } catch (IllegalArgumentException e){
                    System.out.println("Format invalide");
                }
            }
            product.setBuyDate(buyDate);
            price = scanDouble("Le prix actuel est " + product.getPrice() +
                    "\nEntrez un nouveau prix (avec une virgule et pas un point) ou 0 pour laisser tel quel" , product.getPrice());
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

    private void close() {
        productService.close();
        System.out.println("Très bien, au revoir");
    }

}

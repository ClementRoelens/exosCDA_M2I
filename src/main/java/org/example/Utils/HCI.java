package org.example.Utils;

import org.example.Models.Account;
import org.example.Models.Client;
import org.example.Models.Operation;
import org.example.Models.Status;
import org.example.Service.Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class HCI {
    Scanner scanner = new Scanner(System.in);
    Service service = new Service();

    public void menu() {
        int choice = 0;

        System.out.println("Bonjour cher utilisateur. Êtes-vous déjà client chez nous ?\n");
        while (choice != 1 && choice != 2) {
            System.out.println("1 - Oui\n2 - Non");
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrez un nombre entier");
            } finally {
                scanner.nextLine();
            }
        }

        if (choice == 1) {
            int id = -1;

            while (id == -1) {
                try {
                    System.out.println("Entrez votre identifiant");
                    id = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Entrez un nombre entier");
                }
                finally {
                    scanner.nextLine();
                }
            }

            Client client = service.getClient(id);

            if (client != null) {
                clientMenu(client);
            } else {
                System.out.println("Désolé, il semble que vous n'ayez pas de compte chez nous");
            }
        } else {
            createClient();
        }
    }

    public void clientMenu(Client client) {
        int choice = -1;

        System.out.printf("Bienvenue cher(e) %s %s. Voulez-vous créer un nouveau compte ou effectuer des opérations sur l'un des vôtres ?\n"
                , client.getFirstName(), client.getLastname());

        while (choice != 0 && choice != 1 && choice != 2) {
            System.out.println("1 - Créer un nouveau compte\n" +
                    "2 - Effectuer des opérations sur un compte existant\n" +
                    "0 - Retour à l'accueil");
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrez un nombre");
            } finally {
                scanner.nextLine();
            }

            switch (choice) {
                case 0 -> menu();
                case 1 -> {
                    if (service.createAccount(client.getId()) != null) {
                        System.out.println("Votre compte a bien été créé");
                    } else {
                        System.out.println("Votre compte n'a pas pu être créé");
                    }
                }
                case 2 -> operationChoice(client);
            }
        }


        menu();
    }


    public void operationChoice(Client client) {
        List<Account> accounts = client.getAccounts();
        Account account = accounts.get(0);
        int choice = -1;
        boolean correctChoice = false;

        if (accounts.size() > 1) {
            System.out.println("Sur quel compte voulez-vous agir ?");

            while (!correctChoice) {
                for (int i = 0; i < accounts.size(); i++) {
                    System.out.printf("Compte numéro %d : %.2f€\n", i + 1, accounts.get(i).getBalance());
                }
                try {
                    account = accounts.get(scanner.nextInt());
                    correctChoice = true;
                } catch (InputMismatchException e) {
                    System.out.println("Entrez un nombre entier");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Ce nombre ne correspond à aucun de vos comptes");
                } finally {
                    scanner.nextLine();
                }
            }
        }

        while (choice != 0 && choice != 1 && choice != 2 && choice != 3) {
            System.out.println("Que voulez-vous faire ?\n" +
                    "1 - Afficher les opérations\n" +
                    "2 - Dépôt\n" +
                    "3 - Retrait\n" +
                    "0 - Retour");
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrez un nombre");
            } finally {
                scanner.nextLine();
            }

            switch (choice) {
                case 1 -> displayAccount(account);
                case 2 -> operation(account, true);
                case 3 -> operation(account, false);
            }
        }

        clientMenu(client);
    }

    public void displayAccount(Account account) {
        System.out.printf("Solde : %.2f€\n", account.getBalance());
        for (int i = account.getOperations().size() - 1; i >= 0; i--) {
            Operation operation = account.getOperations().get(i);
            String operationValue = (operation.getStatus() == Status.DEPOSIT) ? "Dépôt" : "Retrait";
            System.out.printf("%d - %.2f€ - %s\n", operation.getId(), operation.getAmount(), operationValue);
        }
    }

    public void operation(Account account, boolean isDeposit) {
        String operation = isDeposit ? "dépôt" : "retrait";
        System.out.println("Montant du " + operation);
        double amount = 0;
        while (amount <= 0) {
            try {
                amount = scanner.nextDouble();

            } catch (InputMismatchException e) {
                System.out.println("Entrez un nombre");
            } finally {
                scanner.nextLine();
            }
        }

        boolean result = service.depositOrWithdraw(account.getId(), amount, isDeposit);

        if (result) {
            account = service.getAccount(account.getId());
            System.out.printf(operation + " bien effectué. Nouveau solde : %.2f€\n", account.getBalance());
        } else {
            System.out.printf("Votre %s n'a pas pu être effectué\n", operation);
        }
    }

    public void createClient() {
        String firstName;
        String lastName;

        System.out.println("Entrez votre prénom");
        firstName = scanner.nextLine();
        System.out.println("Entrez votre nom");
        lastName = scanner.nextLine();

        Client client = service.createClient(firstName, lastName);

        if (client != null) {
            System.out.printf("Votre compte client a été créé avec succès, et un compte bancaire également\n" +
                            "Votre id est %d et l'id de votre compte est %d\n",
                    client.getId(), client.getAccounts().get(0).getId());
        } else {
            System.out.println("Votre compte n'a pas pu être créé");
        }

        menu();
    }
}

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
            if (choice == 1) {
                clientMenu();
            } else {
                createClient();
            }
        }
    }

    public void clientMenu() {
        int id = -1;
        int choice = 0;

        while (id == -1) {
            try {
                System.out.println("Entrez votre identifiant");
                id = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrez un nombre entier");
            }
        }

        Client client = service.findClient(id);

        if (client != null) {
            System.out.printf("Bienvenue cher(e) %s %s. Voulez-vous créer un nouveau compte ou effectuer des opérations sur l'un des vôtres ?\n"
                    , client.getFirstName(), client.getLastname());

            while (choice != 1 && choice != 2) {
                System.out.println("1 - Créer un nouveau compte\n" +
                        "2 - Effectuer des opérations sur un compte existant");
                try {
                    choice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Entrez un nombre");
                } finally {
                    scanner.nextLine();
                }

                if (choice == 1) {
                    if (service.createAccount(client) != null) {
                        System.out.println("Votre compte a bien été créé");
                    } else {
                        System.out.println("Votre compte n'a pas pu être créé");
                    }
                } else {
                    operationChoice(client);
                }
            }
        } else {
            System.out.println("Désolé, il semble que vous n'ayez pas de compte chez nous");
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

        menu();
    }

    public void displayAccount(Account account) {
        System.out.printf("Solde : %.2f\n", account.getBalance());
        for (int i = account.getOperations().size() - 1; i >= 0; i--) {
            Operation operation = account.getOperations().get(i);
            String operationValue = (operation.getStatus() == Status.DEPOSIT) ? "Dépôt" : "Retrait";
            System.out.printf("%d - %2.f€ - %s\n", operation.getId(), operation.getAmount(), operationValue);
        }

        menu();
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

        menu();
    }

    public void createClient() {
        String firstName;
        String lastName;

        System.out.println("Entrez votre prénom");
        firstName = scanner.nextLine();
        System.out.println("Entrez votre nom");
        lastName = scanner.nextLine();

        if (service.createClient(firstName, lastName) != null) {
            System.out.println("Votre compte a été créé avec succès");
        } else {
            System.out.println("Votre compte n'a pas pu être créé");
        }

        menu();
    }
}

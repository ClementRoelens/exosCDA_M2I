package org.example;

public class Main {
    public static void main(String[] args) {

        // Exo 1 (Chaise)

        System.out.println("\nExo 1\n");

        Chaise chaiseOne = new Chaise(4, "bleu", "bois");
        Chaise chaiseTwo = new Chaise(4, "blanche", "métal");
        Chaise chaiseThree = new Chaise(1, "transparente", "plexiglasse");
        System.out.println(chaiseOne);
        System.out.println(chaiseTwo);
        System.out.println(chaiseThree);

        // Exo 2 (héritage)

        System.out.println("\nExo 2\n");

        Person person = new Person();
        person.sayHello();
        Student student = new Student();
        student.setAge(15);
        student.goToClasses();
        student.displayAge();
        Teacher teacher = new Teacher();
        teacher.setAge(40);
        teacher.sayHello();
        teacher.explain();


        // Exo 3

        System.out.println("\nExo 3\n");

        HousePerson clementMaison = new HousePerson(
                "Clément",
                new Appartment(
                        new Door("blanche")
                ));
        clementMaison.display();
        HousePerson bernardArnault = new HousePerson(
                "Bernard",
                new House(5000,
                        new Door("multicolore"))
        );
        bernardArnault.display();

        // Exo 4

        System.out.println("\nExo 4\n");

        SimpleAccount clement = new SimpleAccount(200);
        SavingsAccount camille = new SavingsAccount(7);
        PayantAccount truc = new PayantAccount();
        clement.credit(300);
        camille.credit(300);
        truc.credit(300);

        System.out.println("Les 3 comptes commencent avec 300€");

        System.out.println("Clément donne 200€ à Camille");
        clement.debit(200);
        camille.credit(200);
        System.out.println("Camille donne 100€ à truc");
        camille.debit(100);
        truc.credit(100);
        System.out.printf("État des comptes : \n%s\n%s\n%s\n", clement, camille, truc);
        System.out.println("Clément essaie de donner 500€ à truc");
        clement.debit(500);
        System.out.println("Camille donne 300€ à Clément");
        camille.debit(300);
        clement.credit(300);;
        System.out.println("Clément essaie de donner 500€ à truc");
        clement.debit(500);
        truc.credit(500);
        System.out.printf("État des comptes : \n%s\n%s\n%s\n", clement, camille, truc);
        camille.interestsCalcul(2);
        System.out.println("Camille a des intérêts. Voici l'état de son compte après 2 ans sans y toucher");
        System.out.println(camille);
    }
}
package Controller;

import Entity.ToDo;
import Service.TodoService;

import java.util.Scanner;

public class HCI {
    Scanner scanner = new Scanner(System.in);
    TodoService service = new TodoService();

    public void menu() {
        int choice;

        System.out.println(
                "1 - Créer une tâche\n" +
                        "2 - Voir les tâches existantes\n" +
                        "3 - Finir une tâche\n" +
                        "4 - Modifier une tâche\n" +
                        "5 - Supprimer une tâche\n" +
                        "6 - Au revoir");
        choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice){
            case 1 -> createTodo();
            case 2 -> getTodos();
            case 3 -> todoDone();
            case 4 -> updateTodo();
            case 5 -> deleteTodo();
            case 6 -> end();
        }

        if (choice != 6){
            menu();
        }
    }

    private void createTodo() {
        String name;

        System.out.println("Entrez le nom de la tâche");
        name = scanner.nextLine();

        System.out.println("Votre tâche a pour id " + service.postTodo(name));
    }

    private void getTodos() {
        for (ToDo todo : service.getTodos()){
            System.out.println(todo);
        }
    }

    private void todoDone(){
        int id;

        getTodos();
        System.out.println("Entrez le numéro de la tâche finie");
        id = scanner.nextInt();
        scanner.nextLine();

        service.endTodo(id);
        System.out.println("Tâche finie");
    }

    private void updateTodo() {
        int id;
        String name;

        getTodos();
        System.out.println("Entrez le numéro de la tâche à modifier");
        id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Entrez le nouveau nom");
        name = scanner.nextLine();

        service.updateTodo(id, name);
        System.out.println("Tâche modifiée");
    }

    private void deleteTodo() {
        int id;

        getTodos();
        System.out.println("Entrez le numéro de la tâche à supprimer");
        id = scanner.nextInt();
        scanner.nextLine();

        service.removeTodo(id);
        System.out.println("Tâche supprimée");
    }

    private void end(){
        System.out.println("Au revoir");
    }
}

package Controller;

import Entity.ToDo;
import Service.TodoService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class HCI {
    Scanner scanner = new Scanner(System.in);
    TodoService service = new TodoService();

    private int scanInt(){
        int returnedValue = 0;
        while (returnedValue == 0){
            try {
                returnedValue = scanner.nextInt();
            } catch (InputMismatchException e){
                System.out.println("Entrez un nombre entier positif");
            } finally {
                scanner.nextLine();
            }
        }
        return returnedValue;
    }

    public void menu() {
        int choice;

        System.out.println(
                "1 - Créer une tâche\n" +
                        "2 - Voir les tâches existantes\n" +
                        "3 - Finir une tâche\n" +
                        "4 - Modifier une tâche\n" +
                        "5 - Supprimer une tâche\n" +
                        "6 - Au revoir");
        choice = scanInt();

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
        String name = "";

        while (name.isEmpty()){
            System.out.println("Entrez le nom de la tâche");
            name = scanner.nextLine();
        }
        ToDo newTodo = service.postTodo(name);

        if (newTodo != null){
            System.out.println("Votre tâche a pour id " + newTodo.getId());
        } else {
            System.out.println("Votre tâche n'a pas pu être créée");
        }
    }

    private void getTodos() {
        List<ToDo> todos = service.getTodos();

        if (!todos.isEmpty()){
            for (ToDo todo : todos){
                System.out.println(todo);
            }
        } else {
            System.out.println("Il n'y a aucune tâche d'enregistrée");
        }
    }

    private void todoDone(){
        int id;

        getTodos();
        System.out.println("Entrez le numéro de la tâche finie");
        id = scanInt();

        service.endTodo(id);
        System.out.println("Tâche finie");
    }

    private void updateTodo() {
        int id;
        String name = "";

        getTodos();

        System.out.println("Entrez le numéro de la tâche à modifier");
        id = scanInt();
        while (name.isEmpty()){
            System.out.println("Entrez le nouveau nom");
            name = scanner.nextLine();
        }

        service.updateTodo(id, name);
        System.out.println("Tâche modifiée");
    }

    private void deleteTodo() {
        int id;

        getTodos();
        System.out.println("Entrez le numéro de la tâche à supprimer");
        id = scanInt();

        service.removeTodo(id);
        System.out.println("Tâche supprimée");
    }

    private void end(){
        System.out.println("Au revoir");
    }
}

package Controller;

import Entity.ToDo;
import Entity.TodoInfos;
import Service.TodoService;

import java.sql.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class HCI {
    Scanner scanner = new Scanner(System.in);
    TodoService service = new TodoService();

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
    private int scanInt(String message, int defaultValue){
        int returnedValue = 0;

        System.out.println(message);

        try {
            returnedValue = scanner.nextInt();
        } catch (InputMismatchException e){
            returnedValue = defaultValue;
        } finally {
            scanner.nextLine();
        }

        return (returnedValue == 0 ? defaultValue : returnedValue);
    }

    private String scanString(String message){
        String returnedValue = "";
        while (returnedValue.isEmpty()){
            System.out.println(message);
            returnedValue = scanner.nextLine();
        }
        return returnedValue;
    }

    private String scanString(String message, String defaultValue){
        String returnedValue;

        System.out.println(message);
        returnedValue = scanner.nextLine();

        return (returnedValue.isEmpty() ? defaultValue : returnedValue);
    }

    public void menu() {
        int choice;

        choice = scanInt("1 - Créer une tâche\n" +
                "2 - Voir les tâches existantes\n" +
                "3 - Finir une tâche\n" +
                "4 - Modifier une tâche\n" +
                "5 - Supprimer une tâche\n" +
                "6 - Au revoir");

        switch (choice) {
            case 1 -> createTodo();
            case 2 -> getTodos();
            case 3 -> todoDone();
            case 4 -> updateTodo();
            case 5 -> deleteTodo();
            case 6 -> end();
        }

        if (choice != 6) {
            menu();
        }
    }

    private void createTodo() {
        String name;
        String description;
        String stringDeadline;
        Date deadline = null;
        int priority;

        name = scanString("Entrez le nom de la tâche");
        description = scanString("Entrez la description de la tâche");
        while (deadline == null){
            try {
                stringDeadline = scanString("Entrez la date d'échéance de la tâche (au format yyyy-mm-dd)");
                deadline = Date.valueOf(stringDeadline);
            } catch (IllegalArgumentException e){
                System.out.println("Mauvais format de date");
            }
        }
        priority = scanInt("Notez la priorité de cette tâche");

        ToDo newTodo = service.postTodo(name,description,deadline,priority);

        if (newTodo != null) {
            System.out.println("Votre tâche a pour id " + newTodo.getId());
        } else {
            System.out.println("Votre tâche n'a pas pu être créée");
        }
    }

    private void getTodos() {
        List<ToDo> todos = service.getTodos();

        if (!todos.isEmpty()) {
            for (ToDo todo : todos) {
                System.out.println(todo);
            }
        } else {
            System.out.println("Il n'y a aucune tâche d'enregistrée");
        }
    }

    private void todoDone() {
        int id;

        getTodos();
        id = scanInt("Entrez le numéro de la tâche finie");

        service.endTodo(id);
        System.out.println("Tâche finie");
    }

    private void updateTodo() {
        int id;
        String name;
        String description;
        String stringDeadline;
        Date deadline = null;
        int priority;
        boolean success;

        getTodos();
        id = scanInt("Entrez le numéro de la tâche à modifier");

        ToDo todo = service.getTodo(id);

        if (todo != null){
            TodoInfos infos = todo.getTodoInfos();
            name = scanString("Le nom actuel est : " + todo.getName()
                    + "\nEntrez un nouveau nom, ou rien pour le laisser tel quel\n", todo.getName());
            description = scanString("La description actuelle est : " + infos.getDescription()
                    + "\nEntrez la nouvelle description, ou rien pour la laisser telle quelle", infos.getDescription());
            while (deadline == null){
                try {
                    stringDeadline = scanString("La deadline actuelle est " + infos.getDeadline()
                            + "\nEntrez la nouvelle date d'échéance (au format yyyy-mm-dd)", infos.getDeadline().toString());
                    deadline = Date.valueOf(stringDeadline);
                } catch (IllegalArgumentException e){
                    System.out.println("Mauvais format de date");
                }
            }
            priority = scanInt("La priorité actuelle est " + infos.getPriority()
                    + "\nEntrez le nouveau niveau de priorité (ou 0 ou un autre caractère pour le laisser tel quel)", infos.getPriority());


            success = service.updateTodo(id, infos.getId() ,name, description, deadline, priority);

            if (success) {
                System.out.println("Tâche modifiée");
            } else {
                System.out.println("Tâche non-modifiée, quelque chose s'est mal passé");
            }
        } else {
                System.out.println("Cet id ne correspond à aucune tâche");
        }

    }

    private void deleteTodo() {
        int id;
        boolean success;

        getTodos();
        id = scanInt("Entrez le numéro de la tâche à supprimer");

        success = service.removeTodo(id);
        if (success) {
            System.out.println("Tâche supprimée");
        } else {
            System.out.println("Cet id ne correspond à aucune tâche");
        }
    }

    private void end() {
        System.out.println("Au revoir");
        service.closeAll();
    }
}

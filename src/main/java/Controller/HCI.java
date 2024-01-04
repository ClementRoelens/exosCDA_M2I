package Controller;

import Entity.ToDo;
import Entity.TodoInfos;
import Entity.User;
import Service.TodoService;
import Service.UserService;

import java.sql.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HCI {
    Scanner scanner = new Scanner(System.in);
    TodoService todoService = new TodoService();
    UserService userService = new UserService();

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

    public void menu() {
        int choice;

        choice = scanInt("1 - Créer une tâche\n" +
                "2 - Voir les tâches existantes\n" +
                "3 - Finir une tâche\n" +
                "4 - Modifier une tâche\n" +
                "5 - Supprimer une tâche\n" +
                "6 - Créer un utilisateur\n" +
                "7 - Supprimer un utilisateur (et toutes ses tâches !)\n" +
                "8 - Au revoir");

        switch (choice) {
            case 1 -> createTodo();
            case 2 -> getTodos();
            case 3 -> todoDone();
            case 4 -> updateTodo();
            case 5 -> deleteTodo();
            case 6 -> createUser();
            case 7 -> removeUser();
            case 8 -> end();
        }

        if (choice != 8) {
            menu();
        }
    }

    private void createTodo() {
        String name;
        String description;
        String stringDeadline;
        List<User> users;
        User user;
        Date deadline = null;
        int priority;
        int id;
        TodoInfos infos;
        ToDo newTodo;

        name = scanString("Entrez le nom de la tâche");
        description = scanString("Entrez la description de la tâche");
        while (deadline == null) {
            try {
                stringDeadline = scanString("Entrez la date d'échéance de la tâche (au format yyyy-mm-dd)");
                deadline = Date.valueOf(stringDeadline);
            } catch (IllegalArgumentException e) {
                System.out.println("Mauvais format de date");
            }
        }
        priority = scanInt("Notez la priorité de cette tâche");

        users = userService.getUsers();
        for (User tempUser : users) {
            System.out.println(tempUser);
        }
        id = scanInt("Entrez le numéro de l'utilisateur à qui la tâche sera attribuée");
        user = users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);

        if (user != null){
            infos = new TodoInfos(description, deadline, priority);

            newTodo = new ToDo(name, infos, user);
            newTodo = todoService.postTodo(newTodo);

            if (newTodo != null) {
                System.out.println("Votre tâche a pour id " + newTodo.getId());
            } else {
                System.out.println("Votre tâche n'a pas pu être créée");
            }
        } else {
            System.out.println("Cet id ne correspond à aucun user");
        }
    }

    private void getTodos() {
        List<ToDo> todos = todoService.getTodos();

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

        todoService.endTodo(id);
        System.out.println("Tâche finie");
    }

    private void updateTodo() {
        int todoId;
        ToDo todo;
        TodoInfos infos;
        String name;
        String description;
        String stringDeadline;
        Date deadline = null;
        int priority;
        boolean success;
        User user;
        List<User> users;
        int userId;

        getTodos();
        todoId = scanInt("Entrez le numéro de la tâche à modifier");

        todo = todoService.getTodo(todoId);

        if (todo != null) {
            infos = todo.getTodoInfos();
            name = scanString("Le nom actuel est : " + todo.getName()
                    + "\nEntrez un nouveau nom, ou rien pour le laisser tel quel\n", todo.getName());
            todo.setName(name);
            description = scanString("La description actuelle est : " + infos.getDescription()
                    + "\nEntrez la nouvelle description, ou rien pour la laisser telle quelle", infos.getDescription());
            infos.setDescription(description);
            while (deadline == null) {
                try {
                    stringDeadline = scanString("La deadline actuelle est " + infos.getDeadline()
                            + "\nEntrez la nouvelle date d'échéance (au format yyyy-mm-dd)", infos.getDeadline().toString());
                    deadline = Date.valueOf(stringDeadline);
                } catch (IllegalArgumentException e) {
                    System.out.println("Mauvais format de date");
                }
            }
            infos.setDeadline(deadline);
            priority = scanInt("La priorité actuelle est " + infos.getPriority()
                    + "\nEntrez le nouveau niveau de priorité (ou 0 ou un autre caractère pour le laisser tel quel)", infos.getPriority());
            infos.setPriority(priority);
            todo.setTodoInfos(infos);

            users = userService.getUsers();
            for (User tempuser : users){
                System.out.println(tempuser);
            }
            userId = scanInt("L'utilisateur en charge de cette tâche est le numéro " + todo.getUser().getId()
                    + "\nEntrez l'id du nouvel utilisateur en charge (ou 0 ou un autre caractère pour le laisser tel quel)", todo.getUser().getId());
            user = users.stream().filter(u -> u.getId() == userId).findFirst().orElse(null);
            if (user == null){
                user = todo.getUser();
            }
            todo.setUser(user);

            success = todoService.updateTodo(todo);

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

        success = todoService.removeTodo(id);
        if (success) {
            System.out.println("Tâche supprimée");
        } else {
            System.out.println("Cet id ne correspond à aucune tâche");
        }
    }

    private void createUser() {
        String name;
        User user;

        name = scanString("Entrez le nom de l'utilisateur");
        user = new User(name);
        user = userService.postUser(user);

        if (user != null) {
            System.out.println("User créé, l'id est " + user.getId());
        } else {
            System.out.println("User non créé, quelque chose s'est mal passé");
        }
    }

    private void removeUser(){
        int id;

        for (User user : userService.getUsers()){
            System.out.println(user);
        }
        id = scanInt("Entrez l'id de l'utilisateur à supprimer");

        if (userService.removeUser(id)){
            System.out.println("Utilisateur supprimé");
        } else {
            System.out.println("Utilisateur non-supprimé, l'id devait être mauvais");
        }
    }

    private void end() {
        System.out.println("Au revoir");
        todoService.closeAll();
    }
}

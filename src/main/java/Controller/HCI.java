package Controller;

import Entity.Category;
import Entity.ToDo;
import Entity.TodoInfos;
import Entity.User;
import Service.CategoryService;
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
    CategoryService categoryService = new CategoryService();



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

        choice = scanInt("\n" +
                "1 - Créer une tâche\n" +
                "2 - Voir les tâches existantes\n" +
                "3 - Finir une tâche\n" +
                "4 - Modifier une tâche\n" +
                "5 - Supprimer une tâche\n" +
                "6 - Créer un utilisateur\n" +
                "7 - Afficher toutes les tâches d'un utilisateur\n" +
                "8 - Supprimer un utilisateur (et toutes ses tâches !)\n" +
                "9 - Ajouter une catégorie\n" +
                "10 - Supprimer une catégorie\n" +
                "11 - Afficher les tâches d'une catégorie\n" +
                "12 - Ajouter une tâche à une catégorie\n" +
                "13 - Retirer une tâche d'une catégorie\n" +
                "14 - Au revoir");

        switch (choice) {
            case 1 -> createTodo();
            case 2 -> getTodos();
            case 3 -> todoDone();
            case 4 -> updateTodo();
            case 5 -> deleteTodo();
            case 6 -> createUser();
            case 7 -> showTasksFromOneUser();
            case 8 -> removeUser();
            case 9 -> addCategory();
            case 10 -> deleteCategory();
            case 11 -> showTasksFromOneCategory();
            case 12 -> addTaskToOneCategory();
            case 13 -> removeOneTaskFromOneCategory();
            case 14 -> end();
        }

        if (choice != 14) {
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
            System.out.println("Tâche non-supprimée, l'id est probablement mauvais");
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

    private void showTasksFromOneUser(){
        int id;
        User user;

        for (User tempUser : userService.getUsers()){
            System.out.println(tempUser);
        }
        id = scanInt("Entrez l'id de l'utilisateur dont vous voulez voir les tâches");

        user = userService.getUser(id);

        if (user != null){
            for (ToDo todo : user.getTodos()){
                System.out.println(todo.getName());
            }
        } else {
            System.out.println("Aucun user ne correrspond à cet id");
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

    private void addCategory(){
        String name;
        Category category;

        name = scanString("Entrez le nom de la nouvelle catégorie");

        category = new Category(name);
        category = categoryService.postCategory(category);

        if (category != null){
            System.out.println("Nouvelle catégorie bien créée, son id est " + category.getId());
        } else {
            System.out.println("Catégorie non créée, quelque chose s'est mal passé");
        }
    }

    private void deleteCategory(){
        int id;
        boolean success;

        for (Category category : categoryService.getCategories()){
            System.out.println(category);
        }
        id = scanInt("Entrez l'id de la catégorie que vous voulez supprimer");

        success = categoryService.deleteCategory(id);

        System.out.println(success ? "Catégorie supprimée" : "Catégorie non-supprimée, l'id doit être mauvais");
    }

    private void showTasksFromOneCategory(){
        int id;
        Category category;

        for (Category tempCategory : categoryService.getCategories()){
            System.out.println(tempCategory);
        }
        id = scanInt("Entrez l'id de la catégorie dont vous voulez voir les tâches");

        category = categoryService.getCategorie(id);
        if (category != null){
            if (!category.getTodos().isEmpty()) {
                System.out.println("Voici les tâches de la catégorie " + category.getName());
                for (ToDo todo : category.getTodos()) {
                    System.out.println(todo);
                }
            } else {
                System.out.println("Aucune catégorie n'est encore reliée à cette tâche");
            }
        } else {
            System.out.println("Aucune catégorie à cet id");
        }
    }

    private void addTaskToOneCategory(){
        int todoId;
        int categoryId;
        Category category;
        ToDo todo;
        boolean success;

        for (ToDo tempTodo : todoService.getTodos()){
            System.out.println(tempTodo);
        }
        todoId = scanInt("Entrez l'id de la tâche que vous voulez catégoriser");
        todo = todoService.getTodo(todoId);

        if (todo != null){
            for (Category tempCategory : categoryService.getCategories()){
                System.out.println(tempCategory);
            }
            categoryId = scanInt("Entrez l'id de la catégorie que vous voulez ajouter à la tâche");
            category = categoryService.getCategorie(categoryId);

            if (category != null){
                category.getTodos().add(todo);
                success = categoryService.updateCategory(category);
                System.out.println(success ? "Opération réussie" : "Opération échouée");
            } else {
                System.out.println("Aucune catégorie ne correspond à cet id");
            }
        } else {
            System.out.println("Aucune tâche ne correspond à cet id");
        }
    }

    private void removeOneTaskFromOneCategory(){
        int todoId;
        int categoryId;
        Category category;
        ToDo todo;
        boolean success;

        for (ToDo tempTodo : todoService.getTodos()){
            System.out.println(tempTodo);
        }
        todoId = scanInt("Entrez l'id de la tâche que vous voulez décatégoriser");
        todo = todoService.getTodo(todoId);

        if (todo != null){
            for (Category tempCategory : categoryService.getCategories()){
                System.out.println(tempCategory);
            }
            categoryId = scanInt("Entrez l'id de la catégorie à laquelle vous voulez retirer la tâche");
            category = categoryService.getCategorie(categoryId);

            if (category != null){
                category.getTodos().remove(todo);
                success = categoryService.updateCategory(category);
                System.out.println(success ? "Opération réussie" : "Opération échouée");
            } else {
                System.out.println("Aucune catégorie ne correspond à cet id");
            }
        } else {
            System.out.println("Aucune tâche ne correspond à cet id");
        }
    }

    private void end() {
        System.out.println("Au revoir");
        todoService.closeAll();
    }
}

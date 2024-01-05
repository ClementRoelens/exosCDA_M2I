package Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private boolean isDone;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="infos_id", referencedColumnName = "id_infos")
    private TodoInfos todoInfos;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany(mappedBy = "todos", cascade = CascadeType.REMOVE)
    private List<Category> categories;



    public ToDo() {
        this.categories = new ArrayList<>();
    }

    public ToDo(String name, TodoInfos todoInfos, User user) {
        this();
        this.name = name;
        this.todoInfos = todoInfos;
        this.user = user;
        this.isDone = false;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public TodoInfos getTodoInfos() {
        return todoInfos;
    }

    public void setTodoInfos(TodoInfos todoInfos) {
        this.todoInfos = todoInfos;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }






    @Override
    public String toString() {
        StringBuilder returnedValue = new StringBuilder("\nTâche numéro " + id + " - " + name + (isDone ? " - Finie\n" : " - Non-finie\n"
                + todoInfos + "\nCatégories : "));
        for (Category category : categories){
            returnedValue.append(category.getName() + " ");
        }
        returnedValue.append("\nà faire par \n" + user);

        return returnedValue.toString();
    }
}

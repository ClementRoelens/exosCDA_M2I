package Entity;

import javax.persistence.*;

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



    public ToDo() {
    }

    public ToDo(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Tâche numéro " + id + " - " + name + (isDone ? " - Finie" : " - Non-finie\n" + todoInfos);
    }
}

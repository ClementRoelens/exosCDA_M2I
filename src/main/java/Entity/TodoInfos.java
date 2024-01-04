package Entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class TodoInfos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_infos")
    private int id;
    private String description;
    private Date deadline;
    private int priority;
    @OneToOne(mappedBy = "todoInfos")
    private ToDo todo;



    public TodoInfos() {
    }

    public TodoInfos(String description, Date deadline, int priority) {
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public ToDo getTodo() {
        return todo;
    }

    public void setTodo(ToDo todo) {
        this.todo = todo;
    }


    @Override
    public String toString() {
        return String.format("Informations de la tâche %d : \n" +
                "- %s\n" +
                "- Deadline : %s\n" +
                "- Priorité : %d",
                todo.getId(), description, deadline, priority);
    }
}

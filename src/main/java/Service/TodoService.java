package Service;

import DAO.ToDoDAOImpl;
import Entity.ToDo;
import Entity.TodoInfos;

import java.sql.Date;
import java.util.List;

public class TodoService {
    private ToDoDAOImpl toDoDAO = new ToDoDAOImpl();

    public ToDo postTodo(String name, String description, Date deadline, int priority){
        ToDo toDo = new ToDo(name);
        TodoInfos infos = new TodoInfos();
        infos.setDescription(description);
        infos.setDeadline(deadline);
        infos.setPriority(priority);
        toDo.setTodoInfos(infos);

        return toDoDAO.create(toDo);
    }

    public List<ToDo> getTodos(){
        return toDoDAO.read();
    }

    public ToDo getTodo(int id){
        return toDoDAO.read(id);
    }

    public boolean endTodo(int id){
        ToDo todo = toDoDAO.read(id);
        todo.setDone(true);
        return toDoDAO.update(todo);
    }

    public boolean updateTodo(int id, int infosId, String name, String description, Date deadline, int priority){
        ToDo todo = toDoDAO.read(id);
        if (todo != null){
            TodoInfos infos = new TodoInfos();
            infos.setId(infosId);
            infos.setDescription(description);
            infos.setDeadline(deadline);
            infos.setPriority(priority);

            todo.setName(name);
            todo.setTodoInfos(infos);

            return toDoDAO.update(todo);
        } else {
            return false;
        }
    }

    public boolean removeTodo(int id){
        return toDoDAO.delete(id);
    }

    public void closeAll(){
        toDoDAO.closeFactory();
    }
}

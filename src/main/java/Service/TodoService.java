package Service;

import DAO.ToDoDAOImpl;
import DAO.UserDAOImpl;
import Entity.ToDo;
import Entity.TodoInfos;
import Entity.User;

import java.sql.Date;
import java.util.List;

public class TodoService {
    private ToDoDAOImpl toDoDAO = new ToDoDAOImpl();

    public ToDo postTodo(ToDo todo){
        return toDoDAO.create(todo);
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

    public boolean updateTodo(ToDo todo){
        return toDoDAO.update(todo);
    }

    public boolean removeTodo(int id){
        return toDoDAO.delete(id);
    }

    public void closeAll(){
        toDoDAO.closeFactory();
    }
}

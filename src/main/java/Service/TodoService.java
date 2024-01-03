package Service;

import DAO.ToDoDAOImpl;
import Entity.ToDo;

import java.util.List;

public class TodoService {
    private ToDoDAOImpl toDoDAO = new ToDoDAOImpl();

    public ToDo postTodo(String name){
        return toDoDAO.create(new ToDo(name));
    }

    public List<ToDo> getTodos(){
        return toDoDAO.read();
    }

    public boolean endTodo(int id){
        ToDo todo = toDoDAO.read(id);
        todo.setDone(true);
        return toDoDAO.update(todo);
    }

    public boolean updateTodo(int id, String name){
        ToDo todo = toDoDAO.read(id);
        todo.setName(name);
        return toDoDAO.update(todo);
    }

    public boolean removeTodo(int id){
        return toDoDAO.delete(id);
    }
}

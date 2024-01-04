package Service;

import DAO.UserDAOImpl;
import Entity.User;

import java.util.List;

public class UserService {
    private UserDAOImpl userDAO = new UserDAOImpl();

    public User postUser(User user){
        return userDAO.create(user);
    }

    public List<User> getUsers(){
        return userDAO.read();
    }

    public User getUser(int id){
        return userDAO.read(id);
    }

    public boolean removeUser(int id){
        return userDAO.delete(id);
    }
}

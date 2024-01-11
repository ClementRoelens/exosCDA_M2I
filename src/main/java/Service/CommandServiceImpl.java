package Service;

import DAO.CommandDAOImpl;
import DAO.CommentDAOImpl;
import DAO.ProductDAOImpl;
import Entities.Command;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class CommandServiceImpl implements IService<Command> {
    private static CommandServiceImpl instance = null;
    private static final Object lock = new Object();
    private CommandDAOImpl commandDAO;


    private CommandServiceImpl(){
        commandDAO = new CommandDAOImpl();
    }

    public static CommandServiceImpl getInstance(){
        if (instance == null){
            synchronized (lock){
                instance = new CommandServiceImpl();
            }
        }
        return instance;
    }



    @Override
    public Command post(Command command) {
        return commandDAO.create(command);
    }

    @Override
    public List<Command> getAll() {
        return commandDAO.read();
    }

    @Override
    public Command getOne(int id) {
        return commandDAO.read(id);
    }

    public List<Command> getTodayCommands(){
        return commandDAO.readCommandUponDay(Date.valueOf(LocalDate.now()));
    }

    @Override
    public boolean update(Command command) {
        return commandDAO.update(command);
    }

    @Override
    public boolean delete(int id) {
        Command command = commandDAO.read(id);
        if (command != null){
            return commandDAO.delete(command);
        }
        return false;
    }

    @Override
    public void close() {
        commandDAO.close();
    }
}

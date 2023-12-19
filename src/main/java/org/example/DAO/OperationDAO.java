package org.example.DAO;

import org.example.Models.Operation;
import org.example.Utils.ConnectionManager;

import java.sql.SQLException;
import java.util.List;

public class OperationDAO extends BaseDAO<Operation> {

    private static OperationDAO instance = null;
    private static final Object lock = new Object();


    private OperationDAO(){
        new ConnectionManager().testConnection();
    }



    public static OperationDAO getInstance(){
        if (instance == null){
            synchronized (lock){
                instance = new OperationDAO();
            }
        }
        return instance;
    }

    @Override
    public Operation create(Operation object) throws SQLException {
        return null;
    }

    @Override
    public Operation read(int id) throws SQLException {
        return null;
    }
}

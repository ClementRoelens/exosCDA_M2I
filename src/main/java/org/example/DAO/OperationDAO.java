package org.example.DAO;

import org.example.Models.Account;
import org.example.Models.Operation;
import org.example.Models.Status;
import org.example.Utils.ConnectionManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OperationDAO extends BaseDAO<Operation> {

    private static OperationDAO instance = null;
    private static final Object lock = new Object();


    private OperationDAO() {
        new ConnectionManager().testConnection();
    }


    public static OperationDAO getInstance() {
        if (instance == null) {
            synchronized (lock) {
                instance = new OperationDAO();
            }
        }
        return instance;
    }

    @Override
    public Operation create(Operation operation) throws SQLException {
        _connection = ConnectionManager.getConnection();
        String stringifiedStatus = operation.getStatus() == Status.DEPOSIT ? "Dépôt" : "Retrait";

        query = "INSERT INTO operation (amount, status, account_id)" +
                "VALUES (? , ? , ?)";
        preparedStatement = _connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setDouble(1, operation.getAmount());
        preparedStatement.setString(2, stringifiedStatus);
        preparedStatement.setInt(3, operation.getAccountId());

        int nbRows = preparedStatement.executeUpdate();
        if (nbRows ==0){
            _connection.close();
            return null;
        }

        resultSet = preparedStatement.getGeneratedKeys();
        while (resultSet.next()){
            operation.setId(resultSet.getInt(1));
        }

        _connection.close();
        return operation;
    }

    @Override
    public Operation read(int id) throws SQLException {
        _connection = ConnectionManager.getConnection();
        Operation operation = null;

        query = "SELECT * " +
                "FROM operation " +
                "WHERE id = " + id;
        statement = _connection.createStatement();
        resultSet = statement.executeQuery(query);

        while (resultSet.next()){
            operation = new Operation(
                    resultSet.getInt("id"),
                    resultSet.getDouble("amount"),
                    resultSet.getString("status") == "Dépôt" ?
                            Status.DEPOSIT : Status.WITHDRAWAL,
                    resultSet.getInt("account_id")
                    );
        }

        _connection.close();
        return operation;
    }

    public List<Operation> readFromAccount(Account account) throws SQLException {
        _connection = ConnectionManager.getConnection();
        List<Operation> operations = new ArrayList<>();

        query = "SELECT * " +
                "FROM operation " +
                "WHERE account_id = " + account.getId();
        statement = _connection.createStatement();
        resultSet = statement.executeQuery(query);

        while (resultSet.next()){
            operations.add(new Operation(
                    resultSet.getInt("id"),
                    resultSet.getDouble("amount"),
                    resultSet.getString("status").equals("Dépôt") ?
                            Status.DEPOSIT : Status.WITHDRAWAL
            ));
        }

        _connection.close();
        return operations;
    }
}

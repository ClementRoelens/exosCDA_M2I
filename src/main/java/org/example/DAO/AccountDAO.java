package org.example.DAO;

import org.example.Models.Account;
import org.example.Models.Client;
import org.example.Utils.ConnectionManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO extends BaseDAO<Account>{
    private static AccountDAO instance = null;
    private static final Object lock = new Object();


    private AccountDAO(){
        new ConnectionManager().testConnection();
    }



    public static AccountDAO getInstance(){
        if (instance == null){
            synchronized (lock){
                instance = new AccountDAO();
            }
        }
        return instance;
    }


    @Override
    public Account create(Account account) throws SQLException {
        _connection = ConnectionManager.getConnection();

        query = "INSERT INTO account (client_id, balance) " +
                " VALUES (? , ? )";
        preparedStatement = _connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1,account.getClientId());
        preparedStatement.setDouble(2,account.getBalance());

        int nbRows = preparedStatement.executeUpdate();
        if (nbRows == 0){
            _connection.close();
            return null;
        }

        resultSet = preparedStatement.getGeneratedKeys();
        while (resultSet.next()){
            account.setId(resultSet.getInt(1));
        }

        _connection.close();
        return account;
    }

    @Override
    public Account read(int id) throws SQLException {
        _connection = ConnectionManager.getConnection();
        Account account = null;

        query = "SELECT * " +
                "FROM account " +
                "WHERE id = " + id;
        statement = _connection.createStatement();

        resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            account = new Account(
                    resultSet.getInt("id"),
                    resultSet.getInt("client_id"),
                    resultSet.getDouble("balance")
            );
        }

        _connection.close();
        return account;
    }

    public List<Account> readFromClient(Client client)throws SQLException {
        _connection = ConnectionManager.getConnection();
        List<Account> accounts = new ArrayList<>();

        query = "SELECT * " +
                "FROM account " +
                "WHERE client_id = " + client.getId();
        statement = _connection.createStatement();

        resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            accounts.add(new Account(
                    resultSet.getInt("id"),
                    client,
                    resultSet.getDouble("balance")
            ));
        }

        _connection.close();
        return accounts;
    }
    public boolean update(Account updatedAccount) throws SQLException {
        _connection = ConnectionManager.getConnection();

        query = "UPDATE account " +
                "SET balance = " + updatedAccount.getBalance() +
                " WHERE id = " + updatedAccount.getId();
        statement = _connection.createStatement();

        int nbRows = statement.executeUpdate(query);
        if ((nbRows == 0)){
            _connection.close();
            return false;
        }

        return true;
    }
}

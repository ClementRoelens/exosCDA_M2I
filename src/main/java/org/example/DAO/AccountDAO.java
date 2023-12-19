package org.example.DAO;

import org.example.Models.Account;
import org.example.Utils.ConnectionManager;

import java.sql.SQLException;
import java.sql.Statement;
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
        preparedStatement.setInt(1,account.getClient().getId());
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
        return null;
    }
    public Account update(Account updatedAccount) throws SQLException {
        return null;
    }
}

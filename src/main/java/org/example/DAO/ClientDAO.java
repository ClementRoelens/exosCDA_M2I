package org.example.DAO;

import org.example.Models.Account;
import org.example.Models.Client;
import org.example.Utils.ConnectionManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO extends BaseDAO<Client> {
    private static ClientDAO instance = null;
    private static final Object lock = new Object();


    private ClientDAO() {
        ConnectionManager.testConnection();
    }


    public static ClientDAO getInstance() {
        if (instance == null) {
            synchronized (lock) {
                instance = new ClientDAO();
            }
        }
        return instance;
    }


    public Client create(Client client) throws SQLException {
        this._connection = ConnectionManager.getConnection();

        query = "INSERT INTO client (first_name, last_name) VALUES (?,?)";
        preparedStatement = _connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, client.getFirstName());
        preparedStatement.setString(2, client.getLastname());

        int nbRows = preparedStatement.executeUpdate();
        if (nbRows == 0) {
            _connection.close();
            return null;
        }

        resultSet = preparedStatement.getGeneratedKeys();
        while (resultSet.next()) {
            client.setId(resultSet.getInt(1));
        }

        _connection.close();
        return client;
    }


    public Client read(int id) throws SQLException {
        _connection = ConnectionManager.getConnection();
        Client client = null;

        query = "SELECT * " +
                "FROM client " +
                "WHERE id = " + id;
        statement = _connection.createStatement();

        resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            client = new Client(
                    resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    new ArrayList<>()
            );
        }

        _connection.close();
        return client;
    }

}

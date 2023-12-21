package org.example.DAO;

import org.example.Utils.ConnectionManager;

import java.sql.*;
import java.util.List;

public abstract class BaseDAO<T> {
    protected Connection connection;
    protected Statement statement;
    protected PreparedStatement preparedStatement;
    protected String query;
    protected ResultSet resultSet;
    protected String tableName;


    protected abstract T createObjectFromResultSet(ResultSet resultSet) throws SQLException;
    protected abstract void preparedStatementWithObject(PreparedStatement preparedStatement, T object) throws SQLException;

    public abstract List<T> read() throws SQLException;
    public abstract T read(int id) throws SQLException;
    public abstract T create(T object) throws SQLException;
    public abstract boolean update(T object) throws SQLException;

    public boolean delete(int id) throws SQLException{
        query = String.format("DELETE FROM %s " +
                "WHERE id = ?", tableName);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);

        int nbRows = preparedStatement.executeUpdate();

        preparedStatement.close();
        return nbRows != 0;
    }


    public BaseDAO() throws SQLException {
        connection = ConnectionManager.getConnection();
    }
}

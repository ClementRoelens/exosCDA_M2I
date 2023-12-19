package org.example.DAO;

import java.sql.*;
import java.util.List;

public abstract class BaseDAO<T> {

    protected Connection _connection;
    protected String query;
    protected Statement statement;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;


    public abstract T create(T object) throws SQLException;
    public abstract T read(int id) throws SQLException;

}

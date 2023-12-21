package org.example.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ConnectionManager {
    private static Connection connection;
    private static final Object lock = new Object();
    private static final String url = "jdbc:mysql://localhost:3306/exo_banque";
    private static final String user = "root";
    private static final String password = "1234";


    private ConnectionManager(){}

    public static Connection getConnection() throws SQLException {
        if (connection == null){
            synchronized (lock){
                connection = DriverManager.getConnection(url,user,password);
            }
        }
        return connection;
    }

    public static void closeConnection() throws SQLException{
        connection.close();
    }
}

package org.example.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

public class ConnectionManager {
    private static final String url = "jdbc:mysql://localhost:3306/exo_banque";
    private static final String user = "root";
    private static final String password = "1234";


    public static boolean testConnection() {
        try {
            DriverManager.getConnection(url,user,password);
            return true;
        } catch (SQLException e){
            System.out.println(e);
            return false;
        }
    }

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url,user,password);
    }
}

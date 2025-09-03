package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String CONNECTION_URL = "jdbc:sqlserver://localhost:1433;databaseName=LapTrinhWeb;user=sa;password=nhacc123@;encrypt=true;trustServerCertificate=true;";
    private static final String DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    private DBConnection() {}

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER_CLASS);
            
            return DriverManager.getConnection(CONNECTION_URL);
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
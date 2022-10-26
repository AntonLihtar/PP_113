package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util throws SQLException{
   
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db113";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "anton";


    public static Connection getConnection() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            System.out.println("Connection OK");
            return connection;
        }
    }
}

package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {

    public static Connection connection;
    public static PreparedStatement pst;


    public Connection getConnection() {
        try {
            // Load the JDBC Driver
            Class.forName("org.postgresql.Driver");

            // Set properties for the login
            Properties props = new Properties();
            props.setProperty("user", "wayne");
            props.setProperty("password", "admin");

            // set the JDBC url
            String dburl = "jdbc:postgresql://localhost:5432/mellanbytest";

            connection = DriverManager.getConnection(dburl, props);

            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Finally Executed");
        }

        return connection;

    }

}

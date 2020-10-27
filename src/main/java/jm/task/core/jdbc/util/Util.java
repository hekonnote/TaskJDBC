package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/task1_1_3";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "sososo";

    private static Connection connection = createConnection();

    private static Connection createConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection() {
        try {
            if (getConnection() != null) {
                getConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




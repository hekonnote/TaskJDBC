package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соединения с БД

    private static final String URL = "jdbc:mysql://localhost:3306/task1_1_3";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "sososo";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}

// dbConnection = getDBConnection();
// statement = dbConnection.createStatement();

/*
public List<User> getUser(int userId) {
    try (Connection con = DriverManager.getConnection(myConnectionURL);
         PreparedStatement ps = createPreparedStatement(con, userId);
         ResultSet rs = ps.executeQuery()) {

         // process the resultset here, all resources will be cleaned up

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

private PreparedStatement createPreparedStatement(Connection con, int userId) throws SQLException {
    String sql = "SELECT id, username FROM users WHERE id = ?";
    PreparedStatement ps = con.prepareStatement(sql);
    ps.setInt(1, userId);
    return ps;
}
 */



package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private PreparedStatement preparedStatement2;

    {
        try {
            connection = Util.getConnection();
            if (connection != null) {
                System.out.println("Connected");
            }
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(
                    "INSERT user (name, lastName, age) VALUES (?, ?, ?)");
            preparedStatement2 = connection.prepareStatement(
                    "DELETE FROM user WHERE id = ?");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS user" +
                "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                " name VARCHAR(50) NOT NULL, " +
                " lastName VARCHAR (50) NOT NULL, " +
                " age TINYINT NOT NULL, " +
                " PRIMARY KEY (id))";
        try {
            getStatement().executeUpdate(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void dropUsersTable() {
        String dropQuery = "DROP TABLE IF EXISTS user";
        try {
            getStatement().executeUpdate(dropQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            getPreparedStatement().setString(1, name);
            getPreparedStatement().setString(2, lastName);
            getPreparedStatement().setByte(3, age);
            getPreparedStatement().executeUpdate();
            printNameSaved(getPreparedStatement());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printNameSaved(Statement statement) {
        try {
            ResultSet result = getStatement().executeQuery(
                    "SELECT name FROM user WHERE id = LAST_INSERT_ID()");
            while (result.next()) {
                System.out.println("User с именем – " + result.getString("name") + " добавлен в базу данных.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            getPreparedStatement2().setLong(1, id);
            getPreparedStatement2().executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userslist = new ArrayList<>();
        try {
            ResultSet result = getStatement().executeQuery(
                    "SELECT * FROM user");

            while (result.next()) {
                userslist.add(new User(
                        result.getString("name"),
                        result.getString("lastName"),
                        result.getByte("age")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userslist;
    }

    public void cleanUsersTable() {
        String cleanTableQuery = "TRUNCATE TABLE user";
        try {
            getStatement().executeUpdate(cleanTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public PreparedStatement getPreparedStatement2() {
        return preparedStatement2;
    }
}

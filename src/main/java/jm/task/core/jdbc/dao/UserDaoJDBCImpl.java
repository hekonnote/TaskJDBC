package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static Connection connection;

    public UserDaoJDBCImpl() {
    }

    static {
        connection = Util.getConnection();
        if (connection != null) {
            System.out.println("Connected");
        } else
            System.out.println("Disconnected");
    }

    public void createUsersTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS user" +
                "(id BIGINT NOT NULL AUTO_INCREMENT, " +
                " name VARCHAR(50) NOT NULL, " +
                " lastName VARCHAR (50) NOT NULL, " +
                " age TINYINT NOT NULL, " +
                " PRIMARY KEY (id))";
        try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String dropQuery = "DROP TABLE IF EXISTS user";
        try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(dropQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(
                "INSERT user (name, lastName, age) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            printUserSaved(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printUserSaved(Statement preparedStatement) {
        try {
            ResultSet result = preparedStatement.executeQuery(
                    "SELECT name FROM user WHERE id = LAST_INSERT_ID()");
            while (result.next()) {
                System.out.println(
                        "User с именем – " + result.getString("name") + " добавлен в базу данных.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(
                "DELETE FROM user WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userslist = new ArrayList<>();
        try (Statement statement = getConnection().createStatement()) {
            ResultSet result = statement.executeQuery(
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
        try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(cleanTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}

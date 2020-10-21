package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Pit1", "ddddd", (byte) 30);
        userService.saveUser("Pit2", "ccccc", (byte) 35);
        userService.saveUser("Pit3", "eeeee", (byte) 40);
        userService.saveUser("Pit4", "hhhhh", (byte) 45);
        System.out.println(userService.getAllUsers().toString());
        userService.cleanUsersTable();
        userService.dropUsersTable();
        try {
            userService.getUserDaoJDBC().getConnection().close();
            userService.getUserDaoJDBC().getStatement().close();
            userService.getUserDaoJDBC().getPreparedStatement().close();
            userService.getUserDaoJDBC().getPreparedStatement2().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


//        try (Connection connection = Util.getConnection();
//             Statement statement = connection.createStatement();
//             PreparedStatement preparedStatement = connection.prepareStatement(
//                     "INSERT users (name, lastName, age) VALUES (?, ?, ?)");
//             PreparedStatement preparedStatement2 = connection.prepareStatement(
//                     "DELETE FROM users WHERE id = ?")) {
//            UserServiceImpl userService = new UserServiceImpl();
//            userService.createUsersTable();
//            userService.saveUser("Pit1", "ddddd", (byte) 30);
//            userService.saveUser("Pit2", "ccccc", (byte) 35);
//            userService.saveUser("Pit3", "eeeee", (byte) 40);
//            userService.saveUser("Pit4", "hhhhh", (byte) 45);
//            System.out.println(userService.getAllUsers().toString());
//            userService.cleanUsersTable();
//            userService.dropUsersTable();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}

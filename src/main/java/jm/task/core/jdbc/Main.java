package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Pit1", "ddddd", (byte) 30);
        userService.saveUser("Pit2", "ccccc", (byte) 35);
        userService.saveUser("Pit3", "eeeee", (byte) 40);
        userService.saveUser("Pit4", "hhhhh", (byte) 45);
        System.out.println(userService.getAllUsers().toString());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

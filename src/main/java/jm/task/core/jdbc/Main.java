package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Pit1", "ddddd", (byte) 30);
        userService.saveUser("Pit2", "ccccc", (byte) 35);
        userService.saveUser("Pit3", "eeeee", (byte) 40);
        userService.saveUser("Pit4", "hhhhh", (byte) 45);
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }
//        userService.getAllUsers().forEach(System.out::println);// не работает, спросить почему у Семена
//        Error:(19, 55) java: method references are not supported in -source 7
//        (use -source 8 or higher to enable method references)
        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.closeConnection();
    }
}

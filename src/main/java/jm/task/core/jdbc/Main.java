package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        String[] name = {"Ivan", "Petr", "Sergei", "Bob"};
        String[] lastName = {"Ivanov", "Petrov", "Sergeev", "Bobov"};
        byte[] age = {15, 18, 25, 20};

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        for (int i = 0; i < 4; i++) {
            userService.saveUser(name[i], lastName[i], age[i]);
            System.out.printf("User с именем – %s добавлен в базу данных\n", name[i]);
        }
        System.out.println(userService.getAllUsers().toString());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

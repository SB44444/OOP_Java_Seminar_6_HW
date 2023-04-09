package notebook.view;

import notebook.view.mapper.C;
import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;

import java.util.List;
import java.util.Scanner;

public class UserView {
    private final UserController<C> userController;

    public UserView(UserController<C> userController) {
        this.userController = userController;
    }

    public void run() {
        Commands com;

        while (true) {
            String command = prompt("Введите команду: NONE, READ, CREATE, LIST, GET, UPDATE, DELETE, EXIT\n:  ");
            com = Commands.valueOf(command.toUpperCase());
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    try {
                        User person = createUser();
                        userController.saveUser(person);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                case NONE:
                    break;
                case READ:
                    String userId = prompt("Enter user id: ");
                    try {
                        User user = userController.readUser(Long.parseLong(userId));
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case LIST:
                    List<User> users = userController.getAllUsers();
                    for (User user : users) {
                        System.out.println(user);
                    }
                    break;
                case UPDATE:
                    String id = prompt("Enter user id: ");
                    userController.updateUser(id, createUser());
                    break;
                case DELETE:
                    userId = prompt("Enter user id: ");
                    userController.deleteUser(Long.parseLong(userId));
                    break;

                default:
                    System.out.println("Ошибка ввода");
            }
        }
    }
    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
    private User createUser() {
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        return new User(firstName + " ", lastName + " ", phone);
    }
}

package View.Impl;

import Model.Admin;
import Model.User;
import Model.UserManager;
import Model.UserRole;
import View.Menu;

import java.util.Scanner;

public class LoginMenu implements Menu {

    private final String[] items = {"1.Login", "2.Register"};
    private Scanner sc;

    @Override
    public void show() {

        while (true)
            startMenu();

    }

    @Override
    public void exit() {
        System.exit(0);
    }

    private void loginSubMenu(Scanner scanner) {

        User loggedInUser = login();
        if (loggedInUser == null) {
            System.out.println("Користувач з такою електронною поштою не знайдений або пароль невірний. " +
                    "Ви хочете зареєструватися. 1- так, 0 - ні");
            int regChoice = sc.nextInt();
            if (regChoice == 1) {
                User newUser = registerClient();
                if (newUser != null)
                    System.out.println("Користувач " + newUser.getUserFullName() + " зареєстрований !");
                else {
                    System.out.println("Виникла помилка");
                    show();
                }

            } else
                show();
        } else {
            if (loggedInUser.getRole() == UserRole.ADMIN)
                new AdminMenu((Admin) loggedInUser).show();
            else
                new ClientMenu(loggedInUser).show();

        }
    }

    private void startMenu() {
        Scanner sc = new Scanner(System.in);
        User loggedInUser = null;
        System.out.println("1. Логін користувач/адміністратор");
        System.out.println("2. Зареєструватися як користувач ");
        System.out.println("3. Вихід");
        int userChoice = sc.nextInt();

        switch (userChoice) {
            case 1:
                loggedInUser = login();
                if (loggedInUser == null) {
                    System.out.println("Користувач з такою електронною поштою не знайдений або пароль невірний. " +
                            "Ви хочете зареєструватися. 1- так, 0 - ні");
                    int regChoice = sc.nextInt();
                    if (regChoice == 1) {
                        User newUser = registerClient();
                        if (newUser != null) {
                            System.out.println("Користувач " + newUser.getUserFullName() + " зареістрований !");
                            startMenu();
                        } else {
                            System.out.println("Виникла помилка");
                            startMenu();
                        }

                    } else
                        startMenu();
                } else {
                    if (loggedInUser.getRole() == UserRole.ADMIN)
                        new AdminMenu((Admin) loggedInUser).show();
                    else
                        new ClientMenu(loggedInUser).show();
                }
                break;
            case 2:
                User newUser = registerClient();
                if (newUser != null) {
                    System.out.println("Користувач " + newUser.getUserFullName() + " зареістрований !");
                    startMenu();
                } else {
                    System.out.println("Виникла помилка");
                    startMenu();
                }
            case 3:
                exit();
            default:
                System.out.println("Невірний пункт меню!");
                startMenu();

        }
    }

    private User login() {
        String userLogin, passwd = "";
        boolean isLoggedIn = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Введення електронної пошти користувача: ");
        userLogin = sc.nextLine();
        System.out.println("Введення паролю користувача: ");
        passwd = sc.nextLine();
        User foundUser = UserManager.findUserByEmail(userLogin);

        if (foundUser != null) {
            isLoggedIn = foundUser.login(userLogin, passwd);
        }
        if (isLoggedIn) {
            return foundUser;
        }
        return null;

    }

    private User registerClient() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Будь-ласка,введіть ваше ім'я: ");
        String firstName = sc.nextLine();
        System.out.println("Будь-ласка,введіть ваше прізвище: ");
        String lastName = sc.nextLine();
        System.out.println("Будь-ласка,введіть вашу електронну пошту: ");
        String email = sc.nextLine();
        System.out.println("Будь-ласка,введіть ваш пароль: ");
        String password = sc.nextLine();
        System.out.println("Будь-ласка,введіть ваш бюджет: ");
        double budget = sc.nextDouble();
        return UserManager.createClient(firstName, lastName, email, password, budget);
    }

    private void registerSubMenu(Scanner scanner) {
        show();
    }
}
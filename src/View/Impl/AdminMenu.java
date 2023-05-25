package View.Impl;

import Model.Admin;
import Model.UserManager;
import View.Menu;

import java.util.Scanner;

public class AdminMenu implements Menu {

    Admin loggedInAdmin = null;

    AdminMenu(Admin loggedInAdmin){
        this.loggedInAdmin = loggedInAdmin;
    }

    public  void  show() {
        System.out.println("1. Меню користувача");
        System.out.println("2. Меню замовлень");
        System.out.println("3. Меню продуктів");
        System.out.println("4. Вихід");
        System.out.println("5. Назад");
        Scanner sc = new Scanner(System.in);
        int userChoice = sc.nextInt();
        switch (userChoice){
            case 1:
                performUserOperations();
                break;
            case 2:
                new OrdersMenu(this, (Admin)loggedInAdmin).show();
                break;
            case 3:
                performProductsOperations();
                show();
                break;
            case 4:
                exit();
                break;
            case 5:
                loggedInAdmin.logout();
                new LoginMenu().show();
            default:
                System.out.println("Обрана недійсна операція");
                show();
        }
    }

    @Override
    public void exit() {
        new LoginMenu().show();
    }

    private void performUserOperations(){
        Scanner sc = new Scanner(System.in);
        int userIdChoice = 0;
        System.out.println("Оберіть операцію:");
        System.out.println("1. Блокування користувача");
        System.out.println("2. Розблокувати користувача");
        System.out.println("3.Повернутись до меню адміністратора");
        System.out.println("4. Вийти");
        int operationChoice = sc.nextInt();
        switch (operationChoice){
            case 1:
            case 2:
                UserManager.displayAllUsers();
                System.out.println("Вибір користувача для цієї операції"+ operationChoice);
                int userChoice = sc.nextInt();
                if(operationChoice == 1) {
                    if (((Admin)loggedInAdmin).lockUser(userChoice))
                        System.out.println("Ви успішно заблокували користувача з №: " + userChoice);
                }
                if (operationChoice == 2) {
                    if (((Admin)loggedInAdmin).unlockUser(userChoice))
                        System.out.println("Ви успішно розблокували користувача з №: " + userChoice);
                }
                performUserOperations();
                break;
            case 3:
                show();
                break;
            case 4:
                exit();
                break;
            default:
                System.out.println("Невірний пункт меню");
                show();
        }
    }

    private void performOrdersOperations(){
        new OrdersMenu(this, (Admin)loggedInAdmin).show();
    }

    private void performProductsOperations(){
        new ProductChangeMenu(this, loggedInAdmin).show();
    }
}
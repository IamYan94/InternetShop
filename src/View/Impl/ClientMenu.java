package View.Impl;
import Model.*;
import View.Menu;

import java.util.Scanner;


public class ClientMenu implements Menu {

    User loggedInClient = null;

    ClientMenu(User loggedInClient) {
        this.loggedInClient = loggedInClient;
    }

    public void show() {
        Scanner sc = new Scanner(System.in);
        int userIdChoice = 0;
        System.out.println("Оберіть операцію:");
        System.out.println("1. Показати мої дані");
        System.out.println("2. Доступна продукція");
        System.out.println("3. Пошук товару");
        System.out.println("4. Мої замовлення");
        System.out.println("5. Вихід");
        int operationChoice = sc.nextInt();
        switch (operationChoice) {
            case 1:
                System.out.println(loggedInClient);
                show();
                break;
            case 2:
                ProductWarehouse.displayAllProds();
                System.out.println("Бажаєте оформити замовлення або повернутися в меню клієнта? " +
                        "1- оформити замовлення, будь-яка інша цифра - повернутися в меню клієнта");
                int orderChoice = sc.nextInt();
                if (orderChoice == 1) {
                    Order newOrder = ((Client) loggedInClient).createUserOrder();
                    OrderManagement.displayOrderDetails(newOrder);
                    show();
                } else
                    show();
                break;
            case 3:
                new ProductSearchMenu(this).show();
                break;
            case 4:
                OrderManagement.getOrdersByUser(loggedInClient);
                show();
                break;
            case 5:
                loggedInClient.logout();
                exit();
                break;
            default:
                System.out.println("Невірний пункт меню");
                show();
        }
    }

    @Override
    public void exit() {
        new LoginMenu().show();
    }

}

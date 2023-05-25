package View.Impl;
import Model.Admin;
import Model.OrderManagement;
import View.Menu;

import java.util.Scanner;

public class OrdersMenu implements Menu {

    AdminMenu admMenu = null;
    Admin adm = null;

    OrdersMenu(AdminMenu admMenu, Admin adm) {
        this.admMenu = admMenu;
        this.adm = adm;
    }

    @Override
    public void show() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Відображення всіх замовлень");
        System.out.println("2. Підтвердити замовлення");
        System.out.println("3. Скасувати замовлення");
        System.out.println("4. Повернутися до меню адміністратора");
        int admChoice = sc.nextInt();
        int orderId = 0;
        switch (admChoice) {

            case 1:
                OrderManagement.displayOrders();
                show();
                break;
            case 2:
                System.out.println("Введіть ідентифікатор замовлення для підтвердження:");
                orderId = sc.nextInt();
                adm.confirmOrder(orderId);
                show();
                break;
            case 3:
                System.out.println("Введіть ідентифікатор замовлення, щоб скасувати підтвердження:");
                orderId = sc.nextInt();
                adm.unconfirmOrder(orderId);
                show();
                break;
            case 4:
                exit();
                break;
            default:
                System.out.println("Будь ласка, оберіть вірний пункт!");
                show();
        }
    }

    @Override
    public void exit() {
        admMenu.show();
    }
}
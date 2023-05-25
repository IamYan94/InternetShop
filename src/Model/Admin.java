package Model;
import Service.UserLock;

import java.util.Scanner;

public class Admin extends User implements UserLock {

    Admin(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password, UserRole.ADMIN);
    }

    @Override
    public boolean lockUser(int userId) {
        return UserManager.lockUser(this, userId);

    }

    @Override
    public boolean unlockUser(int userId) {
        return UserManager.unlockUser(this, userId);

    }

    public void confirmOrder(int orderId) {
        Order orderToConfirm = OrderManagement.getOrder(orderId);
        if (orderToConfirm == null) {
            System.out.println("Замовлення з № : " + orderId + " данний товар не існує!");
            return;
        }
        if (orderToConfirm.getStatus().equals(OrderStatus.CREATED)) {
            orderToConfirm.changeStatus(OrderStatus.CONFIRMED);
            System.out.println("Статус замовлення з № :" + orderToConfirm.getOrderId() + " Змінено на: "
                    + orderToConfirm.getStatus());
        }
    }

    public void unconfirmOrder(int orderId) {
        Order orderToConfirm = OrderManagement.getOrder(orderId);
        if (orderToConfirm == null) {
            System.out.println("Замовлення №: " + orderId + " Не існує!");
            return;
        }
        if (orderToConfirm.getStatus().equals(OrderStatus.CONFIRMED)) {
            orderToConfirm.changeStatus(OrderStatus.DECLINED);
            System.out.println("Статус замовлення № :" + orderToConfirm.getOrderId() + " змінено на : "
                    + orderToConfirm.getStatus());
        }
    }

    public boolean addNewProduct() {
        Scanner scText = new Scanner(System.in);
        Scanner scNumbers = new Scanner(System.in);
        System.out.println("Введіть назву продукту :");
        String prodName = scText.nextLine();
        System.out.println("Введіть продуктовий бренд :");
        String prodBrand = scText.nextLine();
        System.out.println("Ввудіть категорію товару :");
        String prodCat = scText.nextLine();
        System.out.println("Введіть ціну товару :");
        double price = scNumbers.nextDouble();
        System.out.println("Введіть доступну кількість товару :");
        int qty = scNumbers.nextInt();
        Product newProd = new Product(prodBrand, prodCat, prodName, price, qty);
        return ProductWarehouse.addProduct(newProd);
    }
}
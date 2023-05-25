package Model;
import Service.OrderService;

import java.util.ArrayList;
import java.util.Scanner;

public class Client extends User implements OrderService {

    private ArrayList<Order> orders;
    private double budget;

    Client(String firstName, String lastName, String email, String password, double budget) {
        super(firstName, lastName, email, password, UserRole.CLIENT);
        this.budget = budget;
        orders = new ArrayList<>();
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public double getCurrentBudget() {
        return budget;
    }

    public void decreaseBudget(double amount) {
        if (amount <= budget) {
            budget -= amount;
        }
    }

    public void increaseBudget(double amount) {
        budget += amount;
    }

    @Override
    public Order createUserOrder() {
        Scanner sc = new Scanner(System.in);
        int id, qty = 0, furtherBuyChoice;
        Order newOrder = new Order(this);
        boolean flag = true;
        double budgetBeforeWithdrawing = 0.0;
        int qtyBeforeDecreasing = 0;
        Product chosenProduct = null;
        double itemPrice = 0.0;
        while (flag) {
            System.out.println("Ваш поточний бюджет : " + budget);
            ProductWarehouse.displayAllProds();
            System.out.println("Будь ласка, введіть ідентифікатор продукту :");
            id = sc.nextInt();
            System.out.println("Будь ласка, введіть кількість товару для покупки: ");
            qty = sc.nextInt();
            chosenProduct = ProductWarehouse.getProductById(id);
            if (chosenProduct == null) {
                System.out.println("Продукту з таким ідентифікатором не існує. Все ще хочете придбати? 1- так, 0- ні");
                furtherBuyChoice = sc.nextInt();
                if (furtherBuyChoice != 1) {
                    flag = false;
                }
                continue;
            }
            if (qty < 1) {
                System.out.println("Обрана кількість не може бути менше 1!" + '\n' + "Все ще хочете купити? 1- так, 0- ні ");
                furtherBuyChoice = sc.nextInt();
                if (furtherBuyChoice != 1) {
                    flag = false;
                }
                continue;
            }
            if (chosenProduct.getProdQty() < 1) {
                System.out.println("Данний товар відсутній на складі!" + '\n' + "Все ще хочете купити? 1- так, 0- ні ");
                furtherBuyChoice = sc.nextInt();
                if (furtherBuyChoice != 1) {
                    flag = false;
                }
                continue;
            }
            itemPrice = chosenProduct.getProdPrice() * qty;
            if (chosenProduct.getProdQty() < qty) {
                System.out.println("Бажана кількість перевищує кількість товару на складі!" + '\n' + "Все ще хочете купити? 1- так, 0- ні ");
                furtherBuyChoice = sc.nextInt();
                if (furtherBuyChoice != 1) {
                    flag = false;
                }
                continue;

            }
            if (getCurrentBudget() < itemPrice) {
                System.out.println("У вас недостатньо грошей, щоб додати цей товар в кошик!" + '\n' + "Все ще хочете купити? 1- так, 0- ні ");
                furtherBuyChoice = sc.nextInt();
                if (furtherBuyChoice != 1) {
                    flag = false;
                }
                continue;
            }
            System.out.println("Данний товар коштує: " + itemPrice);

            qtyBeforeDecreasing = chosenProduct.getProdQty();

            budgetBeforeWithdrawing = getCurrentBudget();
            decreaseBudget(itemPrice);
            System.out.println("Бюджет :" + getCurrentBudget());
            OrderManagement.increaseProdQtyIfPresent(newOrder, chosenProduct, qty);
            OrderManagement.changeProdQty(newOrder, chosenProduct, qty);

            System.out.println("Хочете продовжити покупки? 1- так, 0- ні ");
            furtherBuyChoice = sc.nextInt();
            if (furtherBuyChoice != 1) {
                flag = false;
            }

        }

        if (qty == 0 || qtyBeforeDecreasing < qty || budgetBeforeWithdrawing < itemPrice) {
            return null;
        }
        OrderManagement.placeOrder(this, newOrder);

        return newOrder;
    }

    @Override
    public String toString() {
        return super.toString().replace('}', ' ') +
                ", budget= " + budget +
                ", order amount=" + OrderManagement.countOrdersByUser(this) +
                '}';
    }
}
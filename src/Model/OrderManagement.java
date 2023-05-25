package Model;
import java.util.ArrayList;
import java.util.HashMap;

public class OrderManagement {

    private static HashMap<User, ArrayList<Order>> orders;

    static {
        orders = new HashMap<>();
    }

    public static boolean placeOrder(User usr, Order ord) {
        int oldLen = getSize();
        if (orders.containsKey(usr))
            orders.get(usr).add(ord);
        else {
            ArrayList<Order> newOrderList = new ArrayList<>();
            newOrderList.add(ord);
            orders.put(usr, newOrderList);
        }
        return oldLen < getSize();
    }

    public static Order getOrder(int Id) {
        for (User usr : orders.keySet()) {
            for (Order ord : orders.get(usr)) {
                if (ord.getOrderId() == Id)
                    return ord;
            }
        }
        return null;
    }

    public static int getSize() {
        return orders.size();
    }

    public static boolean checkProductPresence(Order ord, Product product) {
        HashMap<Product, Integer> orderedProducts = ord.getOrderedProducts();
        for (Product prd : orderedProducts.keySet()) {
            if (product.equals(prd))
                return true;
        }
        return false;
    }

    public static void increaseProdQtyIfPresent(Order ord, Product prd, int qty) {
        if (checkProductPresence(ord, prd)) {
            int currQty = ord.getOrderedProducts().get(prd);

            currQty += qty;
            if (currQty <= prd.getProdQty()) {
                ord.getOrderedProducts().remove(prd);
                ord.getOrderedProducts().put(prd, currQty);
            }

        } else
            ord.getOrderedProducts().put(prd, qty);
    }

    public static void changeProdQty(Order ord, Product prd, int qty) {
        for (Product prod : ord.getOrderedProducts().keySet()) {
            if (prd.equals(prod))
                prod.decreaseProdQTY(qty);
        }
    }

    public static int countOrdersByUser(User usr) {
        int count = 0;
        if (orders.containsKey(usr))
            count = orders.get(usr).size();
        return count;
    }

    public static void getOrdersByUser(User usr) {
        ArrayList<Order> userOrders = orders.get(usr);
        if (userOrders == null || userOrders.isEmpty()) {
            System.out.println("Користувач " + usr.getUserFullName() + " ще не зробив жодного замовлення");
        } else {
            for (Order ord : orders.get(usr)) {
                displayOrderDetails(ord);
            }
        }

    }

    public static void displayOrderDetails(Order ord) {
        if (ord != null) {
            System.out.println("Замовлення № " + ord.getOrderId() + " Зробленно: " + ord.getBuyer().getFirstName() +
                    " " + ord.getBuyer().getLastName() + "Електронна пошта : " + ord.getBuyer().getEmail() + ", з: " + ord.getDateOfOrder());
            System.out.println("Замовленна продукція : ");
            HashMap<Product, Integer> orderedProducts = ord.getOrderedProducts();
            double totalOrderPrice = 0.0;
            for (Product prd : orderedProducts.keySet()) {
                System.out.println("Номер продукту:" + prd.getProdId() + ", Бранд: " + prd.getBrand() +
                        ", Опис: " + prd.getProdName() + " Ціна: " + prd.getProdPrice() + "$, Замовленно " +
                        orderedProducts.get(prd) + " Елементи " + " Загальна сума: " +
                        orderedProducts.get(prd) * prd.getProdPrice() + "$");
                totalOrderPrice += orderedProducts.get(prd) * prd.getProdPrice();
            }
            System.out.println("Загальна вартість замовлення становить: " + totalOrderPrice + "$");
        }
    }

    public static void displayOrders() {
        if (orders.isEmpty()) {
            System.out.println("Не зроблено жодного замовлення!");
            return;
        }
        for (User usr : orders.keySet()) {
            System.out.println(usr.getFirstName() + " " + usr.getLastName() + " Зроблено замовлення:" + orders.get(usr));
        }
    }
}
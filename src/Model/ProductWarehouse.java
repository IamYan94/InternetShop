package Model;
import java.util.ArrayList;
import java.util.Objects;

public class ProductWarehouse {

    private static ArrayList<Product> warehouse;

    static {
        warehouse = new ArrayList<>();
    }

    public static boolean addProduct(Product product) {
        if (!warehouse.contains(product)) {
            warehouse.add(product);
            System.out.println(product + " додано до складу");
            return true;
        }
        return false;
    }

    public static void prepopulateProducts() {
        warehouse.add(new Product("Royal Canin", "Сухий корм для собак", "Royal Canin mimi", 15, 32));
        warehouse.add(new Product("Pro Plan", "Вологий корм для собак", "Purina Pro Plan Veterinary Diets NF Renal Function ", 135, 9));
        warehouse.add(new Product("Trixie", "Лежаки", "Trixie Bett Jimmy М'яке місце для собак", 199, 15));
        warehouse.add(new Product("Trixie", "Клітки для собак ", "Клітка для собак з 2-ма дверима", 99, 11));
        warehouse.add(new Product("Trixie", "Іграшки для собак та цуценят", "Collar Pulle", 9, 17));
        warehouse.add(new Product("Petstages", "іграшки для собак", "Petstages Dogwood Stick", 5, 8));
        warehouse.add(new Product("Flamingo", "Охолоджуючий килимок для собак і кішок", "Flamingo Cooling Pad Fresk", 25, 5));
        warehouse.add(new Product("Trixie", "Іграшки для цуценяти", "Trixie 35641 М'яка іграшка для цуценяти (Осел)\n", 97, 62));
        warehouse.add(new Product("Trixie", "Іграшки для котів та кошенят", "Trixie 35671 М'яка іграшка для собак (Лінивець)", 25, 72));
        warehouse.add(new Product("Trixie", "Іграшки для собак", "Trixie 34953 Lick'n'Snack Ball ", 25, 9));
    }

    public static boolean removeProduct(Product product) {
        if (warehouse.contains(product)) {
            warehouse.remove(product);
            return true;
        }
        return false;
    }

    public static boolean removeProduct(String name) {
        Product prodToRemove = getProduct(name);
        return removeProduct(prodToRemove);
    }

    public static boolean removeProduct(int id) {
        Product prodToRemove = getProductById(id);
        return removeProduct(prodToRemove);
    }

    public static Product getProduct(String name) {
        for (Product prd : warehouse) {
            if (Objects.equals(prd.getProdName(), name))
                return prd;
        }
        return null;
    }

    private static boolean inputCheck(String input) {
        return input != null && input.length() > 1;
    }

    public static void searchByName(String name) {
        if (!inputCheck(name)) {
            System.out.println("Введені дані повинні містити принаймні 2 символи");
            return;
        }
        ArrayList<Product> foundProds = new ArrayList<>();
        for (Product prd : warehouse) {
            if (prd.getProdName().toLowerCase().contains(name.toLowerCase()))
                foundProds.add(prd);
        }
        displayAllProds(foundProds);
    }

    public static Product getProductById(int id) {
        for (Product prd : warehouse) {
            if (Objects.equals(prd.getProdId(), id))
                return prd;
        }
        return null;
    }

    public static void searchByBrand(String brandName) {
        if (!inputCheck(brandName)) {
            System.out.println("Введені дані повинні містити принаймні 2 символи");
            return;
        }
        ArrayList<Product> foundProds = new ArrayList<>();
        for (Product prd : warehouse) {
            if (prd.getBrand().toLowerCase().contains(brandName.toLowerCase()))
                foundProds.add(prd);
        }
        displayAllProds(foundProds);
    }

    public static void searchByCategory(String category) {
        if (!inputCheck(category)) {
            System.out.println("Введені дані повинні містити принаймні 2 символи");
            return;
        }
        ArrayList<Product> foundProds = new ArrayList<>();
        for (Product prd : warehouse) {
            if (prd.getCategory().toLowerCase().contains(category.toLowerCase()))
                foundProds.add(prd);
        }
        displayAllProds(foundProds);
    }

    public static void searchProdsInPriceRange(double startPrice, double endPrice) {
        ArrayList<Product> foundProds = new ArrayList<>();
        for (Product prd : warehouse) {
            if (prd.getProdPrice() >= startPrice && prd.getProdPrice() <= endPrice) {
                foundProds.add(prd);
            }
        }
        displayAllProds(foundProds);

    }

    public static boolean buyProduct(User usr, Product prd) {
        if (usr.getStatus() == UserStatus.ACTIVE) {

        }
        return false;
    }

    public static ArrayList<Product> getAllProducts() {
        return warehouse;
    }

    public static int getProductsQty() {
        return warehouse.size();
    }

    public static void displayAllProds(ArrayList<Product> prods) {
        if (prods == null || prods.size() == 0) {
            System.out.println("Немає товарів для показу");
            return;
        }
        for (Product prd : prods) {
            displayProduct(prd);
        }
    }

    public static void displayAllProds() {
        for (Product prd : warehouse) {
            displayProduct(prd);
        }
    }

    private static void displayProduct(Product prd) {
        System.out.println("Товар №: " + prd.getProdId() + ", бренд: " + prd.getBrand() + ", категорія: "
                + prd.getCategory() + ", назва: " + prd.getProdName() +
                ", ціна: " + prd.getProdPrice() + " $, кількість: " + prd.getProdQty());
    }

}
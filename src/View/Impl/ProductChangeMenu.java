package View.Impl;
import Model.Admin;
import Model.Product;
import Model.ProductWarehouse;
import View.Menu;

import java.util.Scanner;

public class ProductChangeMenu implements Menu {

    Admin adm = null;
    AdminMenu adminMenu = null;

    ProductChangeMenu(AdminMenu adminMenu, Admin adm) {
        this.adm = adm;
        this.adminMenu = adminMenu;
    }

    @Override
    public void show() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Змінити назву продукту");
        System.out.println("2. Змінити категорію продукту");
        System.out.println("3. Змінити вартість продукту");
        System.out.println("4. Змінити кількість товару");
        System.out.println("5. Додати новий товар");
        System.out.println("6. Повернутись до попереднього меню");
        int admChoice = sc.nextInt();

        switch (admChoice) {

            case 1:
                changeProductTemplate("назва");
                show();
                break;
            case 2:
                changeProductTemplate("категорія");
                show();
                break;
            case 3:
                changeProductTemplate("ціна");
                show();
                break;
            case 4:
                changeProductTemplate("кількість");
                show();
                break;
            case 5:
                adm.addNewProduct();
                show();
                break;
            case 6:
                exit();
                break;
            default:
                System.out.println("Невірний вибір !");
                show();
        }

    }

    private void changeStringField(String fieldToChange, String newValue, Product chosenProd) {

        switch (fieldToChange) {
            case "назва":
                chosenProd.setProdName(newValue);
                System.out.println("Продукт " + fieldToChange + " №: " + chosenProd.getProdId() + " було змінено: " +
                        chosenProd.getProdName());
                break;
            case "категорія":
                chosenProd.changeCategory(newValue);
                System.out.println("Продукт " + fieldToChange + " №: " + chosenProd.getProdId() + " було змінено: " +
                        chosenProd.getProdName());
                break;
        }
    }

    private void changeProductTemplate(String fieldToChange) {
        ProductWarehouse.displayAllProds();
        System.out.println("Введіть номер продукту:");
        Scanner scNumbers = new Scanner(System.in);
        Scanner scText = new Scanner(System.in);
        int prodId = scNumbers.nextInt();
        Product chosenProd = ProductWarehouse.getProductById(prodId);
        if (chosenProd != null) {
            System.out.println("Введіть новий продукт " + fieldToChange);
            if (fieldToChange.equals("категорія") || fieldToChange.equals("назва")) {

                String newValue = scText.nextLine();
                changeStringField(fieldToChange, newValue, chosenProd);
            }
            if (fieldToChange.equals("кількість")) {

                int newQty = scNumbers.nextInt();
                chosenProd.setProdQty(newQty);
                System.out.println("Продукт " + fieldToChange + "товар з індефікатором: " + chosenProd.getProdId() + " було змінено: " +
                        chosenProd.getProdQty());
            }
            if (fieldToChange.equals("Ціна")) {
                double newPrice = scNumbers.nextDouble();
                chosenProd.setProdPrice(newPrice);
                System.out.println("Продукт " + fieldToChange + "товар з індифікатором: " + chosenProd.getProdId() + " було змінено: " +
                        chosenProd.getProdPrice());
            }

        } else {
            System.out.println("Товар з індифікатором: " + prodId + " не існує! ");
        }
    }

    @Override
    public void exit() {
        adminMenu.show();
    }
}
package View.Impl;
import Model.ProductWarehouse;
import View.Menu;

import java.util.Scanner;

public class ProductSearchMenu implements Menu {

    ClientMenu clm = null;

    ProductSearchMenu(ClientMenu clm) {
        this.clm = clm;
    }

    @Override
    public void show() {
        Scanner scDigits = new Scanner(System.in);
        Scanner scText = new Scanner(System.in);
        int searchChoice = 0;
        System.out.println("Оберіть операцію:");
        System.out.println("1. Пошук товару за назвою");
        System.out.println("2. Пошук товару за брендом");
        System.out.println("3. Пошук товару за категорією");
        System.out.println("4. Пошук товару в ціновому діапазоні");
        System.out.println("5. Повернутися до меню клієнта");
        searchChoice = scDigits.nextInt();
        String searchParameters;
        switch (searchChoice) {

            case 1 -> {
                Scanner sc1 = new Scanner(System.in);
                System.out.println("Будь ласка, введіть слово, назву продукту :");
                searchParameters = scText.nextLine();
                System.out.println(searchParameters);
                ProductWarehouse.searchByName(searchParameters);
                show();
            }
            case 2 -> {
                Scanner sc1 = new Scanner(System.in);
                System.out.println("Будь ласка, введіть слово, бренд товару:");
                searchParameters = scText.nextLine();
                ProductWarehouse.searchByBrand(searchParameters);
                show();
            }
            case 3 -> {
                Scanner sc1 = new Scanner(System.in);
                System.out.println("Будь ласка, введіть слово, категорія товару:");
                searchParameters = scText.nextLine();
                ProductWarehouse.searchByCategory(searchParameters);
                show();
            }
            case 4 -> {
                double startPrice = 0.0, endPrice = 0.0;
                System.out.println("Будь ласка, введіть діапазон цін:");
                System.out.println("Ціна від:");
                startPrice = scDigits.nextDouble();
                System.out.println("Ціна до:");
                endPrice = scDigits.nextDouble();
                if (startPrice > endPrice) {
                    System.out.println("Стартова ціна має бути меншою або дорівнювати кінцевій ціні");
                    show();
                }
                ProductWarehouse.searchProdsInPriceRange(startPrice, endPrice);
                show();
            }
            case 5 -> clm.show();
            default -> {
                System.out.println("Невірний пункт меню");
                show();
            }
        }
    }

    @Override
    public void exit() {
        clm.show();
    }
}

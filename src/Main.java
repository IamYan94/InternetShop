import Model.*;
import View.Impl.LoginMenu;

public class Main {
    public static void main(String[] args) {

        User oleh = UserManager.createClient("Oleh", "Sakharuk", "oleh.sahar@.com", "000", 5500.00);
        User natalia = UserManager.createClient("Natalia", "Nikolaieva", "natalia.skoropad@.com", "0210", 3100.00);
        User nastia = UserManager.createClient("Nastia", "Sakharuk", "nastyashurba.com", "1234", 12750.00);

        User yan = UserManager.createAdmin("Yan", "Nikolaiev", "yan.nikolaiev.com", "777");

        ProductWarehouse.prepopulateProducts();
        new LoginMenu().show();
    }

}
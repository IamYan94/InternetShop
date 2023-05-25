package View.Impl;
import View.Menu;

public class UserMainMenu implements Menu {
    @Override
    public void show() {
        System.out.println("!!");
    }

    @Override
    public void exit() {
        new LoginMenu().show();
    }
}

package View.Impl;

import View.Menu;

public class ProductMenu implements Menu {

    @Override
    public void show() {

    }

    @Override
    public void exit() {
        new LoginMenu().show();
    }
}
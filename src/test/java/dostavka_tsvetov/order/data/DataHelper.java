package dostavka_tsvetov.order.data;

import static com.codeborne.selenide.Selenide.open;

public class DataHelper {

    private DataHelper() {
    }

    // Методы открытия различных страниц
    public static void openMainPage() {
        open("https://www.dostavka-tsvetov.com/");
    }

    public static void openOrderPage() {
        open("https://www.dostavka-tsvetov.com/index.php?route=checkout/simple");
    }

    public static void openOProductPage() {
        open("https://www.dostavka-tsvetov.com/11-roz");
    }

}

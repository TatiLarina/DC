package dostavka_tsvetov.order.page;

import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    private final ElementsCollection products = $$(".catalogtovarov_tovar");
    private final ElementsCollection buttonsBuyNow = $$(".js-btn-quick-order-list");
    private final ElementsCollection buttonsAddToCart = $$(".add_card__button");


    public void addToCart(int number) {
        products.get(number).hover();
        buttonsAddToCart.get(number).click();

    }

}

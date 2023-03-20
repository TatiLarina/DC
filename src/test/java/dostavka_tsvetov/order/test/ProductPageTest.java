package dostavka_tsvetov.order.test;

import dostavka_tsvetov.order.data.DataHelper;
import dostavka_tsvetov.order.page.ProductPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class ProductPageTest {

    @BeforeEach
    void before() {
        DataHelper.openOProductPage();
    }

    // Купить в один клик. Валидные данные
    @Test
    void buyOneClickSuccess() {
        var productPage = new ProductPage();
        productPage.buyOneClick("Тестовый заказ", "0000000000");
    }

    // Добавление в избранное
    @Test
    void addWishlist() {
        var productPage = new ProductPage();
        productPage.clickHeart(0);
        $$(".wishlist_tov_active").get(0).shouldBe(visible);
        $$(".wishlist_tov_active").get(1).shouldBe(visible);
    }
}

package dostavka_tsvetov.order.test;

import dostavka_tsvetov.order.data.DataHelper;
import dostavka_tsvetov.order.page.ProductPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductPageTest {

    @BeforeEach
    void before() {
        DataHelper.openOProductPage();
    }

    // Купить в один клик
    @Test
    void buyOneClickEntryField() {
        var productPage = new ProductPage();
        productPage.buyOneClick("", "");
        $(".modal_form__input.name.error").shouldBe(visible);
        $(".modal_form__input.telephone.error").shouldBe(visible);
    }

    @Test
    void buyOneClickEntryFieldPhone() {
        var productPage = new ProductPage();
        productPage.buyOneClick("Тестовый заказ", "");
        $(".modal_form__input.telephone.error").shouldBe(visible);
    }

    @Test
    void buyOneClickEntryFieldName() {
        var productPage = new ProductPage();
        productPage.buyOneClick("", "0000000000");
        $(".modal_form__input.name.error").shouldBe(visible);
    }

    @Test
    void buyOneClickSuccess() {
        var productPage = new ProductPage();
        productPage.buyOneClick("Тестовый заказ", "0000000000");
        $(".oneclick_success_block").shouldBe(visible);
        $(".product_information_buttons__buyOneClick__close").click();
        $(".modalOnClick").shouldBe(hidden);
    }

    // Добавление в корзину
    @Test
    void addToCart() {
        $(".product_information_buttons__addCart").click();

    }

    // Переход на Правила возврата
    @Test
    void returnPolicy() {
        var productPage = new ProductPage();
        $$(".utp_item").get(1).click();
        switchTo().window(1);
        String expected = "Гарантии";
        String actual = $(".assurance_header__h1").getText().trim();
        assertEquals(expected, actual);
    }


}
